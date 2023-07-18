package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import BO.User;

public class UsersDAOimplJDBC implements DAO<User> {
	
	// declaration des constantes pour les requetes SQL

	public static final String USER_SQL_INSERT = "INSERT INTO Users (pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	public static final String USER_SQL_UPDATE = "UPDATE Users SET pseudo = ? ,nom = ? ,prenom = ? ,email = ? ,telephone = ? ,rue = ? ,code_postal = ? ,ville = ? ,mot_de_passe = ? ,credit = ? ,administrateur = ? WHERE no_user = ?";
	public static final String USER_SQL_DELETE = "DELETE FROM Users WHERE no_user = ?";
	public static final String USER_SQL_SELECTALL = "SELECT * FROM Users";
	public static final String USER_SQL_SELECTBYID = "SELECT Users FROM ? where no_user = ? ";
	
	//Selectionner tout les utilisateurs
	@Override
	public List<User> selectAll() {
		List<User> users = new ArrayList<>();
		PreparedStatement stmt;
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			stmt = cnx.prepareStatement(USER_SQL_SELECTALL);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				User user = new User(rs.getInt("no_user"),rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), rs.getString("code_postal"),
						rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"),
						rs.getInt("administrateur"));
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
		
	}
	
	//Selectionner un utilisateur par son numero
	@Override
	public User selectByID(int id) {
		User user = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(USER_SQL_SELECTBYID);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				user = new User(rs.getInt("no_user"),rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), rs.getString("code_postal"),
						rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"),
						rs.getInt("administrateur"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	// Inserer un utilisateur 
	@Override
	public void insert(User user) {
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(USER_SQL_INSERT);

			stmt.setString(1, user.getPseudo());
			stmt.setString(2, user.getNom());
			stmt.setString(3, user.getPrenom());
			stmt.setString(4, user.getEmail());
			stmt.setString(5, user.getTelephone());
			stmt.setString(6, user.getRue());
			stmt.setString(7, user.getCodePostal());
			stmt.setString(8, user.getVille());
			stmt.setString(9, user.getMotDePasse());
			stmt.setInt(10, user.getCredit());
			stmt.setInt(11, user.estAdministrateur() ? 1 : 0); // convertit le booléen en entier
			stmt.setInt(12, user.getNoUser());
			
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//modifier un utilisateur
	@Override
	public void update(User user) {

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(USER_SQL_UPDATE);

			stmt.setString(1, user.getPseudo());
			stmt.setString(2, user.getNom());
			stmt.setString(3, user.getPrenom());
			stmt.setString(4, user.getEmail());
			stmt.setString(5, user.getTelephone());
			stmt.setString(6, user.getRue());
			stmt.setString(7, user.getCodePostal());
			stmt.setString(8, user.getVille());
			stmt.setString(9, user.getMotDePasse());
			stmt.setInt(10, user.getCredit());
			stmt.setInt(11, user.estAdministrateur() ? 1 : 0); // convertit le booléen en entier

			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	//Supprimer un utilisateur

	@Override
	public void delete(int id) {

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement stmt = cnx.prepareStatement(USER_SQL_UPDATE);
			stmt.setInt(1, id);
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}