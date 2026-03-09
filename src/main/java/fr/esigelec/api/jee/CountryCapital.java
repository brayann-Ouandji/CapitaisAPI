package fr.esigelec.api.jee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import fr.esigelec.api.dao.DBDAO;

/**
 * Servlet implementation class CountryCapital
 */
@WebServlet("/CountryCapital")
public class CountryCapital extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CountryCapital() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String countrycode = request.getParameter("countrycode");
		DBDAO dbdao = new DBDAO();
		CountryModel countryM = dbdao.getCountry(countrycode);
		String capital= countryM.getCapital();
		String country = countryM.getName();	
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		String jsonResponse = "{\"country\": \"" + country + "\", \"capital\": \"" + capital + "\", \"code\": \"" + countrycode + "\"}";
		out.print(jsonResponse);
		out.flush();
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
