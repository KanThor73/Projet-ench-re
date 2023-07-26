package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import BO.Retrait;
import Exceptions.DALException;

public class RetraitDAOimplJDBC implements RetraitDAO {
	public static final String RETRAIT_SQL_INSERT = "INSERT INTO Retraits (no_article,rue,code_postal,ville) VALUES (?,?,?,?)";
	public static final String RETRAIT_SQL_SELECTBYID = "SELECT * FROM Retraits WHERE no_article = ?";
	public static final String RETRAIT_SQL_DELETE = "DELETE FROM Retraits WHERE no_article = ?";

	// creation de l'udpate

	@Override
	public Retrait selectByIdArticle(int id) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement stmt = cnx.prepareStatement(RETRAIT_SQL_SELECTBYID);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new Retrait(rs.getInt("no_article"), rs.getString("rue"), rs.getString("code_postal"),
						rs.getString("ville"));
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("problème de connexion aux données");
		}
	}

	@Override
	public void insert(Retrait r) throws DALException {
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(RETRAIT_SQL_INSERT);

			stmt.setInt(1, r.getNo_article());
			stmt.setString(2, r.getRue());
			stmt.setString(3, r.getCode_postal());
			stmt.setString(4, r.getVille());

			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("problème de connexion aux données");
		}
	}

	@Override
	public void delete(int id) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement stmt = cnx.prepareStatement(RETRAIT_SQL_DELETE);
			stmt.setInt(1, id);
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("problème de connexion aux données");
		}

	}

}
