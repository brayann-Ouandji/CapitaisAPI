package fr.esigelec.api.jee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import fr.esigelec.api.dao.DBDAO;

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
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        
        // take the operation we want.
        String operation = request.getParameter("operation");
        if (operation == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"error\":\"Operation parameter is MIssing\"}");
            return;
        }
		DBDAO dbdao = new DBDAO();
		String user = request.getParameter("username");
		String pass = request.getParameter("password");
		response.setContentType("application/json");
 		if( user == null || pass == null || user.isEmpty() || pass.isEmpty()) {
 			
 			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
 			
 			out.print("{\"message\": \" BAD REQUEST" + "\", \"error\": \"Missing Parameter , user or pass" + "\"}");
			return;
 		}
 		
 		dbdao.createUser(user, pass);
 		boolean validate = dbdao.validateUserCredentials(user, pass);
 		if (validate) {
 			HttpSession session = request.getSession(true);
 			session.setAttribute("UserName", user);
 			response.setStatus(HttpServletResponse.SC_OK);
 			out.print("{\"message\": \" Login is OK" + "\", \"user\": \"" + user + "\"}");
 		} else {
 			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
 			out.print("{\"message\": \" FORBIDDEN!" + "\", \"error\": \"Invalid login or password"+ "\"}");
 		}
 		
 		 out.flush();
	}

}
