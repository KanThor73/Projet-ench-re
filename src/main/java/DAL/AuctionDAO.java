package DAL;

import java.util.List;

import BO.Auction;
import Exceptions.DALException;
import Exceptions.BLLException;

public interface AuctionDAO {
	
	public List<Auction> selectAll() throws DALException;
	public Auction selectByID(int id) throws DALException;
	public void update(Auction t) throws DALException, BLLException;
	public void delete(int id) throws DALException;
	public void insert(Auction t) throws DALException, BLLException;
}
