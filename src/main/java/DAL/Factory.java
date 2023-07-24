package DAL;

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

	public static RetraitDAO getRetraitDAO() {
		return new RetraitDAOimplJDBC();
	}
}
