package pages;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import dao.AppointmentDao;
import dao.AppointmentDaoImpl;

@WebServlet("/CancelAppointmentServlet")
public class CancelAppointmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AppointmentDao appDao;
	
	public void init() throws ServletException {
		try {
			ServletContext ctx = getServletContext();
			String url = ctx.getInitParameter("DBURL");
			String user = ctx.getInitParameter("DBuser");
			String pass = ctx.getInitParameter("DBPass");
			appDao = new AppointmentDaoImpl(url,user,pass);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException("Error in Cancel Init",e);
		}
		System.out.println("Cancel Appointment Servlet Init Done!!");
	}

	public void destroy() {
		try {
			appDao.cleanUp();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Cancel Appointment Servlet Destroy Done!!");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long apptmtId = Long.parseLong(request.getParameter("appId"));
		LocalDate d1 = LocalDate.parse(request.getParameter("date"));
		LocalDate d2 = LocalDate.now();
		if(d1.equals(d2)) {
			response.sendRedirect("pDashboardServlet?msg=Todays date, Cant Cancel");				
		}
		else {
		System.out.println("Delete logic for appointment Id : "+ apptmtId+ "Date: "+ d1);
		try {
			int res = appDao.cancelAppointment(apptmtId);
			if(res!=0) {
				response.sendRedirect("pDashboardServlet?msg=Cancel SuccessFull!!");
			}
			else {
				response.sendRedirect("pDashboardServlet?msg=Cancel Failed");				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
	}
}
