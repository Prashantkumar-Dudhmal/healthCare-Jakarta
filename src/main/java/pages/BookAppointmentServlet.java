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
import java.sql.Timestamp;
import java.time.LocalDateTime;

import dao.AppointmentDao;
import dao.AppointmentDaoImpl;
import entities.Appointment;
import entities.Patient;

@WebServlet(value = "/BookAppointmentServlet")
public class BookAppointmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AppointmentDao appDao;

	public void init(ServletConfig config) throws ServletException {
		try {
			appDao = new AppointmentDaoImpl();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException("Error in Book Init",e);
		}
		System.out.println("BookAppointment Servlet Init Done!!");
	}

	public void destroy() {
		try {
			appDao.cleanUp();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("book Appointment Servlet Destroy Done!!");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		try(PrintWriter pw = response.getWriter()){
			HttpSession ss = request.getSession();
			if(ss.isNew()) {
				pw.print("Allow Cookies then LogIn Again plz <br><a href='index.html'>Back</a>");
				pw.flush();
			}
			else {
				long doctorId = Long.parseLong(request.getParameter("doc_id"));
				LocalDateTime ldt = LocalDateTime.parse(request.getParameter("appointmentDateTime"));
				Timestamp ts = Timestamp.valueOf(ldt);
				
				Patient p = (Patient) ss.getAttribute("patient_details");
				System.out.println("Patient Id : "+ p.getPatientID());
				Appointment a = new Appointment(ts,doctorId, p.getPatientID());
				
				String res = appDao.bookAppointment(a);
				response.sendRedirect("pDashboardServlet?msg="+res);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
}
