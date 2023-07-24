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


@WebFilter(urlPatterns = {"/Vente"}, dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.INCLUDE,
		DispatcherType.FORWARD, DispatcherType.ERROR })// renseigner des que les pages sont faite

public class FiltreUsers implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();

		if (session.getAttribute("id") == null) {
			// laisse l'utilisateur sur la meme page s'il n''est pas connecte
			httpRequest.getRequestDispatcher(httpRequest.getRequestURL().toString()).forward(httpRequest,httpResponse);
		}else {			
			chain.doFilter(httpRequest, httpResponse);
		}	
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
}


// creer variable de session id si util refuse cookie
// recuperer jsessionID
// tester si l'un ou l'autre est present pour autoriser acces