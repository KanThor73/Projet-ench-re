package IHM;

import java.io.IOException;
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

@WebServlet("/Editer")
public class EditAuctionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleManager articleMgr = ArticleManager.getInstanceOf();
	private RetraitManager retraitMgr = RetraitManager.getInstanceOf();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// recuperation ID de l'enchère a modifier

		String idString = request.getParameter("id");
		if (idString != null && !idString.isEmpty()) {
			try {
				int id = Integer.parseInt(idString);
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
				request.setAttribute("msgErreur", e.getMessage());
			} catch (DALException e) {
				e.printStackTrace();
			}
		}

		request.getRequestDispatcher("/WEB-INF/JSP/EditAuction.jsp").forward(request, response);
	}

	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String idAuctionString = request.getParameter("id");
		String newNom = request.getParameter("nom");
		String newDesc = request.getParameter("desc");
		String newCat = request.getParameter("cat");
		
		doGet(request, response);
	}
}
