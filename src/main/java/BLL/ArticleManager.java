package BLL;

import java.util.List;

import BO.Article;
import DAL.DAO;
import DAL.Factory;
import Exceptions.BLLException;
import Exceptions.DALException;

public class ArticleManager {

	private DAO<Article> articleDAO = Factory.getArticleDAO();

	/*********************
	 * Pattern singleton *
	 *********************/
	
	private static ArticleManager instance;
	
	private ArticleManager() {

	}

	public static ArticleManager getInstanceOf() {

		if (instance == null) {
			instance = new ArticleManager();
		}
		return instance;
	}
	
	/******************
	 * Logique métier *
	 ******************/
	
	// ajouter un article
	public void insert(Article article) throws BLLException, DALException {
		
		control(article); // vérifie que les éléments soient en adéquation avec la bdd
		articleDAO.insert(article);
	}
	
	// modifier un article
	public void update(Article article) throws BLLException, DALException {
		
		control(article); // vérifie que les éléments soient en adéquation avec la bdd
		articleDAO.update(article);
	}
	
	// récupérer tous les articles
	public List<Article> selectAll() throws DALException {
		return articleDAO.selectAll();
	}

	// récupérer un article par son ID
	public Article selectByID(int id) throws DALException {
		return articleDAO.selectByID(id);
	}
	
	/*************
	 * Controles *
	 *************/
	
	private void control(Article article) throws BLLException, DALException {
		
		UserManager userMgr = UserManager.getInstanceOf();
		CategorieManager catMgr = CategorieManager.getInstanceOf();
		
		if (article == null) {
			throw new BLLException("article");
		} else if (article.getNom() == null || article.getNom().isEmpty() || article.getNom().length() > 30) {
			throw new BLLException("saisie incorrecte du nom");
		} else if(article.getDescription() == null || article.getDescription().length() > 300) {
			throw new BLLException("saisie incorrecte de la description");
		} else if (article.getDateDebut().after(article.getDateFin())) {
			throw new BLLException("dates incompatibles");
		} else if (article.getPrixInit() != null && article.getPrixVente() != null && (article.getPrixInit().compareTo(article.getPrixVente()) > 0)) {
			throw new BLLException("prix incompatibles");
		} else if (userMgr.selectByID(article.getOwnerId()) == null) { // potentielle DALException
			throw new BLLException("utilisateur inexistant");
		} else if(catMgr.check(article.getCategorie())) { // potentielle DALException
			throw new BLLException("catégorie inexistante");
		}
	}
}