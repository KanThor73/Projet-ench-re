/*
 * Script mariadb-10.6.2
 */

USE TrocEncheres;

/* TABLE UTILISATEURS */

CREATE TABLE Users (
    no_user   INT AUTO_INCREMENT NOT NULL,
    pseudo           VARCHAR(30) NOT NULL,
    nom              VARCHAR(30) NOT NULL,
    prenom           VARCHAR(30) NOT NULL,
    email            VARCHAR(50) NOT NULL,
    telephone        VARCHAR(15),
    rue              VARCHAR(30) NOT NULL,
    code_postal      VARCHAR(10) NOT NULL,
    ville            VARCHAR(30) NOT NULL,
    mot_de_passe     VARCHAR(30) NOT NULL,
    credit           INT NOT NULL,
    administrateur   INT NOT NULL, /* c'est un booléen */

    CONSTRAINT user_pk PRIMARY KEY (no_user)
);

/* Users default Values */

ALTER TABLE Users
ALTER credit SET DEFAULT 0;
