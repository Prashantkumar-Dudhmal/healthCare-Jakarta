package pages;

import jakarta.servlet.ServletContext;
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
import dao.PatientDao;
import dao.PatientDaoImpl;
import dto.AppointmentDTO;
import entities.Patient;
import entities.User;

@WebServlet(value="/pDashboardServlet")
public class pDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PatientDao pdao;
	private AppointmentDao appDao;
	
	
	public void init() throws ServletException {
		try {
			ServletContext ctx = getServletContext();
			String url = ctx.getInitParameter("DBURL");
			String user = ctx.getInitParameter("DBuser");
			String pass = ctx.getInitParameter("DBPass");
			pdao = new PatientDaoImpl(url,user,pass);
			appDao = new AppointmentDaoImpl(url,user,pass);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException("Error in Init of pDashboardServlet",e);
		}
		System.out.println("Patient DashBoard Servlet Init Done!!");
	}

	public void destroy() {
		try {
			pdao.cleanUp();
			appDao.cleanUp();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("Patient DashBoard Servlet Destroy Done!!");
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
				Patient p = pdao.getPatientDetails(u.getUserId());
				ss.setAttribute("patient_details", p);
				pw.printf("""
						<h2 style="text-align:center;">Patient Dashboard</h2>
						<h3>Hello %s %s</h3>
						<p>=====> Gender: %s & Blood Group: %s</p>
						<p>=====> Family History: %s</p>
						<h3 style="text-align:center;">Patient Appointments</h3>
						""",u.getFirstName(),u.getLastName(),p.getGender(),p.getBloodGroup(),p.getFamilyHistory());
				List<AppointmentDTO> ls = appDao.getAppointments(p.getPatientID());
				pw.print("<table style='background-color: #f5f2a9; width:75%; margin: auto' border='1'><tr style='background-color: #e3dd49;'><th>AppointmentId</th><th>TimeStamp</th><th>DoctorName</th><th>Cancel</th></tr>");
				for(AppointmentDTO adto : ls) {
					LocalDate d = adto.getAppointmentDateTime().toLocalDateTime().toLocalDate();
					pw.printf("<tr><td>  %d  </td><td>  %s  </td><td>  %s %s  </td> <td> <button><a href='CancelAppointmentServlet?appId=%d&date=%s'>Cancel</a></button></td></tr>",adto.getAppointmentId(),adto.getAppointmentDateTime(),adto.getFirstName(),adto.getLastName(),adto.getAppointmentId(),d);
				}
				pw.print("</table><br><br>");
				pw.print("<button><a href='BookAppointment.html'>Book Appointment</a></button>   <button><a href='logout'>LogOut</a></button>");
				
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
