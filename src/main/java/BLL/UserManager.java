package BLL;

import java.util.List;

import BO.User;
import DAL.DAO;
import DAL.Factory;

public class UserManager {

		public static String test() {
			DAO<User> userDAO = Factory.getUserDAO();
			List<User> userList = userDAO.selectAll();
			for (User user : userList) {
				return user.toString();
			}
			return "";
		}
}
