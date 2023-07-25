package DAL;

import java.util.List;

import BO.Auction;
import Exceptions.DALException;

public interface AuctionDAO {
	
	public List<Auction> selectAll() throws DALException;
	public Auction selectByUser(int id) throws DALException;
	public Auction selectByArticle(int id) throws DALException;
	public Auction selectByID(int idUser, int idArticle) throws DALException;
	public void update(Auction t) throws DALException;
	public void deleteByUser(int id) throws DALException;
	public void deleteByArticle(int id) throws DALException;
	public void delete(int idUser, int idArticle) throws DALException;
	public void insert(Auction t) throws DALException;
}
