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
			String nom = request.getParameter("nomArticle").toUpperCase();// plus classe en maj dans le css
			String description = request.getParameter("description");
			String categorie = request.getParameter("categorie");
			int prixInit = Integer.parseInt(request.getParameter("prixInit"));
		
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
			Date dateDebut = formatter.parse(request.getParameter("dateDebut"));
			Date dateFin = formatter.parse(request.getParameter("dateFin"));
			
			int id = Integer.parseInt(request.getSession().getAttribute("id").toString()); // récupération de l'id du user connecté
			System.out.println(categorie);
			Article newArticle = new Article(nom, description, categorie, dateDebut, dateFin, prixInit, id);
						
			articleMgr.insert(newArticle); // ajout de l'utilisateur
			
			// creation du retrait
			String rue = request.getParameter("rue");
			String codePostal = request.getParameter("codePostal");
			String ville = request.getParameter("ville");
			
			System.out.println(articleMgr.getNextNoArticle());
			
			Retrait retrait = new Retrait(articleMgr.getNextNoArticle(),rue,codePostal,ville); //creation d'un nouveau retrait
			retraitManager.insert(retrait);
			
			response.sendRedirect("IndexServlet");// retourne à l'accueil si bon déroulement
			
			
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
