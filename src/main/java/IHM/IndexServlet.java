package IHM;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BLL.ArticleManager;
import BLL.CategorieManager;
import BO.Article;
import Exceptions.DALException;

@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArticleManager articleMgr = ArticleManager.getInstanceOf();
	CategorieManager catMgr = CategorieManager.getInstanceOf();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			List<String> categories = catMgr.selectAll();
			request.setAttribute("categories", categories);
			List<Article> listeArticles = articleMgr.selectAll();// recupere tous les articles pour le premier affichage

			request.setAttribute("articles", listeArticles);
			if(listeArticles.size() == 0 || listeArticles == null) {request.setAttribute("msg", "Aucun element ne correspond a vos criteres de recherche");}
		} catch (DALException e) {
			e.printStackTrace();
		}
		request.setCharacterEncoding("UTF-8");

		request.getRequestDispatcher("WEB-INF/JSP/indexMain.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");// gere les caracteres avec accents
		String searchString = request.getParameter("recherche").trim(); //retire les espace en trop
		String catForSelectArt = request.getParameter("categorie");// recuperation libelle

		try {
			List<String> categories = catMgr.selectAll();
			request.setAttribute("categories", categories);
			String cat = (!request.getParameter("categorie").equals("toutes")) ?
						String.valueOf(catMgr.selectNoByCAT(request.getParameter("categorie"))) :
						"";
			
			if (catForSelectArt.equals("toutes") && (searchString == null || searchString.equals(""))) {
				List<Article> listeArticles = articleMgr.selectAll();// fonctionne avec le libelle
				request.setAttribute("articles", listeArticles);
				if(listeArticles == null || listeArticles.size() == 0) {
					request.setAttribute("msg", "Aucun élément ne correspond a vos criteres de recherche");
				}
			} else if (searchString != null && !searchString.equals("") && !cat.equals("") && cat != null && catMgr.check(catForSelectArt)) { // recherche par categorie et mot clef

				String requete = "SELECT no_article, nom_article, description, libelle as categorie, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_user FROM ArticlesVendus JOIN Categories WHERE ArticlesVendus.no_categorie = Categories.no_categorie AND ArticlesVendus.no_categorie = ? AND nom_article LIKE CONCAT('%', ?, '%');";
				List<Article> listeArticles = articleMgr.selectDynamic(requete, new String[] { cat, searchString });
				request.setAttribute("articles", listeArticles);
				if(listeArticles == null || listeArticles.size() == 0) {request.setAttribute("msg", "Aucun element ne correspond a vos criteres de recherche");}
				
			} else if (searchString == null || searchString.equals("") && cat != null && !cat.equals("")) { // recherche par categorie uniquement

				List<Article> listeArticles = articleMgr.selectByCategory(catForSelectArt);// fonctionne avec le libelle
				request.setAttribute("articles", listeArticles);
				if(listeArticles == null || listeArticles.size() == 0) {request.setAttribute("msg", "Aucun element ne correspond a vos criteres de recherche");}
				
			} else if (searchString != null && !searchString.equals("") && !catMgr.check(catForSelectArt)) {// recherche par mot clefs uniquement

				String requete = "SELECT no_article, nom_article, description, libelle as categorie, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_user FROM ArticlesVendus JOIN Categories WHERE ArticlesVendus.no_categorie = Categories.no_categorie AND nom_article LIKE CONCAT('%', ?, '%');";
				List<Article> listeArticles = articleMgr.selectDynamic(requete, new String[] {searchString});
				request.setAttribute("articles", listeArticles);
				if(listeArticles == null || listeArticles.size() == 0) {request.setAttribute("msg", "Aucun element ne correspond a vos criteres de recherche");}
			}
			
		} catch (DALException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			request.setAttribute("msgErreur", "Impossible de satisfaire votre requête");
		}
		request.getRequestDispatcher("WEB-INF/JSP/indexMain.jsp").forward(request, response);
	}
}