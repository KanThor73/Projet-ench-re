package DAL;

import BO.User;

public class Factory {

	
	public static DAO<User> getUserDAO(){
		return new UsersDAOimplJDBC();
	}
}
