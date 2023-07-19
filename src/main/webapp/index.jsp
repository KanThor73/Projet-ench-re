<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
<%@ include file="/WEB-INF/jspf/meta.jspf"%>
		<link href="style/index.css" rel="stylesheet">
		<title>Les objets sont nos amis</title>
	</head>
	<body>
<%@ include file="/WEB-INF/jspf/header.jspf"%>

		<h2>Liste des enchères</h2>
	
		<div class="filters">
			<p>
				<strong>Filtres :</strong>
			</p>
			<input type="text" name="recherche" placeholder="Le nom de l'article contient">
		</div>
	
		<div class="categories">
			<label for="categorie">Catégorie :</label>
			<select id="categorie" name="categorie">
				<option value="informatique">Informatique</option>
				<option value="ameublement">Ameublement</option>
				<option value="vetement">Vêtement</option>
				<option value="sportLoisir">Sport&Loisir</option>
			</select>
		</div>
	
		<div class="search-button">
			<button>Rechercher</button>
		</div>
	
		<div class="encadrer">
			<img src="#" alt="img1">
			<div class="infos">
				<h4>PC gamer pour travailler</h4>
				<div class="prix">Prix : 210 Points</div>
				<div class="enchere">Fin de l'enchère : 20 juillet 2023</div>
				<div class="vendeur">Vendeur : JOJO 44</div>
			</div>
		</div>
	
		<div class="encadrer">
			<img src="#" alt="image2">
			<div class="infos">
				<h4>Rocket stove pour riz et pâtes</h4>
				<div class="prix">Prix : 185 points</div>
				<div class="enchere">Fin de l'enchère : 21 juillet 2023</div>
				<div class="vendeur">Vendeur : Jane Smith</div>
			</div>
		</div>
	</body>
</html>