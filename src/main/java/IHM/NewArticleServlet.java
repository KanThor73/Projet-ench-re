package IHM;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;
import java.util.Locale;

import BO.Article;
import Exceptions.BLLException;
import Exceptions.DALException;
import BLL.ArticleManager;

public class NewArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleManager articleMgr = ArticleManager.getInstanceOf();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getNamedDispatcher("NewArticleJSP").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// Récupération des paramètres du formulaire de newAccount.jsp
			String nom = request.getParameter("nomArticle").toUpperCase();// plus classe en maj dans le css
			String description = request.getParameter("description");
			String categorie = request.getParameter("categorie");
			int prixInit = Integer.parseInt(request.getParameter("prixInit"));
		
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
			Date dateDebut = formatter.parse(request.getParameter("dateDebut"));
			Date dateFin = formatter.parse(request.getParameter("dateFin"));
			
			int id = Integer.parseInt(request.getSession().getAttribute("id").toString()); // récupération de l'id du user connecté
			
			Article newArticle = new Article(nom, description, categorie, dateDebut, dateFin, prixInit, id);
						
			articleMgr.insert(newArticle); // ajout de l'utilisateur
			getServletContext().getNamedDispatcher("Index").forward(request, response); // retourne à l'accueil si bon déroulement
			
			// TODO traiter rue, code postal, et ville
			/*String rue = request.getParameter("rue");
			String codePostal = request.getParameter("codePostal");
			String ville = request.getParameter("ville");*/
			
		} catch (ParseException e) {
			request.setAttribute("msgErreur","Problème de formattage des dates");
			getServletContext().getNamedDispatcher("NewArticleJSP").forward(request, response);
		} catch (DALException e) {
			request.setAttribute("msgErreur","Problème d'accès aux données");
			getServletContext().getNamedDispatcher("NewArticleJSP").forward(request, response);
		} catch (BLLException e) {
			request.setAttribute("msgErreur", e.getMessage());
			getServletContext().getNamedDispatcher("NewArticleJSP").forward(request, response);
		}
	}
}
