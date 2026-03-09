package fr.esigelec.api.jee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import fr.esigelec.api.dao.DBDAO;
/**
 * Servlet implementation class CountryEditServlet
 */
@WebServlet("/CountryEditServlet")
public class CountryEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CountryEditServlet() {
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
		response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        
        // take the operation we want.
        String operation = request.getParameter("operation");
        if (operation == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"error\":\"Operation parameter is MIssing\"}");
            return;
        }

        String code = request.getParameter("code");
        String name = request.getParameter("name");
        String capital = request.getParameter("capital");
        
        DBDAO dao = new DBDAO();

        // We use a switch to execute the right SQL method
        switch (operation) {
            case "insert":
                if (dao.insertCountry(code, name, capital)) {
                    response.setStatus(HttpServletResponse.SC_CREATED); // 201 
                    out.print("{\"message\":\"Country inserted successfully\"}");
                } else {
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    out.print("{\"error\":\"Insertion failed\"}");
                }
                break;
                
            case "update":
                if (dao.updateCountry(code, name, capital)) {
                    response.setStatus(HttpServletResponse.SC_OK); // 200 OK 
                    out.print("{\"message\":\"Country updated successfully\"}");
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    out.print("{\"error\":\"Update failed. Country not found.\"}");
                }
                break;
                
            case "delete":
                if (dao.deleteCountry(code)) {
                    response.setStatus(HttpServletResponse.SC_OK); // 200 OK
                    out.print("{\"message\":\"Country deleted successfully\"}");
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    out.print("{\"error\":\"Delete failed. Country not found.\"}");
                }
                break;
                
            default:
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print("{\"error\":\"Unknown operation\"}");
        }
        out.flush();
    }
}
