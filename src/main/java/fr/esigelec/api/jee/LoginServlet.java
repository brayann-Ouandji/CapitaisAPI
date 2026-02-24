package fr.esigelec.api.jee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DBDAO dbdao = new DBDAO();
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		 
 		if( user == null || pass == null || user.isEmpty() || pass.isEmpty()) {
 			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
 		}
 		boolean validate = dbdao.validateUserCredentials(user, pass);
 		if (validate) {
 			HttpSession session = request.getSession(true);
 			session.setAttribute("UserName", user);
 			response.setStatus(HttpServletResponse.SC_OK);
 			response.setContentType("application/json");
 			PrintWriter out = response.getWriter();
 			String jsonResponse = "{\"message\": \"OK" + "\", \"passwuserord\": \"" + user + "\"}";
 			out.print(jsonResponse);
 			out.flush();
 		}
 		
		doGet(request, response);
	}

}
