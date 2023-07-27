package BO;

import Exceptions.BOException;

public class User {
	
	private int noUser;
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private String rue;
	private String codePostal;
	private String ville;
	private String motDePasse;
	private int credit;
	private boolean administrateur;
	

	// Constructeur sans noUser ni credit, utilisé lorsque créé par l'utilisateur
    public User(String pseudo, String nom, String prenom, String email, String telephone, String rue, String codePostal,
            String ville, String motDePasse, boolean administrateur) {
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        setEmail(email);
        setTelephone(telephone); // peut être null
        setRue(rue);
        setCodePostal(codePostal);
        setVille(ville);
        setMotDePasse(motDePasse);
        this.credit = 0;
        setAdministrateur(administrateur);
    }
    
    // Constructeur avec noUser et credit, utilisé lors de la lecture de la bdd
    public User(int noUser, String pseudo, String nom, String prenom, String email, String telephone, String rue,
            String codePostal, String ville, String motDePasse, int credit, int administrateur) {
    	this.noUser = noUser;
    	this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        setEmail(email);
        setTelephone(telephone); // peut être null
        setRue(rue);
        setCodePostal(codePostal);
        setVille(ville);
        setMotDePasse(motDePasse);
        this.credit = credit;
        setAdministrateur(administrateur == 1); // convert l'entier en booléen
    }
    
    /*
     * Accesseurs
     * Il n'y a un setter que lorsque la donnée est modifiable après la création de l'objet
     */
    
    // No User
    public int getNoUser() {
        return noUser;
    }
    
    // Pseudo
    public String getPseudo() {
        return pseudo;
    }
    
    // Nom
    public String getNom() {
        return nom;
    }
    
    // Prenom
    public String getPrenom() {
        return prenom;
    }
    
    // Email
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
    	if (email == null) {
    		// TODO lancer une exception
    	}
        this.email = email;
    }
    
    // Telephone
    public String getTelephone() {
        return telephone; // Peut retourner null
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone; // Peut être null
    }
    public void rmTelephone() { // pour supprimer directement le numéro de téléphone
    	setTelephone(null);
    }
    
    // Rue
    public String getRue() {
        return rue;
    }
    public void setRue(String rue) {
        this.rue = rue;
    }
    
    // Code Postal
    public String getCodePostal() {
        return codePostal;
    }
    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }
    
    // Ville
    public String getVille() {
        return ville;
    }
    public void setVille(String ville) {
        this.ville = ville;
    }
    
    // Mot de passe
    public String getMotDePasse() {
        return motDePasse;
    }
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    
    // Credit
    public int getCredit() {
        return credit;
    }
    public void changeCredit(int credit) throws BOException {
    	this.credit += credit;
    	if (this.credit < 0) {
    		this.credit -= credit; // annulation
    		throw new BOException("solde insuffisant");
    	}
    }
    
    // Administrateur
    public boolean estAdministrateur() { // Attention syntaxe
        return administrateur;
    }
    public void setAdministrateur(boolean administrateur) {
        this.administrateur = administrateur;
    }

    @Override
    public String toString() {
        return "User [noUser=" + noUser + ", pseudo=" + pseudo + ", nom=" + nom + ", prenom=" + prenom + ", email="
                + email + ", telephone=" + telephone + ", rue=" + rue + ", codePostal=" + codePostal + ", ville="
                + ville + ", motDePasse=" + motDePasse + ", credit=" + credit + ", administrateur=" + administrateur
                + "]";
    }
	
}