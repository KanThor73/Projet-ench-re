package IHM;

import java.io.IOException;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BLL.ArticleManager;
import BO.Article;
import Exceptions.DALException;

public class AuctionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getServletContext().getNamedDispatcher("AuctionJSP").forward(request, response);

		int articleId = Integer.parseInt(request.getParameter("id"));

		try {

			ArticleManager articleMgr = ArticleManager.getInstanceOf();
			Article article = articleMgr.selectByID(articleId);

			request.setAttribute("nom", article.getNom());
			request.setAttribute("desc", article.getDescription());
			request.setAttribute("cat", article.getCategorie());
			request.setAttribute("prixVente", article.getPrixVente());
			request.setAttribute("dateFin", article.getDateFin());

			request.getRequestDispatcher("/Auction.jsp").forward(request, response);
		} catch (DALException e) {
			e.printStackTrace();

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
