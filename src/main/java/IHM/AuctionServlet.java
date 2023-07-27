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
			
			// Si date passée, renvoie sur winAuction / FinEncheres
			if (article.getDateFin().before(Date.from(Instant.now()))) {
				response.sendRedirect("FinEncheres?id=" + articleId);
				return;
			}
			
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
				mise = article.getPrixInit() != null ? article.getPrixInit() : 0;
			}
			
			if (request.getSession().getAttribute("id") != null) { // utilisateur connecté
				int sessionId = Integer.parseInt(request.getSession().getAttribute("id").toString());
				request.setAttribute((sessionId == idOwner) ? "proprio" : "user", "true");
				
				User thisUser = userMgr.selectByID(sessionId);
				request.setAttribute("solde", thisUser.getCredit());
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
		
		try {
			int idUser = Integer.parseInt(request.getSession().getAttribute("id").toString());
			int idArticle = Integer.parseInt(request.getParameter("id"));
			int relance = Integer.parseInt(request.getParameter("relance"));
			Date maintenant = Date.from(Instant.now());
			
			Auction auction = new Auction(idUser, idArticle, maintenant, relance);
			
			User newOfferer = userMgr.selectByID(auction.getNoUtilisateur());
			newOfferer.changeCredit(-relance); // retrait du crédit, potentielle BOException
			
			User lastOfferer = null;
			List<Auction> allAuctions = auctionMgr.selectByArticle(idArticle);
			if (!allAuctions.isEmpty()) {
				Auction lastAuction = Collections.max(allAuctions); // récupération de la dernière enchère
				
				// on crédite l'ancien bestOfferer
				lastOfferer = userMgr.selectByID(lastAuction.getNoUtilisateur());
				lastOfferer.changeCredit(lastAuction.getMontantEnchere());
			}
			
			Auction auctionCheck = auctionMgr.selectByID(idUser, idArticle);
			if (auctionCheck != null) { // déjà une enchère de ce (user, article) dans la bdd
				auctionMgr.update(auction); // controle et update de l'enchère
			} else {
				auctionMgr.insert(auction); // controle et ajout de l'enchère
			}
			
			// si tout s'est bien passé, on commit les crédits en bdd
			userMgr.update(newOfferer);
			if (lastOfferer != null) {
				userMgr.update(lastOfferer);
			}
			
			// on update le prix vente de l'article dans la bdd
			Article article = articleMgr.selectByID(idArticle);
			article.setPrixVente(relance);
			articleMgr.update(article);
			
		} catch (Exception e) { // DAL, BLL ou BO
			e.printStackTrace();
			request.setAttribute("msgErreur", e.getMessage());
		}
		
		doGet(request, response); // retour sur la même page
	}
}
