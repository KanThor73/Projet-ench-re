package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

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
			
			
			while (rs.next()) {
				int idUser = rs.getInt("no_user");
				int idArticle = rs.getInt("no_article");
				Date date = rs.getDate("date_enchere");
				int montant = rs.getInt("montant_enchere");
				
				Auction auction = new Auction(idUser, idArticle, date, montant);
				auctions.add(auction);
			}
			
			return auctions;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("Problème de connexion aux données");
		}
	}

	@Override
	public void update(Auction t) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			

			PreparedStatement stmt = cnx.prepareStatement(AUCTION_SQL_UPDATE);
			
			stmt.setDate(1, new java.sql.Date(t.getDateEnchere().getTime()));
			stmt.setInt(2, t.getMontantEnchere());
			stmt.setInt(3, t.getNoUtilisateur());
			stmt.setInt(4, t.getNoArticle());
			
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("Problème de connexion aux données");
		}
	}

	@Override
	public void insert(Auction t) throws DALException {
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(AUCTION_SQL_INSERT);

		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("Problème de connexion aux données");
		}

	}

	@Override
	public Auction selectByUser(int id) throws DALException {
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(AUCTION_SQL_SELECTBYUSER);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) { // il y a une ligne dans le ResultSet
				int idUser = rs.getInt("no_user");
				int idArticle = rs.getInt("no_article");
				Date date = rs.getDate("date_enchere");
				int montant = rs.getInt("montant_enchere");
				
				return new Auction(idUser, idArticle, date, montant); // on retourne une nouvelle instance
				
			} else { // on ne trouve rien
				throw new DALException("Enchère ou article inexistant");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("Problème de connexion aux données");
		}
	}

	@Override
	public Auction selectByArticle(int id) throws DALException {
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(AUCTION_SQL_SELECTBYARTICLE);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				
				int idUser = rs.getInt("no_user");
				int idArticle = rs.getInt("no_article");
				Date date = rs.getDate("date_enchere");
				int montant = rs.getInt("montant_enchere");
				
				Auction auction = new Auction(idUser, idArticle, date, montant);
				
				return auction;
			}else {
				throw new DALException("Problème de connexion aux données");
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("Problème de connexion aux données");
		}
	}

	@Override
	public Auction selectByID(int idUser, int idArticle) throws DALException {
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(AUCTION_SQL_INSERT);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("Problème de connexion aux données");
		}
	}

	@Override
	public void deleteByUser(int id) throws DALException {
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(AUCTION_SQL_INSERT);

		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("Problème de connexion aux données");
		}

	}

	@Override
	public void deleteByArticle(int id) throws DALException {
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(AUCTION_SQL_INSERT);

		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("Problème de connexion aux données");
		}

	}

	@Override
	public void delete(int idUser, int idArticle) throws DALException {
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(AUCTION_SQL_INSERT);

		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("Problème de connexion aux données");
		}
	}
}