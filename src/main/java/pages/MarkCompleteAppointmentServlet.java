package pages;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import dao.AppointmentDao;
import dao.AppointmentDaoImpl;


@WebServlet("/MarkCompleteAppointmentServlet")
public class MarkCompleteAppointmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AppointmentDao appDao;
	
	public void init(ServletConfig config) throws ServletException {
		try {
			appDao = new AppointmentDaoImpl();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException("Error in MarkComplete Init !",e);
		}
		System.out.println("Mark Complete Appointment Init Done!!");
	}

	public void destroy() {
		try {
			appDao.cleanUp();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Mark Complete Appointment Destroy Done!!");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long apptmtId = Long.parseLong(request.getParameter("appId"));
		
		System.out.println("Mark Complete logic for appointment Id : "+ apptmtId);
		try {
			int res = appDao.markComplete(apptmtId);
			if(res!=0) {
				response.sendRedirect("dDashboardServlet?msg=Marked Completed !!");
			}
			else {
				response.sendRedirect("dDashboardServlet?msg=Mark Complete Failed");				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
