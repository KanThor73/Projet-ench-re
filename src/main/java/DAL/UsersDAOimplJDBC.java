package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import BO.User;
import Exceptions.DALException;

public class UsersDAOimplJDBC implements UserDAO {
	
	// declaration des constantes pour les requetes SQL

	public static final String USER_SQL_INSERT = "INSERT INTO Users (pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur,salt) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String USER_SQL_UPDATE = "UPDATE Users SET pseudo = ? ,nom = ? ,prenom = ? ,email = ? ,telephone = ? ,rue = ? ,code_postal = ? ,ville = ? ,mot_de_passe = ? ,credit = ? ,administrateur = ? ,salt = ? WHERE no_user = ?";
	public static final String USER_SQL_DELETE = "DELETE FROM Users WHERE no_user = ?";
	public static final String USER_SQL_SELECTALL = "SELECT * FROM Users";
	public static final String USER_SQL_SELECTBYID = "SELECT * FROM Users WHERE no_user = ?";
	
	public static final String USER_SQL_GETID = "SELECT no_user FROM Users WHERE pseudo = ?";
	public static final String USER_SQL_CHECKPSEUDO = "SELECT COUNT(*) AS cnt FROM Users WHERE pseudo = ?";
	public static final String USER_SQL_CHECKMAIL = "SELECT COUNT(*) AS cnt FROM Users WHERE email = ?";
	public static final String USER_SQL_CHECKMDP = "SELECT COUNT(*) AS cnt FROM Users WHERE pseudo = ? AND mot_de_passe = ?";
	public static final String USER_SQL_SELECTBYPSEUDO = "SELECT * FROM Users WHERE pseudo = ?";
	
	
	//Selectionner tout les utilisateurs
	@Override
	public List<User> selectAll() throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			List<User> users = new ArrayList<>();
			PreparedStatement stmt = cnx.prepareStatement(USER_SQL_SELECTALL);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				users.add(new User(rs.getInt("no_user"),rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), rs.getString("code_postal"),
						rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"),
						rs.getInt("administrateur"),rs.getString("salt")));
			}
			return users;

		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("problème de connexion aux données");
		}
	}
	
	//Selectionner un utilisateur par son numero
	@Override
	public User selectByID(int id) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			PreparedStatement stmt = cnx.prepareStatement(USER_SQL_SELECTBYID);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new User(rs.getInt("no_user"),rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), rs.getString("code_postal"),
						rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"),
						rs.getInt("administrateur"),rs.getString("salt"));
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("problème de connexion aux données");
		}
	}
	
	//Selectionner un utilisateur par son pseudo
	@Override
	public User selectByPseudo(String pseudo) throws DALException{
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			PreparedStatement stmt = cnx.prepareStatement(USER_SQL_SELECTBYPSEUDO);
			stmt.setString(1, pseudo);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new User(rs.getInt("no_user"),rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), rs.getString("code_postal"),
						rs.getString("ville"), rs.getString("mot_de_passe"), rs.getInt("credit"),
						rs.getInt("administrateur"),rs.getString("salt"));
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("problème de connexion aux données");
		}
	}
	
	// Inserer un utilisateur 
	@Override
	public void insert(User user) throws DALException {
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(USER_SQL_INSERT);
			
			stmt.setString(1, user.getPseudo());
			stmt.setString(2, user.getNom());
			stmt.setString(3, user.getPrenom());
			stmt.setString(4, user.getEmail());
			if (user.getTelephone() != null) {
				stmt.setString(5, user.getTelephone());
			} else {
				stmt.setNull(5, Types.VARCHAR);
			}
			stmt.setString(6, user.getRue());
			stmt.setString(7, user.getCodePostal());
			stmt.setString(8, user.getVille());
			stmt.setString(9, user.getMotDePasse());
			stmt.setInt(10, user.getCredit());
			stmt.setInt(11, user.estAdministrateur() ? 1 : 0); // convertit le booléen en entier
			stmt.setString(12, user.getSalt());
			
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("problème de connexion aux données");
		}
	}

	//modifier un utilisateur
	@Override
	public void update(User user) throws DALException {

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = cnx.prepareStatement(USER_SQL_UPDATE);

			stmt.setString(1, user.getPseudo());
			stmt.setString(2, user.getNom());
			stmt.setString(3, user.getPrenom());
			stmt.setString(4, user.getEmail());
			if (user.getTelephone() != null) {
				stmt.setString(5, user.getTelephone());
			} else {
				stmt.setNull(5, Types.VARCHAR);
			}
			stmt.setString(6, user.getRue());
			stmt.setString(7, user.getCodePostal());
			stmt.setString(8, user.getVille());
			stmt.setString(9, user.getMotDePasse());
			stmt.setInt(10, user.getCredit());
			stmt.setInt(11, user.estAdministrateur() ? 1 : 0); // convertit le booléen en entier
			stmt.setInt(12, user.getNoUser());
			stmt.setString(13, user.getSalt());

			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("problème de connexion aux données");
		}
	}
	
	//Supprimer un utilisateur
	@Override
	public void delete(int id) throws DALException {

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement stmt = cnx.prepareStatement(USER_SQL_DELETE);
			stmt.setInt(1, id);
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("problème de connexion aux données");
		}
	}
	
	// retourne l'id d'un pseudo demandé
	@Override
	public int getId(String pseudo) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement stmt = cnx.prepareStatement(USER_SQL_GETID);
			stmt.setString(1, pseudo);
			ResultSet rs = stmt.executeQuery();
			
			if (!rs.next()) {
				throw new DALException("utilisateur non trouvé");
			}
			
			return rs.getInt("no_user"); // si le mail a été trouvé
		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException(e.getMessage());
		}
	}
	
	// Vérifie la disponibilité d'un pseudo 
	@Override
	public boolean checkPseudo(String pseudo) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement stmt = cnx.prepareStatement(USER_SQL_CHECKPSEUDO);
			stmt.setString(1, pseudo);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				return (rs.getInt("cnt") == 1); // si le pseudo a été trouvé
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("problème de connexion aux données");
		}
	}
	
	// Vérifie la disponibilité d'un email
	@Override
	public boolean checkEmail(String email) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement stmt = cnx.prepareStatement(USER_SQL_CHECKMAIL);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				return (rs.getInt("cnt") == 1); // si le mail a été trouvé
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("problème de connexion aux données");
		}
	}
	
	// Vérifie la véracité du mdp selon le pseudo
	@Override
	public boolean checkMdp(String pseudo, String mdp) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement stmt = cnx.prepareStatement(USER_SQL_CHECKMDP);
			stmt.setString(1, pseudo);
			stmt.setString(2, mdp);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				return (rs.getInt("cnt") == 1); // si le couple a été trouvé
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("problème de connexion aux données");
		}
	}
}