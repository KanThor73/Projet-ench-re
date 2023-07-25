package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;

import BO.Auction;
import Exceptions.DALException;

public class AuctionDAOimplJDBC implements AuctionDAO {
	
	public static final String AUCTION_SQL_SELECTALL = "SELECT * FROM Encheres";
	public static final String AUCTION_SQL_SELECTBYUSER = "SELECT * FROM Encheres WHERE no_user = ?";
	public static final String AUCTION_SQL_SELECTBYARTICLE = "SELECT * FROM Encheres WHERE no_article = ?";
	public static final String AUCTION_SQL_SELECTBYID = "SELECT * FROM Encheres WHERE no_user = ? AND no_article = ?";
	public static final String AUCTION_SQL_UPDATE = "UPDATE Encheres SET date_enchere = ?, montant_enchere = ? WHERE no_user = ? AND no_article = ?";
	public static final String AUCTION_SQL_DELETEBYUSER = "DELETE FROM Encheres WHERE no_user = ?";
	public static final String AUCTION_SQL_DELETEBYARTICLE = "DELETE FROM Encheres where no_article = ?";
	public static final String AUCTION_SQL_DELETE = "DELETE FROM Encheres WHERE no_user = ? AND no_article = ?";
	public static final String AUCTION_SQL_INSERT = "INSERT INTO Encheres (no_user, no_article, date_enchere, montant_enchere) VALUES (?, ?, ?, ?)";

	@Override
	public List<Auction> selectAll() throws DALException {
		  try (Connection cnx = ConnectionProvider.getConnection()) {
	            List<Auction> auctions = new ArrayList<>();
	            PreparedStatement stmt = cnx.prepareStatement(AUCTION_SQL_SELECTALL);
	            ResultSet rs = stmt.executeQuery();
	          	return null;
		  }
	}
	       
	            
	            
	        
	    

	@Override
	public Auction selectByID(int id) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement stmt = cnx.prepareStatement(AUCTION_SQL_SELECTBYID);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
		
	}
	}

	@Override
	public void update(Auction t) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement stmt = cnx.prepareStatement(AUCTION_SQL_UPDATE);
		}
	}

	@Override
	public void delete(int id) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement stmt = cnx.prepareStatement(AUCTION_SQL_DELETE);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		}

	}

	@Override
	public void insert(Auction t) throws DALException {
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(AUCTION_SQL_INSERT);


	}

	}
}
