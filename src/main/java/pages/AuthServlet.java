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

import dao.UserDao;
import dao.UserDaoImpl;
import entities.User;

@WebServlet(value="/login",loadOnStartup = 1)
public class AuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDao udao;
	
	public void init(ServletConfig config) throws ServletException {
		
		try {
			udao = new UserDaoImpl();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServletException("Error in Init of AuthServlet",e);
		}
		System.out.println("Auth Servlet Init Done!!");
	}

	public void destroy() {
		try {
			udao.cleanUp();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Auth Servlet Destroy Done!!");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		try(PrintWriter pw = response.getWriter()){
			
			String email = request.getParameter("em");
			String pass = request.getParameter("pass");
			User currentUser = udao.getUser(email, pass);
			if(currentUser!=null) {
				HttpSession session = request.getSession();
				session.setAttribute("user_details", currentUser);
				if(currentUser.getRole().equals("ROLE_PATIENT")) {
//					pw.print("Patient");
					response.sendRedirect("pDashboardServlet");
				}
				else if(currentUser.getRole().equals("ROLE_DOCTOR")) {
					response.sendRedirect("dDashboardServlet");
				}
				else if(currentUser.getRole().equals("ROLE_ADMIN")) {
					response.sendRedirect("aDashboardServlet");
				}
				else {
					pw.print("Login Done But Role Do Not Match With Anything!!");
				}
			}
			
			System.out.println("DoPost Of Auth Servlet Called!!");
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
