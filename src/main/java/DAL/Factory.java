package DAL;

public class Factory {

	public static UserDAO getUserDAO(){
		return new UsersDAOimplJDBC();
	}
}
