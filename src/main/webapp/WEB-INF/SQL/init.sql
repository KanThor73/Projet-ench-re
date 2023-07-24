/*
 * Script mariadb-10.6.2
 */

USE TrocEncheres;

/* TABLE UTILISATEURS */
CREATE TABLE Users (
	no_user			INT AUTO_INCREMENT NOT NULL,
	pseudo			VARCHAR(30) NOT NULL,
	nom				VARCHAR(30) NOT NULL,
	prenom			VARCHAR(30) NOT NULL,
	email			VARCHAR(50) NOT NULL, /* Pour stocker des adresses assez longues */
	telephone		VARCHAR(15),
	rue				VARCHAR(30) NOT NULL,
	code_postal		VARCHAR(10) NOT NULL,
	ville			VARCHAR(30) NOT NULL,
	mot_de_passe	VARCHAR(40) NOT NULL, /* Plus de 32 pour les personnes utilisant des gestionnaires de mdp */
	credit			INT NOT NULL,
	administrateur	INT NOT NULL, /* c'est un booléen 0 ou 1 */

	CONSTRAINT user_pk PRIMARY KEY (no_user)
);

/* Users default Values */
ALTER TABLE Users
ALTER credit SET DEFAULT 0;

/* TABLE Categories */
CREATE TABLE Categories (
	no_categorie	INT AUTO_INCREMENT NOT NULL,
	libelle			VARCHAR(30) NOT NULL,
	
	CONSTRAINT categorie_pk PRIMARY KEY (no_categorie)
);

/* TABLE ArticlesVendus */
CREATE TABLE ArticlesVendus (
	no_article				INT AUTO_INCREMENT NOT NULL,
	nom_article				VARCHAR(30) NOT NULL,
	description				VARCHAR(300) NOT NULL,
	date_debut_encheres		DATE NOT NULL,
	date_fin_encheres		DATE NOT NULL,
	prix_initial			INT NULL, /* Peut être null si personne n'a proposé de prix */
	prix_vente				INT NULL, /* Peut être null tant qu'il n'est pas vendu */
	no_user					INT NOT NULL,
	no_categorie			INT NOT NULL,
	
	CONSTRAINT article_pk PRIMARY KEY (no_article),
	CONSTRAINT article_user_fk FOREIGN KEY (no_user) REFERENCES Users (no_user) ON DELETE CASCADE,
	CONSTRAINT article_categorie_fk FOREIGN KEY (no_categorie) REFERENCES Categories (no_categorie)
);

/* TABLE Encheres */
CREATE TABLE Encheres (
	no_user			INT NOT NULL,
	no_article		INT NOT NULL,
	date_enchere	DATETIME NOT NULL,
	montant_enchere	INT NOT NULL,
	
	CONSTRAINT enchere_pk PRIMARY KEY (no_user, no_article),
	CONSTRAINT enchere_user_fk FOREIGN KEY (no_user) REFERENCES Users (no_user),
	CONSTRAINT enchere_article_fk FOREIGN KEY (no_article) REFERENCES ArticlesVendus (no_article)
);

/* TABLE Retraits */
CREATE TABLE Retraits (
	no_article		INT NOT NULL,
	rue				VARCHAR(30) NOT NULL,
	code_postal		VARCHAR(15) NOT NULL,
	ville			VARCHAR(30) NOT NULL,
	
	CONSTRAINT retrait_pk PRIMARY KEY (no_article),
	CONSTRAINT retrait_article_fk FOREIGN KEY (no_article) REFERENCES ArticlesVendus (no_article)
);
