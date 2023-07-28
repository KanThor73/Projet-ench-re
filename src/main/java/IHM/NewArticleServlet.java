package IHM;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BLL.ArticleManager;
import BLL.UserManager;
import BLL.RetraitManager;
import BO.Article;
import BO.User;
import BO.Retrait;
import Exceptions.BLLException;
import Exceptions.DALException;

public class NewArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleManager articleMgr = ArticleManager.getInstanceOf();
	private UserManager userManager = UserManager.getInstanceOf();
	private RetraitManager retraitManager = RetraitManager.getInstanceOf();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = (int)request.getSession().getAttribute("id");
		
		// recuperer les infos user pour preremplir les champs
		try {
			User user = userManager.selectByID(id);
			
			request.setAttribute("rue",user.getRue());
			request.setAttribute("codePostal",user.getCodePostal());
			request.setAttribute("ville",user.getVille());
			
		} catch (DALException e) {
			e.printStackTrace();
			request.setAttribute("msgErreur","Problème d'accès aux données");
		}
		getServletContext().getNamedDispatcher("NewArticleJSP").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
		
		try {
			// Récupération des paramètres du formulaire de newAccount.jsp
			String nom = request.getParameter("nomArticle");
			String description = request.getParameter("description");
			String categorie = request.getParameter("categorie");
			int prixInit = Integer.parseInt(request.getParameter("prixInit"));
		
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			Date dateDebut = formatter.parse(request.getParameter("dateDebut"));
			Date dateFin = formatter.parse(request.getParameter("dateFin"));
			
			int id = Integer.parseInt(request.getSession().getAttribute("id").toString()); // récupération de l'id du user connecté
			Article newArticle = new Article(nom, description, categorie, dateDebut, dateFin, prixInit, id);
						
			articleMgr.insert(newArticle); // ajout de l'utilisateur
			
			// creation du retrait
			String rue = request.getParameter("rue");
			String codePostal = request.getParameter("codePostal");
			String ville = request.getParameter("ville");
			
			Retrait retrait = new Retrait(articleMgr.getMaxNoArticle(),rue,codePostal,ville); //creation d'un nouveau retrait
			retraitManager.insert(retrait);
			
			response.sendRedirect("IndexServlet");// retourne à l'accueil si bon déroulement
			
		} catch (ParseException e) {
			request.setAttribute("msgErreur","Problème de formattage des dates");
			getServletContext().getNamedDispatcher("NewArticleJSP").forward(request, response);
		} catch (DALException e) {
			request.setAttribute("msgErreur","Problème d'accès aux données");
			
			try {
				int idArticle = articleMgr.getMaxNoArticle();
				articleMgr.delete(idArticle); // au cas où l'article ait été ajouté sans le retrait
			} catch (DALException e1) {
				request.setAttribute("msgErreur","C'est la merde, appelez un admin.");
			}
			
			getServletContext().getNamedDispatcher("NewArticleJSP").forward(request, response);
		} catch (BLLException e) {
			request.setAttribute("msgErreur", e.getMessage());
			getServletContext().getNamedDispatcher("NewArticleJSP").forward(request, response);
		}
	}
}