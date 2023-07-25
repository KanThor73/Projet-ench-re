package IHM;

import java.io.IOException;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BLL.ArticleManager;
import BO.Article;
import BLL.AuctionManager;
import BO.Auction;
import BLL.UserManager;
import BO.User;
import Exceptions.DALException;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AuctionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int articleId = Integer.parseInt(request.getParameter("id"));

		try {

			ArticleManager articleMgr = ArticleManager.getInstanceOf();
			AuctionManager auctionMgr = AuctionManager.getInstanceOf();
			UserManager userMgr = UserManager.getInstanceOf();
			
			Article article = articleMgr.selectByID(articleId);
			int idOwner = article.getOwnerId();
			
			List<Auction> auctions = auctionMgr.selectByArticle(articleId);
			Auction maxEnchere = null;
			if (!auctions.isEmpty()) {
				maxEnchere = Collections.max(auctions);
			}
			
			User user = null;
			if (maxEnchere != null) {
				int idEnchere = maxEnchere.getNoUtilisateur();
				user = userMgr.selectByID(idEnchere);
			}
			
			if (request.getSession().getAttribute("id") != null) { // utilisateur connecté
				int sessionId = Integer.parseInt(request.getSession().getAttribute("id").toString());
				System.out.println(sessionId + " " + idOwner);
				System.out.println((sessionId == idOwner));
				request.setAttribute((sessionId == idOwner) ? "proprio" : "user", "true");
			} else {
				request.setAttribute("visiteur", "true");
			}
			request.setAttribute("id", request.getParameter("id"));
			request.setAttribute("nom", article.getNom());
			request.setAttribute("desc", article.getDescription());
			request.setAttribute("cat", article.getCategorie());
			request.setAttribute("bestOffer", (maxEnchere != null) ? maxEnchere.getMontantEnchere() : 0);
			request.setAttribute("bestOfferer", (user != null) ? user.getPseudo() : "");
			request.setAttribute("prixVente", (article.getPrixInit() != null) ? article.getPrixInit() : 0);
			request.setAttribute("dateFin", article.getDateFin().toString());

		} catch (DALException e) {
			e.printStackTrace();
			response.sendRedirect("/ProjetEnchere"); // retourne à l'accueil
		}
		
		getServletContext().getNamedDispatcher("AuctionJSP").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
