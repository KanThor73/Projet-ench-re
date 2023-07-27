package IHM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BLL.ArticleManager;
import BO.Article;
import BLL.RetraitManager;
import BO.Retrait;
import BLL.AuctionManager;
import BO.Auction;
import BLL.UserManager;
import BO.User;
import Exceptions.DALException;
import Exceptions.BOException;

import java.util.Collections;
import java.util.List;

public class winAuctionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleManager articleMgr = ArticleManager.getInstanceOf();
	private RetraitManager retraitMgr = RetraitManager.getInstanceOf();
	private AuctionManager auctionMgr = AuctionManager.getInstanceOf();
	private UserManager userMgr = UserManager.getInstanceOf();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int sessionId = (int) request.getSession().getAttribute("id");
		int id = Integer.parseInt(request.getParameter("id"));
		
		try {
			Article article = articleMgr.selectByID(id); // récupération de l'article concerné
			List<Auction> auctions = auctionMgr.selectByArticle(id); // récupération des enchères effectuées sur cet article
			
			if (auctions.isEmpty() && sessionId == article.getOwnerId()) { // si aucune enchère n'a été faite et vue du proprio
				request.setAttribute("delete", true); // demander la suppression
				getServletContext().getNamedDispatcher("WinAuctionJSP").forward(request, response);
				return;
			} else if (auctions.isEmpty()) { // si aucune enchère mais pas le propriétaire
				response.sendRedirect("IndexServlet"); // ciao
				return;
			}
			Auction maxAuction = Collections.max(auctions); // récupération de la plus haute enchère
			
			if (sessionId == article.getOwnerId()) { // proprio
				
				request.setAttribute("proprio", true);
				
				User winner = userMgr.selectByID(maxAuction.getNoUtilisateur());
				request.setAttribute("contactId", winner.getNoUser());
				request.setAttribute("contactPseudo", winner.getPseudo());
				
				getServletContext().getNamedDispatcher("WinAuctionJSP").forward(request, response); // affiche la jsp
				
			} else if (sessionId == maxAuction.getNoUtilisateur()) { // gagnant
				
				request.setAttribute("winner", true);
				
				User proprio = userMgr.selectByID(article.getOwnerId());
				request.setAttribute("contactId", proprio.getNoUser());
				request.setAttribute("contactPseudo", proprio.getPseudo());
				
				Retrait retrait = retraitMgr.selectByID(id);
				request.setAttribute("rue", retrait.getRue());
				request.setAttribute("codePostal", retrait.getCode_postal());
				request.setAttribute("ville", retrait.getVille());
				
				getServletContext().getNamedDispatcher("WinAuctionJSP").forward(request, response); // affiche la jsp
				
			} else { // usurpateur ! pas concerné
				response.sendRedirect("IndexServlet"); // ciao
			}

		} catch (DALException e) {
			e.printStackTrace();
			request.setAttribute("msgErreur", e.getMessage());
			getServletContext().getNamedDispatcher("WinAuctionJSP").forward(request, response); // affiche la jsp
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO rediriger les personnes non concernées
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		try {
			if (request.getParameter("noAuction") != null) {
				
				articleMgr.delete(id);
				
			} else if (request.getParameter("auction") != null) {
				
				List<Auction> auctions = auctionMgr.selectByArticle(id); // récupération des enchères
				Auction maxAuction = Collections.max(auctions); // récupération de la plus haute enchère
				
				Article article = articleMgr.selectByID(id);
				User proprio = userMgr.selectByID(article.getOwnerId());
				
				try {
					proprio.changeCredit(maxAuction.getMontantEnchere()); // credite le propriétaire
				} catch (BOException e1) { // ne devrait pas arriver
					e1.printStackTrace();
					request.setAttribute("msgErreur", e1.getMessage());
					getServletContext().getNamedDispatcher("WinAuctionJSP").forward(request, response); // affiche la jsp
				}
				
				articleMgr.delete(id); // suppression en bdd
				userMgr.update(proprio); // update en bdd
				
				response.sendRedirect("IndexServlet"); // retour à l'accueil (un peu barbare)
				
			} else { // usurpateur !
				response.sendRedirect("IndexServlet"); // ciao
			}
		} catch (DALException e) {
			e.printStackTrace();
			request.setAttribute("msgErreur", e.getMessage());
			getServletContext().getNamedDispatcher("WinAuctionJSP").forward(request, response); // affiche la jsp
		}
	}

}
