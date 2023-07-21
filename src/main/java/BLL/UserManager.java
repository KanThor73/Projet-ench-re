package BLL;

import java.util.List;

import BO.User;
import DAL.UserDAO;
import DAL.Factory;
import Exceptions.BLLException;
import Exceptions.DALException;

public class UserManager {

	private UserDAO userDAO = Factory.getUserDAO();

	/*********************
	 * Pattern singleton *
	 *********************/

	private static UserManager instance;

	private UserManager() {

	}

	public static UserManager getInstanceOf() {

		if (instance == null) {
			instance = new UserManager();
		}
		return instance;
	}

	/******************
	 * Logique métier *
	 ******************/

	// ajouter un utilisateur
	public void insert(User user) throws BLLException, DALException {

		control(user); // vérifie que les éléments soient en adéquation avec la bdd

		// vérifie que le pseudo et l'email ne soient pas déjà dans la bdd
		if (checkPseudo(user.getPseudo())) {
			throw new BLLException("pseudo déjà utilisé");
		}
		if (checkEmail(user.getEmail())) {
			throw new BLLException("email déjà utilisé");
		}

		userDAO.insert(user); // ajoute l'utilisateur à la bdd TODO pas d'exception à try ?
	}

	// modifier un utilisateur
	public void update(User user) throws DALException {
		userDAO.update(user);
	}

	// récupérer tous les utilisateurs
	public List<User> selectAll() throws DALException {
		return userDAO.selectAll();
	}

	// récupérer un utilisateur par son ID
	public User selectByID(int id) throws DALException {
		return userDAO.selectByID(id);
	}
	
	
	// récupérer un utilisateur par son pseudo
	public User selectByPseudo(String pseudo) throws DALException {
		return userDAO.selectByPseudo(pseudo);
	}

	// récupérer un ID depuis un pseudo (pour connexion par exemple)
	public int getId(String pseudo) throws DALException {
		return userDAO.getId(pseudo);
	}

	/*************
	 * CONTROLES *
	 *************/

	public boolean checkMdp(String pseudo, String mdp) throws DALException { // le mdp est-il correct ?
		return userDAO.checkMdp(pseudo, mdp);
	}

	public boolean checkPseudo(String pseudo) throws DALException { // le pseudo est-il présent dans la bdd ?
		return userDAO.checkPseudo(pseudo);
	}

	private boolean checkEmail(String email) throws DALException { // l'email est-il présent dans la bdd ?
		return userDAO.checkEmail(email);
	}

	private static void control(User user) throws BLLException {

		if (user == null) {
			throw new BLLException("utilisateur");
		} else if (user.getPseudo() == null || user.getPseudo().isEmpty() || user.getPseudo().length() > 30) {
			throw new BLLException("saisie incorrecte du pseudo");
		} else if (user.getNom() == null || user.getNom().isEmpty() || user.getNom().length() > 30) {
			throw new BLLException("saisie incorrecte du nom");
		} else if (user.getPrenom() == null || user.getPrenom().isEmpty() || user.getPrenom().length() > 30) {
			throw new BLLException("saisie incorrecte du prénom");
		} else if (user.getEmail() == null || user.getEmail().isEmpty() || user.getEmail().length() > 50) {
			throw new BLLException("saisie incorrecte de l'email");
		} else if (user.getTelephone() != null
				&& (user.getTelephone().isEmpty() || user.getTelephone().length() > 15)) {
			// le telephone peut être null
			throw new BLLException("saisie incorrecte du numéro de téléphone");
		} else if (user.getRue() == null || user.getRue().isEmpty() || user.getRue().length() > 30) {
			throw new BLLException("saisie incorrecte de la rue");
		} else if (user.getCodePostal() == null || user.getCodePostal().isEmpty()
				|| user.getCodePostal().length() > 10) {
			throw new BLLException("saisie incorrecte du code postal");
		} else if (user.getVille() == null || user.getVille().isEmpty() || user.getVille().length() > 30) {
			throw new BLLException("saisie incorrecte de la ville");
		} else if (user.getMotDePasse() == null || user.getMotDePasse().isEmpty()
				|| user.getMotDePasse().length() > 40) {
			throw new BLLException("saisie incorrecte du mot de passe");
		}
	}
}
