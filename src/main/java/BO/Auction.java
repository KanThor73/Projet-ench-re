package BO;

import java.util.Date;

public class Auction {
	
	private int noUtilisateur;
	private int noArticle;
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
}
