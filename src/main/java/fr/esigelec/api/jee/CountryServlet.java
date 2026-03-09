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
 * Servlet implementation class CountryServlet
 */
@WebServlet("/CountryServlet")
public class CountryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CountryServlet() {
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
		CountryModel countrym = dbdao.getCountry(countrycode);
		if (countrycode == null || countrycode.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		if (countrym == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;

		}
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		String jsonResponse = "{\"country\": \"" + countrym.getName() + "\", \"capital\": \"" + countrym.getCapital()
				+ "\", \"ccode\": \"" + countrym.getCode() + "\"}";
		out.print(jsonResponse);
		out.flush();

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
