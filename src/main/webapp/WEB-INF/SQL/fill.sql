/*
 * Script mariadb-10.6.2
 * à utiliser pour remplir la bdd de valeurs tests
 * ATTENTION ! bien reset la bdd de toute donnée (script reset.sql) avant de lancer celui-ci
 */

USE TrocEncheres;

INSERT INTO Users VALUES
(default, "thib", "JACQUET", "Thibault", "thib@gmail.com", "0657483946", "evergreen terass", "73260", "Moutiers", "mdps", 10000, 1),
(default, "JB", "SABOURIN", "Jean-Baptiste", "j.b11@hotmail.fr", null, "3, rue du satellite", "-1000", "Moon", "mdp", 10000, 1),
(default, "SamDwarf", "GROLLEAU", "Sami", "sami.grolleau@mailo.com", null, "le grand arbre", "-----", "Forêt du Sahel", "topsecret", 10000, 1),
(default, "LuckyLuke", "LUKE", "Lucky", "dontjoinme@texas.com", null, "somewhere", "-USA-", "Lonely", "lonely", 3500, 0),
(default, "MacPicsou", "PICSOU", "Balthazar", "riche@donaldville.fr", null, "domaine picsou", "-6400", "Donaldville", "dollar", 1147483647, 0),
(default, "AbbePierre", "GROUÈS", "Henri", "abbepierre@emmaus-france.org", null, "boulevard de Port-Royal", "75005", "Paris", "merci", 0, 0),
(default, "HommePressé", "DÉSIR", "Noir", "secretariat@megacorp.com", null, "tout le temps", "00000", "Partout", " ", 50000, 0);

INSERT INTO Categories (libelle) VALUES
("jardinage"),
("informatique"),
("jeux"),
("littérature"),
("décoration"),
("ameublement"),
("vêtements"),
("accessoires");

INSERT INTO ArticlesVendus VALUES
(default, "Brindille mâchée", "Elle me suit depuis 40 ans", "2023-07-24", "2023-07-31", 20, 20, 4, 1),
(default, "Lit en bois", "Lit de l'ère victorienne, retrouvé dans les décombres de la guerre", "2023-07-24", "2023-08-15", 1000, 1000, 6, 6),
(default, "Chaussettes d'hiver", "Elles auraient appartenu à un grand barbu généreux", "2023-07-24", "2023-12-25", 120, 120, 6, 7),
(default, "Machine à écrire", "Provient du Klondike", "2023-06-24", "2023-08-12", 600, 600, 5, 2),
(default, "Bonheur", "Encore à définir", "1000-01-01", "9999-12-31", 0, 0, 6, 6),
(default, "Bonheur", "Bon pour admirer mon sou porte-bonheur pendant une journée", "2023-07-24", "2023-07-31", 5000, 5000, 5, 5),
(default, "PC antique", "Permet tout de même de faire tourner Linux", "2023-07-24", "2023-07-31", 15, 15, 3, 2),
(default, "Écran à CSS instantané", "Tout ce dont vous pourriez rêver !", "2023-07-24", "2023-07-31", 1400, 1400, 1, 2),
(default, "Petit poussin", "Il est jaune", "2023-07-24", "2023-07-31", 9, 9, 3, 1),
(default, "Talkie-walkie", "Portée de 20.000km, pratique pour vos déplacements quotidiens.", "2023-07-26", "2023-07-27", 100, 100, 7, 2),
(default, "Costard du bayou", "Propre", "2023-07-26", "2023-07-27", 430, 2000, 7, 7);

INSERT INTO Retraits VALUES
(1, "Gare de Lyon", "75000", "Paris"),
(2, "Gare de Lyon", "75000", "Paris"),
(3, "Gare de Lyon", "75000", "Paris"),
(4, "Gare de Lyon", "75000", "Paris"),
(5, "Gare de Lyon", "75000", "Paris"),
(6, "Gare de Lyon", "75000", "Paris"),
(7, "Gare de Lyon", "75000", "Paris"),
(8, "Gare de Lyon", "75000", "Paris"),
(9, "Gare de Lyon", "75000", "Paris"),
(10, "Gare de Lyon", "75000", "Paris"),
(11, "Vallée du Rhône", "75000", "Quimper");

INSERT INTO Encheres VALUES
(5, 11, "2023-07-26", 2000);