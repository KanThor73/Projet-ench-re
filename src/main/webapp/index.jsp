<<<<<<< HEAD
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
	<form action="LoginServlet" method="post">
		<!--label utilisateur + input="txt" -->
		<div>
			<label for="username">Utilisateur :</label> <input type="text"
				id="username" name="username" required>
		</div>

		<!--label password + input="txt" -->
		<div>
			<label for="password">Mot de passe :</label> <input type="text"
				id="password" name="password" required>
		</div>

		<!--label "se souvenir de moi" + input="Checkbox" -->
		<div class="remember-me">
			<input type="checkbox" id="remember" name="remember"> <label
				for="remember">Se souvenir de moi</label>
		</div>

		<!--Bouton connexion -->
		<div class="button-container">
			<input type="submit" value="Connexion">
		</div>

		<!--lien "mot de passe oublié" -->
		<div class="forgot-password">
			<a href="#">Mot de passe oublié</a>
		</div>

		<!--bouton "" -->
		<div class="create-account-button">
			<a href="#"><button class="create-account-button">Créer
					un compte</button></a>
		</div>
		
		<div>
			<label>${msgErreur}</label>
		</div>
	</form>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
=======
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="style/styleAccueil.css" rel="stylesheet">
<%@ include file="/WEB-INF/jspf/meta.jspf"%>
<meta charset="UTF-8">
<title>Accueil</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<div class="login">
        <a href="#">S'inscrire - Se connecter</a>
    </div>

    <h2>Liste des enchères</h2>

    <div class="filters">
        <p><strong>Filtres :</strong></p>
        <input type="text" name="recherche" placeholder="Le nom de l'article contient">
    </div>

    <div class="categories">
        <label for="categorie">Catégorie :</label>
        <select id="categorie" name="categorie">
            <option value="Informatique">Informatique</option>
            <option value="Ameublement">Ameublement</option>
            <option value="Vêtement">Vêtement</option>
            <option value="Sport&Loisir">Sport&Loisir</option>
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
>>>>>>> e06bede7c09c80dc29083a82324bd9359eb49951
</html>