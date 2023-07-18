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

	<form>
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
	</form>

	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>