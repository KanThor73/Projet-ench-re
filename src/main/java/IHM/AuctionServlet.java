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

import java.util.Collections;
import java.util.List;

public class AuctionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");

		int articleId = Integer.parseInt(request.getParameter("id"));

		try {

			ArticleManager articleMgr = ArticleManager.getInstanceOf();
			AuctionManager auctionMgr = AuctionManager.getInstanceOf();
			UserManager userMgr = UserManager.getInstanceOf();
			
			// Article et id propriétaire
			Article article = articleMgr.selectByID(articleId);
			int idOwner = article.getOwnerId();
			
			// Liste des enchères et plus grande enchère
			List<Auction> auctions = auctionMgr.selectByArticle(articleId);
			Auction maxEnchere = null;
			if (!auctions.isEmpty()) {
				maxEnchere = Collections.max(auctions);
			}
			
			// Plus grand offrant
			User user = null;
			if (maxEnchere != null) {
				int idEnchere = maxEnchere.getNoUtilisateur();
				user = userMgr.selectByID(idEnchere);
			}
			
			// Mise minimale
			int mise = 0;
			if (maxEnchere != null) {
				mise = maxEnchere.getMontantEnchere();
			} else {
				mise = article.getPrixInit();
			}
			
			if (request.getSession().getAttribute("id") != null) { // utilisateur connecté
				int sessionId = Integer.parseInt(request.getSession().getAttribute("id").toString());
				request.setAttribute((sessionId == idOwner) ? "proprio" : "user", "true");
			}
			request.setAttribute("id", request.getParameter("id"));
			request.setAttribute("nom", article.getNom());
			request.setAttribute("desc", article.getDescription());
			request.setAttribute("cat", article.getCategorie());
			request.setAttribute("bestOffer", mise);
			request.setAttribute("bestOfferer", (user != null) ? user.getPseudo() : new String(""));
			request.setAttribute("prixVente", (article.getPrixInit() != null) ? article.getPrixInit() : 0);
			request.setAttribute("dateFin", article.getDateFin().toString());
			
			getServletContext().getNamedDispatcher("AuctionJSP").forward(request, response);

		} catch (DALException e) {
			e.printStackTrace();
			response.sendRedirect("IndexServlet"); // retourne à l'accueil
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
