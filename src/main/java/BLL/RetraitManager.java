package BLL;

import BO.Retrait;
import DAL.Factory;
import DAL.RetraitDAO;
import Exceptions.BLLException;
import Exceptions.DALException;

public class RetraitManager {

	private RetraitDAO retraitDAO = Factory.getRetraitDAO();
	/*********************
	 * Pattern singleton *
	 *********************/

	private static RetraitManager instance;

	private RetraitManager() {

	}

	public static RetraitManager getInstanceOf() {

		if (instance == null) {
			instance = new RetraitManager();
		}
		return instance;
	}

	/******************
	 * Logique métier *
	 ******************/

	// ajouter un retrait
	public void insert(Retrait retrait) throws BLLException, DALException {

		control(retrait);
		retraitDAO.insert(retrait);
	}

	// supprimer un retrait
	public void delete(int id) throws DALException {
		retraitDAO.delete(id);
	}
	
	// récupérer un retrait par l'id de l'article
	public Retrait selectByID(int id) throws DALException {
		return retraitDAO.selectByIdArticle(id);
	}

	/*************
	 * CONTROLES *
	 *************/

	private static void control(Retrait retrait) throws BLLException {

		if (retrait == null) {
			throw new BLLException("utilisateur");
		} else if (retrait.getRue() == null || retrait.getRue().isEmpty()) {
			throw new BLLException("saisie incorrecte de la rue");
		} else if (retrait.getCode_postal() == null || retrait.getCode_postal().isEmpty()
				|| !(retrait.getCode_postal().length() == 5)) {// verifier qu'il s'agit bien d'un code postal
			throw new BLLException("saisie incorrecte du code postal");
		} else if (retrait.getVille() == null || retrait.getVille().isEmpty()) {
			throw new BLLException("saisie incorrecte de la ville");
		}
	}
}
