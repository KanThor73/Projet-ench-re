package BO;

import java.util.Date;

public class Auction implements Comparable<Auction> {
	
	private int noUtilisateur;
	private int noArticle;
	private String Description;
	private String Categorie;
	private int meilleurOffre;
	private int meilleurOffrant;
	private int prixVente;
	private Date dateFin;
	private Date dateEnchere;
	private int montantEnchere;

	public Auction(int noUtilisateur, int noArticle, Date dateEnchere, int montantEnchere) {

		this.noUtilisateur = noUtilisateur;
		this.noArticle = noArticle;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}

	public int getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}
	public String getDescrition() {
		return Description;
	}
	
	public void setDescrition(String Description) {
		this.Description = Description;
	}
	
	public String getCategorie() {
		return Categorie;
	}
	
	public void setCategorie(String Categorie) {
		this.Categorie = Categorie;
	}
	
	public int getmeilleurOffre() {
		return meilleurOffre;
	}
	
	public void setmeilleurOffre(int meilleurOffre) {
		this.meilleurOffre = meilleurOffre;
	}
	
	public int getmeilleurOffrant() {
		return meilleurOffrant;
	}
	
	public void setmeilleurOffrant(int meilleurOffrant) {
		this.meilleurOffrant = meilleurOffrant;
	}
	
	public int getprixVente() {
		return prixVente;
	}
	
	public void setprixVente(int prixVente) {
		this.prixVente = prixVente;
	}
	
	public Date dateFin() {
		return dateFin;
	}
	
	public void setdateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public Date getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(Date dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public int getMontantEnchere() {
		return montantEnchere;
	}

	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	public String toString() {
		return "Enchere{" + "noUtilisateur=" + noUtilisateur + ", noArticle=" + noArticle + ", dateEnchere="
				+ dateEnchere + ", montantEnchere=" + montantEnchere + '}';
	}

	@Override
	public int compareTo(Auction o) {
		return montantEnchere - o.getMontantEnchere();
	}
}
