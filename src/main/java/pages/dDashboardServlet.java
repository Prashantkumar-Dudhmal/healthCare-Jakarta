package pages;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import dao.AppointmentDao;
import dao.AppointmentDaoImpl;
import dao.DoctorDao;
import dao.DoctorDaoImpl;
import dto.AppointmentDTO;
import entities.Doctor;
import entities.User;

@WebServlet("/dDashboardServlet")
public class dDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AppointmentDao appDao;
	private DoctorDao dDao;
	
	public void init(ServletConfig config) throws ServletException {
		try {
			dDao = new DoctorDaoImpl();
			appDao = new AppointmentDaoImpl();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException("Error in Init of dDashboardServlet",e);
		}		
		System.out.println("Doctor DashBoard Servlet Init Done!!");
	}
	
	public void destroy() {
		try {
			dDao.cleanUp();
			appDao.cleanUp();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Doctor DashBoard Servlet Destroy Done!!");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		try(PrintWriter pw = response.getWriter()){
			HttpSession ss = request.getSession();
			if(ss.isNew()) {
				pw.print("Cookies Mismatch, LogIn Again plz <br><a href='index.html'>Back</a>");
				pw.flush();
			}
			else {
				String msg = request.getParameter("msg");
				if(msg!=null) {					
					pw.print("<h5> Alert: "+msg+"</h5>");
				}
				User u = (User) ss.getAttribute("user_details");
				Doctor d = dDao.getDoctorDetails(u.getUserId());
				ss.setAttribute("doctor_details", d);
				pw.printf("""
						<h2 style="text-align:center;">Doctor Dashboard</h2>
						<h3>Hello %s %s</h3>
						<p>=====> Qualifications: %s       & Speciality: %s   </p>
						<p>=====> Experience    : %d years & Fees      : %d rs</p>
						<h3 style="text-align:center;">Patient Appointments</h3>
						""",u.getFirstName(),u.getLastName(),d.getQualifications(),d.getSpeciality(),d.getExperienceInYears(),d.getFees());
				List<AppointmentDTO> ls = appDao.getDocAppointments(d.getDoctorId());
				pw.print("<table style='background-color: #f5f2a9; width:75%; margin: auto' border='1'><tr style='background-color: #e3dd49;'><th>AppointmentId</th><th>TimeStamp</th><th>DoctorName</th><th>Mark Complete</th><th>Cancel</th></tr>");
				for(AppointmentDTO adto : ls) {
					LocalDate dt = adto.getAppointmentDateTime().toLocalDateTime().toLocalDate();
					pw.printf("<tr><td>  %d  </td><td>  %s  </td><td>  %s %s  </td><td> <button><a href='MarkCompleteAppointmentServlet?appId=%d'>Mark Complete</a></button></td> <td> <button><a href='CancelAppointmentServlet?appId=%d&date=%s'>Cancel</a></button></td></tr>",adto.getAppointmentId(),adto.getAppointmentDateTime(),adto.getFirstName(),adto.getLastName(),adto.getAppointmentId(),adto.getAppointmentId(),dt);
				}
				pw.print("</table><br><br>");
				pw.print("<button><a href='logout'>LogOut</a></button>");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
