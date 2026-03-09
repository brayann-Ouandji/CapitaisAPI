package fr.esigelec.api.jee;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;

@WebFilter(urlPatterns = {"/CountryServlet", "/CapitalServlet", "/CountryEditServlet"})

public class AuthenticationFilter implements Filter {

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		HttpSession session = request.getSession(false);
		
		if (session != null && session.getAttribute("UserName") != null) {
			arg2.doFilter(request, response);
		} else {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.print("{\"error\" : \"Authentification required\"}");
			return;
		}
	}

}
