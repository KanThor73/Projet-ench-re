package BLL;

import java.util.List;

import BO.User;
import DAL.UserDAO;
import DAL.Factory;
import Exceptions.BLLException;

public class UserManager {

	public static UserManager instance;
	private static UserDAO userDAO = Factory.getUserDAO();

	private UserManager() {

	}

	// singleton

	public static UserManager getInstanceOf() {

		if (instance == null) {
			instance = new UserManager();
		}
		return instance;
	}
	
	// **************             Logique metier               ************************
	
	//ajouter un utilisateur
	
	public void insert(User user) throws BLLException {
		control(user);
		userDAO.insert(user);
	}
	
	//recuperer tous les utilisateurs
	
	public List<User> selectAll(){
		return userDAO.selectAll();
	}

	//recuperer un utilisateur par son ID
	
	public User selectByID(int id){
		return userDAO.selectByID(id);
	}
	
	
	/*
	 * Controls
	 */
	
	public boolean checkPseudo(String pseudo) {
		return userDAO.checkPseudo(pseudo);
	}
	
	public boolean checkEmail(String email) {
		return userDAO.checkEmail(email);
	}
	
	public boolean checkMdp(String pseudo, String mdp) {
		return userDAO.checkMdp(pseudo, mdp);
	}
	
	private static void control(User user) throws BLLException {
		if (user == null) {
			throw new BLLException("L'utilisateur ne peut pas etre null");
			
		} else if (user.getPseudo() == null | user.getPseudo().isEmpty()) {
			throw new BLLException("Le pseudo doit obligatoirement etre renseigne");
			
		} else if (user.getNom() == null | user.getNom().isEmpty()) {
			throw new BLLException("Le nom doit obligatoirement etre renseigne");
			
		}else if (user.getPrenom() == null | user.getPrenom().isEmpty()) {
			throw new BLLException("Le prenom doit obligatoirement etre renseigne");
			
		}else if (user.getEmail() == null | user.getEmail().isEmpty()) {
			throw new BLLException("L'email doit obligatoirement etre renseigne");
			
		}else if (user.getTelephone() == null | user.getTelephone().isEmpty()) {
			throw new BLLException("Le telephone doit obligatoirement etre renseigne");
			
		}else if (user.getRue() == null | user.getRue().isEmpty()) {
			throw new BLLException("La rue doit obligatoirement etre renseignee");
			
		}else if (user.getCodePostal() == null | user.getCodePostal().isEmpty()) {
			throw new BLLException("Le code postal doit obligatoirement etre renseigne");
			
		}else if (user.getVille() == null | user.getVille().isEmpty()) {
			throw new BLLException("La ville doit obligatoirement etre renseignee");
			
		}else if (user.getMotDePasse() == null | user.getMotDePasse().isEmpty()) {
			throw new BLLException("Le mot de passe doit obligatoirement etre renseigne");
		}
	}
}
