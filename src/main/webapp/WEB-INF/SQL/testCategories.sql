/*
 * Script mariadb-10.6.2
 * à utiliser pour ajouter une liste de catégories test à la base de données
 */

USE TrocEncheres;

INSERT INTO Categories (libelle) VALUES
("jardinage"), ("informatique"), ("jeux vidéos"), ("littérature"), ("décoration"), ("ameublement");