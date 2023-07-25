package BLL;

import DAL.AuctionDAO;
import DAL.Factory;

public class AuctionManager {
	
	private AuctionDAO auctionDAO;
	
	
	public AuctionManager() {
		auctionDAO = Factory.getAuctionDAO();
	}
	
}
