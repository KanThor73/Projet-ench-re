package DAL;

import java.util.List;

import BO.User;

public interface UserDAO {

	public List<User> selectAll();

	public User selectByID(int id);

	public void update(User t);

	public void delete(int id);

	public void insert(User t);
	
	// Méthodes spécifiques à User
	
	public boolean checkPseudo(String pseudo); // vérifie la présence dans la bdd
	public boolean checkEmail(String email); // vérifie la présence dans la bdd
	
	public boolean checkMdp(String pseudo, String mdp); // vérifie la présence du couple dans la bdd
}