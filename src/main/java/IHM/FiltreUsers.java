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


@WebFilter(urlPatterns = {}, dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.INCLUDE,
		DispatcherType.FORWARD, DispatcherType.ERROR })// rensigner des que les pages sont faite

public class FiltreUsers implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();

		if (String.valueOf(session.getAttribute("userOk")) == "false") {
			httpRequest.getRequestDispatcher(httpRequest.getRequestURL().toString()).forward(httpRequest,httpResponse);
		}else {			
			chain.doFilter(httpRequest, httpResponse);
		}	
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
}
