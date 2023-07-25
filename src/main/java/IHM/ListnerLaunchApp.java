package IHM;

import java.util.List;
import BLL.CategorieManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exceptions.DALException;
import javax.servlet.http.HttpSession;

/**
 * Application Lifecycle Listener implementation class ListnerLaunchApp
 *
 */
@WebListener
public class ListnerLaunchApp implements ServletContextListener {
	CategorieManager catMgr = CategorieManager.getInstanceOf();

	public void contextDestroyed(ServletContextEvent arg0) {

	}

	public void contextInitialized(ServletContextEvent arg0) {
		HttpServletRequest request;
		HttpSession session = request.getSession();
		try {
			List<String> categories = catMgr.selectAll();
			categories.add("aucune");
			request.setAttribute("categories", categories);
		} catch (DALException e) {
			e.printStackTrace();
		}
	}

}
