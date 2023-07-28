package BLL;

import java.util.Collections;
import java.util.List;

import DAL.AuctionDAO;
import DAL.Factory;
import Exceptions.DALException;
import Exceptions.BLLException;
import BO.Auction;

import BO.Article; // pour comparer les dates de l'enchere et de l'article

public class AuctionManager {
	
	private AuctionDAO auctionDAO = Factory.getAuctionDAO();
	
	private ArticleManager articleMgr = ArticleManager.getInstanceOf(); // pour comparer les dates de l'enchere et de l'article
	
	/*********************
	 * Pattern singleton *
	 *********************/
	
	private static AuctionManager instance;
	
	private AuctionManager() {

	}

	public static AuctionManager getInstanceOf() {

		if (instance == null) {
			instance = new AuctionManager();
		}
		return instance;
	}
	
	/******************
	 * Logique métier *
	 ******************/
	
	public void insert(Auction auct) throws DALException, BLLException {
		control(auct);
		auctionDAO.insert(auct);
	}
	
	public void update(Auction auct) throws DALException, BLLException {
		control(auct);
		auctionDAO.update(auct);
	}

	public void delete(int idUser, int idArticle) throws DALException {
		auctionDAO.delete(idUser, idArticle);
	}
	
	public void deleteByUser(int idUser) throws DALException {
		auctionDAO.deleteByUser(idUser);
	}
	
	public void deleteByArticle(int idArticle) throws DALException {
		auctionDAO.deleteByArticle(idArticle);
	}
	
	public List<Auction> selectAll() throws DALException {
		return auctionDAO.selectAll();
	}
	
	public List<Auction> selectByUser(int idUser) throws DALException {
		return auctionDAO.selectByUser(idUser);
	}
	
	public List<Auction> selectByArticle(int idArticle) throws DALException {
		return auctionDAO.selectByArticle(idArticle);
	}
	
	public Auction selectByID(int idUser, int idArticle) throws DALException {
		return auctionDAO.selectByID(idUser, idArticle);
	}
	
	/********************
	 * Méthodes privées *
	 ********************/
	
	private void control(Auction auct) throws BLLException, DALException {

		// vérification de l'article
		Article art = articleMgr.selectByID(auct.getNoArticle()); // potentielle DALException
		
		// vérification de l'utilisateur
		if (auct.getNoUtilisateur() == art.getOwnerId()) {
			throw new BLLException("Impossible d'enchérir sur ses propres biens");
		}
		List<Auction> auctions = selectByArticle(auct.getNoArticle());
		Auction bestOffer = auctions.isEmpty() ? null : Collections.max(auctions);
		if (bestOffer != null && auct.getNoUtilisateur() == bestOffer.getNoUtilisateur()) {
			throw new BLLException("Impossible d'augmenter sa propre enchère");
		}
		
		// vérification du montant
				if (bestOffer != null && auct.getMontantEnchere() < bestOffer.getMontantEnchere()) {
					throw new BLLException("Montant non valide");
				}
		
		// vérification de la date
		if (auct.getDateEnchere().before(art.getDateDebut()) || auct.getDateEnchere().after(art.getDateFin())) {
			throw new BLLException("Les enchères ne sont pas en cours");
		}
	}
}
