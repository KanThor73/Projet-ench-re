package BO;

import java.util.Date;

public class Article {
	
	private int noArticle;
	private String nom;
	private String description;
	private String categorie;
	
	private Date dateDebut;
	private Date dateFin;
	
	private Integer prixInit; // peut être null
	private Integer prixVente; // peut être null
	
	private int ownerId;
	
	// constructer sans noArticle ni prixVente, utilisé quand créé par l'utilisateur
	public Article(String nom, String description, String categorie, Date dateDebut, Date dateFin, Integer prixInit, int ownerId) {
		
		setNom(nom);
		setDescription(description);
		setCategorie(categorie);
		setDateDebut(dateDebut);
		setDateFin(dateFin);
		setPrixInit(prixInit);
		setPrixVente(null);
		setOwnerId(ownerId);
	}
	
	// constructer avec noArticle et prixVente, utilisé lors de la lecture de la bdd
		public Article(int id, String nom, String description, String categorie, Date dateDebut, Date dateFin, Integer prixInit, Integer prixVente, int ownerId) {
			
			noArticle = id;
			setNom(nom);
			setDescription(description);
			setCategorie(categorie);
			setDateDebut(dateDebut);
			setDateFin(dateFin);
			setPrixInit(prixInit);
			setPrixVente(prixVente);
			setOwnerId(ownerId);
		}
	
    /*
     * Accesseurs
     * Il n'y a un setter que lorsque la donnée est modifiable après la création de l'objet
     */
	
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
	void setPrixVente(Integer prix) {
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


