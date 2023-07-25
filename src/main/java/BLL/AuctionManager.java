package BLL;

import java.util.List;

import DAL.AuctionDAO;
import DAL.Factory;
import Exceptions.DALException;
import Exceptions.BLLException;
import BO.Auction;

public class AuctionManager {
	
	private AuctionDAO auctionDAO = Factory.getAuctionDAO();

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
	
	public Auction selectByUser(int idUser) throws DALException {
		return auctionDAO.selectByUser(idUser);
	}
	
	public Auction selectByArticle(int idArticle) throws DALException {
		return auctionDAO.selectByArticle(idArticle);
	}
	
	/********************
	 * Méthodes privées *
	 ********************/
	
	private void control(Auction auct) throws BLLException {
		// TODO
	}
}
