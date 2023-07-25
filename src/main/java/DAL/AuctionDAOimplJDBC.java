package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;

import BO.Article;
import BO.Auction;
import Exceptions.BLLException;
import Exceptions.DALException;

public class AuctionDAOimplJDBC implements AuctionDAO {
	
	public static final String AUCTION_SQL_SELECTALL = "SELECT * FROM Auctions";
	public static final String AUCTION_SQL_SELECTBYID = "SELECT * FROM Auctions WHERE id = ?";
	public static final String AUCTION_SQL_UPDATE = "UPDATE Auctions SET name = ?, description = ?, date_debut = ?, date_fin = ?, prix_initial = ?, prix_vente = ?, id_utilisateur = ?, gagnant = ? WHERE id = ?";
	public static final String AUCTION_SQL_DELETE = "DELETE FROM Auctions WHERE id = ?";
	public static final String AUCTION_SQL_INSERT = "INSERT INTO Auctions (name, description, date_debut, date_fin, prix_initial, prix_vente, id_utilisateur, gagnant) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	@Override
	public List<Auction> selectAll() throws DALException {
		  try (Connection cnx = ConnectionProvider.getConnection()) {
	            List<Auction> auctions = new ArrayList<>();
	            PreparedStatement stmt = cnx.prepareStatement(AUCTION_SQL_SELECTALL);
	            ResultSet rs = stmt.executeQuery();
		  } catch (Exception e) {
				e.printStackTrace();
				throw new DALException("problème de connexion aux données");
			}
		  return null;
		  }
	
	       
	            
	            
	        
	    

	@Override
	public Auction selectByID(int id) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement stmt = cnx.prepareStatement(AUCTION_SQL_SELECTBYID);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("problème de connexion aux données");
		}
		return null;
		
	}
	

	@Override
	public void update(Auction t) throws DALException, BLLException {
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement stmt = cnx.prepareStatement(AUCTION_SQL_UPDATE);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("problème de connexion aux données");
		}
		}
	

	@Override
	public void delete(int id) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement stmt = cnx.prepareStatement(AUCTION_SQL_DELETE);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("problème de connexion aux données");
		}
		}

	

	@Override
	public void insert(Auction t) throws DALException, BLLException {
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(AUCTION_SQL_INSERT);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("problème de connexion aux données");
		}

	}

	}

