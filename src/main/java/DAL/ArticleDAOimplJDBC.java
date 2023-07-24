package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import BO.Article;
import Exceptions.DALException;

public class ArticleDAOimplJDBC implements ArticleDAO {

	// declaration des constantes pour les requetes SQL

	public static final String ARTICLE_SQL_INSERT = "INSERT INTO ArticlesVendus (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_user, no_categorie) VALUES (?,?,?,?,?,?,?,(SELECT no_categorie FROM Categories WHERE libelle = ?))";
	public static final String ARTICLE_SQL_UPDATE = "UPDATE ArticlesVendus SET nom_article = ? , description = ? , date_debut_encheres = ? , date_fin_encheres = ? , prix_initial = ? , prix_vente = ? , no_user = ? , no_categorie = (SELECT no_categorie FROM Categories WHERE libelle = ?)  WHERE no_article = ?";
	public static final String ARTICLE_SQL_DELETE = "DELETE FROM ArticlesVendus WHERE no_article = ?";
	public static final String ARTICLE_SQL_SELECTALL = "SELECT no_article, nom_article, description, libelle as categorie, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_user FROM ArticlesVendus JOIN Categories WHERE ArticlesVendus.no_categorie = Categories.no_categorie";
	public static final String ARTICLE_SQL_SELECTBYID = "SELECT no_article, nom_article, description, libelle as categorie, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_user FROM ArticlesVendus JOIN Categories WHERE ArticlesVendus.no_categorie = Categories.no_categorie AND no_article = ?";
	public static final String ARTICLE_SQL_SELECTBYCAT = "SELECT no_article, nom_article, description, libelle as categorie, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_user FROM ArticlesVendus JOIN Categories WHERE ArticlesVendus.no_categorie = Categories.no_categorie AND libelle = ?";
	public static final String RETRAIT_SQL_LAST_COLUMN = "SELECT MAX(no_article) AS nextID FROM ArticlesVendus;";

	@Override
	public List<Article> selectAll() throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {

			List<Article> articles = new ArrayList<>();
			PreparedStatement stmt = cnx.prepareStatement(ARTICLE_SQL_SELECTALL);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int noArticle = rs.getInt("no_article");
				String nom = rs.getString("nom_article");
				String desc = rs.getString("description");
				String cat = rs.getString("categorie");
				Date dateDebut = rs.getDate("date_debut_encheres");
				Date dateFin = rs.getDate("date_fin_encheres");
				Integer prixInit = Integer.valueOf(rs.getInt("prix_initial"));
				if (rs.wasNull()) { // si la dernière colonne lue est nulle
					prixInit = null;
				}
				Integer prixVente = Integer.valueOf(rs.getInt("prix_vente"));
				if (rs.wasNull()) { // si la dernière colonne lue est nulle
					prixVente = null;
				}
				int ownerId = rs.getInt("no_user");
				articles.add(new Article(noArticle, nom, desc, cat, dateDebut, dateFin, prixInit, prixVente, ownerId));
			}
			return articles;

		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("problème de connexion aux données");
		}
	}

	@Override
	public Article selectByID(int id) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement stmt = cnx.prepareStatement(ARTICLE_SQL_SELECTBYID);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				int noArticle = rs.getInt("no_article");
				String nom = rs.getString("nom_article");
				String desc = rs.getString("description");
				String cat = rs.getString("categorie");
				Date dateDebut = rs.getDate("date_debut_encheres");
				Date dateFin = rs.getDate("date_fin_encheres");
				Integer prixInit = Integer.valueOf(rs.getInt("prix_initial"));
				if (rs.wasNull()) { // si la dernière colonne lue est nulle
					prixInit = null;
				}
				Integer prixVente = Integer.valueOf(rs.getInt("prix_vente"));
				if (rs.wasNull()) { // si la dernière colonne lue est nulle
					prixVente = null;
				}
				int ownerId = rs.getInt("no_user");

				return new Article(noArticle, nom, desc, cat, dateDebut, dateFin, prixInit, prixVente, ownerId);
			} else {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("problème de connexion aux données");
		}
	}

	@Override
	public void update(Article article) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement stmt = cnx.prepareStatement(ARTICLE_SQL_UPDATE);
			stmt.setString(1, article.getNom());
			stmt.setString(2, article.getDescription());
			stmt.setDate(3, new java.sql.Date(article.getDateDebut().getTime())); // conversion de java.util.Date en
																					// java.sql.Date
			stmt.setDate(4, new java.sql.Date(article.getDateFin().getTime())); // conversion de java.util.Date en
																				// java.sql.Date
			if (article.getPrixInit() != null) {
				stmt.setInt(5, article.getPrixInit());
			} else {
				stmt.setNull(6, Types.INTEGER);
			}
			if (article.getPrixVente() != null) {
				stmt.setInt(6, article.getPrixVente());
			} else {
				stmt.setNull(6, Types.INTEGER);
			}
			stmt.setInt(7, article.getOwnerId());
			stmt.setString(8, article.getCategorie());
			stmt.setInt(9, article.getNoArticle());

			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("problème de connexion aux données");
		}
	}

	@Override
	public void delete(int id) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement stmt = cnx.prepareStatement(ARTICLE_SQL_DELETE);
			stmt.setInt(1, id);
			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("problème de connexion aux données");
		}
	}

	@Override
	public void insert(Article article) throws DALException {
		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement stmt = connection.prepareStatement(ARTICLE_SQL_INSERT);

			stmt.setString(1, article.getNom());
			stmt.setString(2, article.getDescription());
			stmt.setDate(3, new java.sql.Date(article.getDateDebut().getTime())); // conversion de java.util.Date en
																					// java.sql.Date
			stmt.setDate(4, new java.sql.Date(article.getDateFin().getTime())); // conversion de java.util.Date en
																				// java.sql.Date
			if (article.getPrixInit() != null) {
				stmt.setInt(5, article.getPrixInit());
			} else {
				stmt.setNull(6, Types.INTEGER);
			}
			if (article.getPrixVente() != null) {
				stmt.setInt(6, article.getPrixVente());
			} else {
				stmt.setNull(6, Types.INTEGER);
			}
			stmt.setInt(7, article.getOwnerId());
			stmt.setString(8, article.getCategorie());

			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("problème de connexion aux données");
		}
	}

	@Override
	public List<Article> selectByCategory(String categorie) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {

			List<Article> articles = new ArrayList<>();
			PreparedStatement stmt = cnx.prepareStatement(ARTICLE_SQL_SELECTBYCAT);
			stmt.setString(1, categorie);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int noArticle = rs.getInt("no_article");
				String nom = rs.getString("nom_article");
				String desc = rs.getString("description");
				String cat = rs.getString("categorie");
				Date dateDebut = rs.getDate("date_debut_encheres");
				Date dateFin = rs.getDate("date_fin_encheres");
				Integer prixInit = Integer.valueOf(rs.getInt("prix_initial"));
				if (rs.wasNull()) { // si la dernière colonne lue est nulle
					prixInit = null;
				}
				Integer prixVente = Integer.valueOf(rs.getInt("prix_vente"));
				if (rs.wasNull()) { // si la dernière colonne lue est nulle
					prixVente = null;
				}
				int ownerId = rs.getInt("no_user");
				articles.add(new Article(noArticle, nom, desc, cat, dateDebut, dateFin, prixInit, prixVente, ownerId));
			}
			return articles;

		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("problème de connexion aux données");
		}
	}

	@Override
	public int getNextNoArticle() throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement stmt = cnx.prepareStatement(RETRAIT_SQL_LAST_COLUMN);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return (rs.getInt("nextID")); // retourne le no_article du dernier article importe et ajoute 1
			} else {
				throw new DALException("problème de connexion aux données");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new DALException("problème de connexion aux données");
		}
	}
}
