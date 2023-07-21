package DAL;

import java.util.List;

import BO.User;
import Exceptions.DALException;

public interface UserDAO {

	public List<User> selectAll() throws DALException;

	public User selectByID(int id) throws DALException;

	public void update(User t) throws DALException;

	public void delete(int id) throws DALException;

	public void insert(User t) throws DALException;
	
	// Méthodes spécifiques à User
	
	public int getId(String pseudo) throws DALException;
	
	public boolean checkPseudo(String pseudo) throws DALException; // vérifie la présence dans la bdd
	public boolean checkEmail(String email) throws DALException; // vérifie la présence dans la bdd
	
	public boolean checkMdp(String pseudo, String mdp) throws DALException; // vérifie la présence du couple dans la bdd
}