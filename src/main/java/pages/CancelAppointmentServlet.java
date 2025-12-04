package pages;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;

import dao.AppointmentDao;
import dao.AppointmentDaoImpl;

/**
 * Servlet implementation class CancelAppointmentServlet
 */
@WebServlet("/CancelAppointmentServlet")
public class CancelAppointmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AppointmentDao appDao;
	
	public void init(ServletConfig config) throws ServletException {
		try {
			appDao = new AppointmentDaoImpl();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException("Error in Cancel Init",e);
		}
	}

	public void destroy() {
		try {
			appDao.cleanUp();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ss = request.getSession();
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
		}}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
