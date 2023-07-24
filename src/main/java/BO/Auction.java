package BO;
import java.util.Date;

public class Auction {
	private int noUtilisateur;
	private int noArticle;
	private Date DateEnchere;
	private int montantEnchere;
	
	


public Auction(){
	
}

public Auction (int noUtilisateur, int noArticle, Date DateEnchere, int montantEnchere) {
	
	this.noUtilisateur = noUtilisateur;
	this.noArticle = noArticle;
	this.DateEnchere = DateEnchere;
	this.montantEnchere = montantEnchere;
	
}

public int getnoUtilisateur() {
	return noUtilisateur;
}

public void setnoUtilisateur(int noUtilisateur) {
	this.noUtilisateur = noUtilisateur;
}

public int getnoArticle() {
	return noArticle;
}

public void setnoArticles(int noArticle) {
	this.noArticle = noArticle;
	
}

public Date getDateEnchere() {
	return DateEnchere;
}

public void setDateEnchere(Date DateEnchere) {
	this.DateEnchere  = DateEnchere;
}

public int getmontantEnchere() {
	return montantEnchere;
	
}

public void setmontantEnchere(int montantEnchere) {
	this.montantEnchere = montantEnchere;
}

public String toString() {
	return "Enchere{" +
            "noUtilisateur=" + noUtilisateur +
            ", noArticle=" + noArticle +
            ", dateEnchere=" + DateEnchere +
            ", montantEnchere=" + montantEnchere +
            '}';
}


}
