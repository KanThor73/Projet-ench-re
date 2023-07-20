package BLL;

import java.util.List;

import BO.Article;
import DAL.DAO;
import DAL.Factory;

public class ArticleManager {

	private static DAO<Article> articleDAO = Factory.getArticleDAO();

	/*********************
	 * Pattern singleton *
	 *********************/
	
	public static ArticleManager instance;
	
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
	public void insert(Article article) {
		
		control(article); // vérifie que les éléments soient en adéquation avec la bdd
		
		articleDAO.insert(article); // ajoute l'utilisateur à la bdd TODO pas d'exception à try ?
	}
	
	// modifier un article
	public void update(Article article) {
		articleDAO.update(article);
	}
	
	// récupérer tous les articles
	public List<Article> selectAll(){
		return articleDAO.selectAll();
	}

	// récupérer un article par son ID
	public Article selectByID(int id){
		return articleDAO.selectByID(id);
	}
	
	/*************
	 * Controles *
	 *************/
	
	private void control(Article article) {
		
	}
}