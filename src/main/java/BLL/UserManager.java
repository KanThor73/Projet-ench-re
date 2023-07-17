package BLL;

import java.util.List;

import BO.User;
import DAL.DAO;
import DAL.Factory;

public class UserManager {

		public static void main (String []args) {
			DAO userDAO = Factory.getUserDAO();
			List<User> userList = userDAO.selectAll();
			for (User user : userList) {
				System.out.println(user.toString());
			}
		}
}
