package DAL;

import BO.Article;

public class Factory {

	public static UserDAO getUserDAO() {
		return new UsersDAOimplJDBC();
	}
	
	public static ArticleDAO getArticleDAO() {
		return new ArticleDAOimplJDBC();
	}
	
	public static CategorieDAO getCategorieDAO() {
		return new CategorieDAOimplJDBC();
	}
}
