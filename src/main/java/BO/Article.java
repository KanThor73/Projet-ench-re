package BO;

import java.time.Instant;
import java.util.Date;

import BLL.ArticleManager;
import Exceptions.DALException;

public class Article {

	ArticleManager articleMgr = ArticleManager.getInstanceOf();

	private int noArticle;
	private String nom;
	private String description;
	private String categorie;

	private Date dateDebut;
	private Date dateFin;

	private Integer prixInit; // peut être null
	private Integer prixVente; // peut être null

	private int ownerId;
	private String pseudo;

	// constructer sans noArticle ni prixVente, utilisé quand créé par l'utilisateur
	public Article(String nom, String description, String categorie, Date dateDebut, Date dateFin, Integer prixInit,
			int ownerId) {

		setNom(nom);
		setDescription(description);
		setCategorie(categorie);
		setDateDebut(dateDebut);
		setDateFin(dateFin);
		setPrixInit(prixInit);
		setPrixVente(prixInit); // set up le premier prix vente égal au prix init
		setOwnerId(ownerId);
		setPseudo();
	}

	// constructer avec noArticle et prixVente, utilisé lors de la lecture de la bdd
	public Article(int id, String nom, String description, String categorie, Date dateDebut, Date dateFin,
			Integer prixInit, Integer prixVente, int ownerId) {

		noArticle = id;
		setNom(nom);
		setDescription(description);
		setCategorie(categorie);
		setDateDebut(dateDebut);
		setDateFin(dateFin);
		setPrixInit(prixInit);
		setPrixVente(prixVente);
		setOwnerId(ownerId);
		setPseudo();
	}

	/*
	 * Accesseurs Il n'y a un setter que lorsque la donnée est modifiable après la
	 * création de l'objet
	 */

	// Pseudo
	void setPseudo() {
		try {
			this.pseudo = articleMgr.getPseudoByNoUser(ownerId);
		} catch (DALException e) {
			new DALException("Impossible de recuperer le proprietaire de l'article");
			e.printStackTrace();
		}
	}

	public String getPseudo() {
		return pseudo;
	}

	// No article
	public int getNoArticle() {
		return noArticle;
	}

	// Nom
	public String getNom() {
		return nom;
	}

	void setNom(String nom) {
		this.nom = nom;
	}

	// Description
	public String getDescription() {
		return description;
	}

	void setDescription(String desc) {
		description = desc;
	}

	// Categorie
	public String getCategorie() {
		return categorie;
	}

	void setCategorie(String cat) {
		categorie = cat;
	}

	// Date Debut
	public Date getDateDebut() {
		return dateDebut;
	}

	void setDateDebut(Date date) {
		dateDebut = date;
	}

	// Date Fin
	public Date getDateFin() {
		return dateFin;
	}

	void setDateFin(Date date) {
		dateFin = date;
	}
	
	public String isEnded() {
		return dateFin.before(Date.from(Instant.now())) ? "Enchères terminées" : "";
	}

	// Prix initial
	public Integer getPrixInit() {
		return prixInit;
	}

	void setPrixInit(Integer prix) {
		prixInit = prix;
	}

	// Prix de vente
	public Integer getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(Integer prix) {

		prixVente = prix;
	}

	// Owner id
	public int getOwnerId() {
		return ownerId;
	}

	void setOwnerId(int id) {
		ownerId = id;
	}
}