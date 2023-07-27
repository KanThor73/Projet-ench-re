package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Exceptions.DALException;

public class CategorieDAOimplJDBC implements CategorieDAO {

	// declaration des constantes pour les requetes SQL
	
	public static final String CAT_SQL_INSERT = "INSERT INTO Categories(libelle) VALUES (?)";
	public static final String CAT_SQL_DELETE = "DELETE FROM Categories WHERE libelle = ?";
	public static final String CAT_SQL_SELECTALL = "SELECT libelle FROM Categories";
	public static final String CAT_SQL_SELECTBYLIBELLE = "SELECT count(*) AS cnt FROM Categories WHERE libelle = ?";
	public static final String CAT_SQL_SELECT_NO_CAT = "SELECT no_categorie FROM Categories WHERE libelle = ?;";
	
	@Override
	public void insert(String name) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			PreparedStatement stmt = cnx.prepareStatement(CAT_SQL_INSERT);
			stmt.setString(1, name);
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("problème de connexion aux données");
		}
	}

	@Override
	public void delete(String name) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			PreparedStatement stmt = cnx.prepareStatement(CAT_SQL_DELETE);
			stmt.setString(1, name);
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("problème de connexion aux données");
		}
	}

	@Override
	public List<String> selectAll() throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			PreparedStatement stmt = cnx.prepareStatement(CAT_SQL_SELECTALL);
			ResultSet rs = stmt.executeQuery();
			List<String> cats = new ArrayList<String>();
			
			while (rs.next()) {
				cats.add(new String(rs.getString("libelle")));
			}
			return cats;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("problème de connexion aux données");
		}
	}

	@Override
	public boolean check(String name) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			PreparedStatement stmt = cnx.prepareStatement(CAT_SQL_SELECTBYLIBELLE);
			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				return (rs.getInt("cnt") > 0); // vrai si au moins une catégorie porte ce nom
			} else {
				throw new DALException("problème de connexion aux données");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("problème de connexion aux données");
		}
	}
	
	@Override
	public int selectNoByCAT(String categorie) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement stmt = cnx.prepareStatement(CAT_SQL_SELECT_NO_CAT);
			stmt.setString(1, categorie);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				int noCategorie = rs.getInt("no_categorie");
				return noCategorie;
			} else {
				throw new DALException("catégorie inexistante");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("problème de connexion aux données");
		}
	}
}