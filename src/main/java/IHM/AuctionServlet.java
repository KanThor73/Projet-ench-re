package IHM;

import java.io.IOException;
import java.time.Instant;

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
import java.util.Date;

public class AuctionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleManager articleMgr = ArticleManager.getInstanceOf();
	private AuctionManager auctionMgr = AuctionManager.getInstanceOf();
	private UserManager userMgr = UserManager.getInstanceOf();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");

		int articleId = Integer.parseInt(request.getParameter("id"));

		try {
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
		
		int idUser = Integer.parseInt(request.getSession().getAttribute("id").toString());
		int idArticle = Integer.parseInt(request.getParameter("id"));
		int relance = Integer.parseInt(request.getParameter("relance"));
		Date maintenant = Date.from(Instant.now());
		
		Auction auction = new Auction(idUser, idArticle, maintenant, relance);
		
		try {
			Auction auctionCheck = auctionMgr.selectByID(idUser, idArticle);
			if (auctionCheck != null) { // déjà une enchère de ce (user, article) dans la bdd
				auctionMgr.update(auction); // update de l'enchère
			} else {
				auctionMgr.insert(auction); // ajout de l'enchère
			}
		} catch (Exception e) { // DAL ou BLL
			e.printStackTrace();
			request.setAttribute("msgErreur", e.getMessage());
		}
		
		doGet(request, response); // retour sur la même page
	}
}
