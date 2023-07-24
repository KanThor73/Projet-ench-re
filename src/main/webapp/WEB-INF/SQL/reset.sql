/*
 * Script mariadb-10.6.2
 * à utiliser pour vider toutes les tables de la bdd
 */

USE TrocEncheres;

DELETE FROM Retraits;
DELETE FROM Encheres;
DELETE FROM ArticlesVendus;
DELETE FROM Categories;
DELETE FROM Users;