package IHM;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BLL.ArticleManager;
import BO.Article;
import BLL.RetraitManager;
import BO.Retrait;
import Exceptions.DALException;

import java.util.Date;

@WebServlet("/Editer")
public class EditAuctionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleManager articleMgr = ArticleManager.getInstanceOf();
	private RetraitManager retraitMgr = RetraitManager.getInstanceOf();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		if (request.getParameter("id") == null) {
			response.sendRedirect("IndexServlet");
		}
		
		int id = Integer.parseInt(request.getParameter("id")); // recuperation ID de l'enchère a modifier
		
		try {
			Article article = articleMgr.selectByID(id);
			Retrait retrait = retraitMgr.selectByID(id);

			if (article != null) {
				// Envoyer les informations de l'enchère à la JSP de modification
				request.setAttribute("nom", article.getNom());
				request.setAttribute("desc", article.getDescription());
				request.setAttribute("cat", article.getCategorie());
				request.setAttribute("prixInit", article.getPrixInit() != null ? article.getPrixInit() : 0);
				request.setAttribute("dateDebut", article.getDateDebut());
				request.setAttribute("dateFin", article.getDateFin());
				request.setAttribute("rue", retrait.getRue());
				request.setAttribute("codePostal", retrait.getCode_postal());
				request.setAttribute("ville", retrait.getVille());
				
				request.getRequestDispatcher("/WEB-INF/JSP/EditAuction.jsp").forward(request, response);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			response.sendRedirect("Auction?id="+id+"");
		} catch (DALException e) {
			e.printStackTrace();
			response.sendRedirect("Auction?id="+id+"");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int ownerId = (int) request.getSession().getAttribute("id");
		int idArticle = Integer.parseInt(request.getParameter("id"));
		
		Article article = null;
		try {
			article = articleMgr.selectByID(idArticle);
			
			// test de la date
			if (article.getDateDebut().before(Date.from(Instant.now()))) { // si on essaye de modifier un article dont les enchères ont débuté
				request.setAttribute("msgErreur", "Modification impossible, les enchères sont engagées");
				doGet(request, response); // affiche la jsp
				return;
			}
		} catch (DALException e2) {
			e2.printStackTrace();
			request.setAttribute("msgErreur", e2.getMessage());
			doGet(request, response); // affiche la jsp
			return;
		}
		if (ownerId != article.getOwnerId()) { // usurpateur !
			response.sendRedirect("Auction?id=" + idArticle); // retourne à la vue de l'article
		}
		
		// ENREGISTRER update
		if (request.getParameter("register") != null) {
			
			String nom = request.getParameter("nom");
			String desc = request.getParameter("desc");
			String cat = request.getParameter("cat");
			int prixInit = Integer.parseInt(request.getParameter("prixInit"));
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dateDebut = null;
			Date dateFin = null;
			try {
				dateDebut = sdf.parse(request.getParameter("dateDebut"));
				dateFin = sdf.parse(request.getParameter("dateFin"));
			} catch (ParseException e1) {
				e1.printStackTrace();
				request.setAttribute("msgErreur", "Problème de dates"); // ne devrait pas arriver
				
				doGet(request, response); // affiche la jsp
				return;
			}
			
			String rue = request.getParameter("rue");
			String codePostal = request.getParameter("codePostal");
			String ville = request.getParameter("ville");
			
			// créations d'objets avec les même id que ceux dans la bdd
			Article art = new Article(idArticle, nom, desc, cat, dateDebut, dateFin, prixInit, prixInit, ownerId);
			Retrait ret = new Retrait(idArticle, rue, codePostal, ville);
			
			try {
				articleMgr.update(art);
				retraitMgr.update(ret);
			} catch (Exception e) { // DAL ou BLL
				request.setAttribute("msgErreur", e.getMessage());
			}
		} else if (request.getParameter("delete") != null) { // suppression
			
			try {
				articleMgr.delete(idArticle);
				response.sendRedirect("IndexServlet");
			} catch (DALException e) {
				e.printStackTrace();
				request.setAttribute("msgErreur", e.getMessage());
			}
		} else if (request.getParameter("comeback") != null) {
			
			response.sendRedirect("Auction?id=" + idArticle); // retour à la vue de l'article
			return;
		} else { // usurpateur !
			response.sendRedirect("Auction?id=" + idArticle); // retourne à la vue de l'article
			return;
		}
		
		doGet(request, response); // affiche la jsp
	}
}
