package BLL;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import BO.Article;
import DAL.ArticleDAO;
import DAL.Factory;
import Exceptions.BLLException;
import Exceptions.DALException;

public class ArticleManager {

	private ArticleDAO articleDAO = Factory.getArticleDAO();

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
		
		// controle spécifique à la création
		if (article.getDateDebut().before(Date.from(Instant.now()))) {
			throw new BLLException("Date incorrecte");
		}
		
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
	
	// récupérer tous les articles d'une catégorie
	public List<Article> selectByCategory(String cat) throws DALException {
		return articleDAO.selectByCategory(cat);
	}
	
	// récupérer tous les articles d'une catégorie
	public List<Article> selectDynamic(String requete, String[] parameters) throws DALException {
		return articleDAO.selectDynamic(requete, parameters);
	}
	
	// supprimer un article de la bdd depuis son id
	public void delete(int id) throws DALException {
		articleDAO.delete(id);
	}
	
	// récupérer l'id du prochain article 
	public int getMaxNoArticle() throws DALException {
		return articleDAO.getMaxNoArticle();
	}
	
	public String getPseudoByNoUser(int noUser) throws DALException{
		return articleDAO.getPseudoByNoUser(noUser);
	};
	
	/*************
	 * Controles *
	 *************/
	
	private void control(Article article) throws BLLException, DALException {
		
		UserManager userMgr = UserManager.getInstanceOf();
		CategorieManager catMgr = CategorieManager.getInstanceOf();
		
		if (article == null) {
			throw new BLLException("article");
		} else if (article.getNom() == null || article.getNom().isEmpty() || article.getNom().length() > 30) {
			throw new BLLException("Saisie incorrecte du nom");
		} else if(article.getDescription() == null || article.getDescription().length() > 300) {
			throw new BLLException("Saisie incorrecte de la description");
		} else if (article.getDateDebut().after(article.getDateFin())) {
			throw new BLLException("Dates incompatibles");
		} else if (article.getPrixInit() != null && article.getPrixVente() != null && (article.getPrixInit().compareTo(article.getPrixVente()) > 0)) {
			throw new BLLException("Prix incompatibles");
		} else if (userMgr.selectByID(article.getOwnerId()) == null) { // potentielle DALException
			throw new BLLException("Utilisateur inexistant");
		} else if(!catMgr.check(article.getCategorie())) { // potentielle DALException
			throw new BLLException("Catégorie inexistante");
		}
	}
}