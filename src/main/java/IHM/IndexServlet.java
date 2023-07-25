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
		request.setCharacterEncoding("UTF-8");

		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");// gere les caracteres avec accents
		String searchString = request.getParameter("recherche");
		String catForSelectArt = request.getParameter("categorie");// recuperation libelle

		try {
			String cat = String.valueOf(catMgr.selectNoByCAT(request.getParameter("categorie")));
			System.out.println(cat);
			System.out.println(searchString);
			if (catForSelectArt.equals("aucune") && searchString.equals("") || searchString == null) {
				System.out.println("if1");
				List<Article> listeArticles = articleMgr.selectAll();// fonctionne avec le libelle
				request.setAttribute("articles", listeArticles);
			}
			else if (!searchString.equals("") && searchString != null && !cat.equals("") && cat != null) { // recherche par
																										// categorie et
																										// mot clef
				String requete = "SELECT no_article, nom_article, description, libelle as categorie, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_user FROM ArticlesVendus JOIN Categories WHERE ArticlesVendus.no_categorie = Categories.no_categorie AND ArticlesVendus.no_categorie = ? AND nom_article LIKE CONCAT('%', ?, '%');";
				List<Article> listeArticles = articleMgr.selectDynamic(requete, new String[] { cat, searchString });
				request.setAttribute("articles", listeArticles);
			} else if (searchString.equals("") || searchString == null && !cat.equals("") && cat != null) { // recherche
																											// par
																											// categorie
																											// uniquement

				List<Article> listeArticles = articleMgr.selectByCategory(catForSelectArt);// fonctionne avec le libelle
				request.setAttribute("articles", listeArticles);
			} else if (cat.equals("") || cat == null && !searchString.equals("") && searchString != null && catMgr.check(catForSelectArt)) {// recherche
																											// par mot
																											// clefs
																											// uniquement
				System.out.println("if 3");
				String requete = "SELECT no_article, nom_article, description, libelle as categorie, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_user FROM ArticlesVendus JOIN Categories WHERE ArticlesVendus.no_categorie = Categories.no_categorie AND nom_article LIKE CONCAT('%', ?, '%');";
				List<Article> listeArticles = articleMgr.selectDynamic(requete, new String[] { cat, searchString });
				request.setAttribute("articles", listeArticles);
			} else if (searchString.equals("") || searchString == null && cat.equals("") || cat == null) {// pas de
																											// filtres
				System.out.println("if 4");
				List<Article> listeArticles = articleMgr.selectAll();
				request.setAttribute("articles", listeArticles);
			}
		} catch (DALException e) {
			e.printStackTrace();
			request.setAttribute("msgErreur", "Mot de passe incorrect");
		}
		doGet(request, response);
	}
}