package pages;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/aDashboardServlet")
public class aDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		System.out.println("Admin DashBoard Servlet Init Done!!");
	}

	public void destroy() {
		System.out.println("Admin DashBoard Servlet Destroy Done!!");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Admin DashBoard");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
