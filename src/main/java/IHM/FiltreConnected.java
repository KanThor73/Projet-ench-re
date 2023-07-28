package IHM;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class FiltreConnected
 */
@WebFilter(urlPatterns = {"/Connexion", "/CreerCompte"}, dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.INCLUDE,
		DispatcherType.FORWARD, DispatcherType.ERROR })// renseigner des que les pages sont faite
public class FiltreConnected implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();

		if (session.getAttribute("id") != null) { // si la personne est connectée

			httpResponse.sendRedirect("IndexServlet"); // retourne au menu
		}else {
			chain.doFilter(httpRequest, httpResponse);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
