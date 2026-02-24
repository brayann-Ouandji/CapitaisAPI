package fr.esigelec.api.jee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class CaptitalServlet
 */
@WebServlet("/CaptitalServlet")
public class CaptitalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CaptitalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String countrycode = request.getParameter("countrycode");
		DBDAO dbdao = new DBDAO();
		String capital = dbdao.getCapitalName(countrycode);
		
		if (countrycode == null || countrycode.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		} 
		
		if (capital == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
			
		} 
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			String jsonResponse = "{\"countrycode\": \"" + countrycode.toUpperCase() + "\", \"capital\": \"" + capital + "\"}";
			out.print(jsonResponse);
			out.flush();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
