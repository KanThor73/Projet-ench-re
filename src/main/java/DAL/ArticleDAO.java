package DAL;

import java.util.List;

import BO.Article;
import Exceptions.DALException;

public interface ArticleDAO {

	public List<Article> selectAll() throws DALException;
	public Article selectByID(int id) throws DALException;
	public void update(Article t) throws DALException;
	public void delete(int id) throws DALException;
	public void insert(Article t) throws DALException;
	
	// Méthodes spécifiques à Article
	
	public List<Article> selectByCategory(String cat) throws DALException;
	
	public int getNextNoArticle() throws DALException;// recup prochain no pour ajoute de retrait
	
	public List<Article> selectDynamic(String requete, String[] parameters) throws DALException ; // recup la liste des article adapte aux champs saisis
	
	public String getPseudoByNoUser(int noUser) throws DALException; // recupere le pseudo
}