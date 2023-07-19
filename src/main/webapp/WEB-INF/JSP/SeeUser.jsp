<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="style/SeeUser.css" rel="stylesheet">
<meta charset="UTF-8">
<title>User</title>
</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<div class="user-infos">
		<h3>Afficher profil</h3>

		<label for="pseudo">Pseudo : ${pseudo}</label>
		<p id="pseudo"></p>

		<label for="nom">Nom : ${nom}</label>
		<p id="nom"></p>

		<label for="prenom">Prénom : ${prenom}</label>
		<p id="prenom"></p>

		<label for="email">Email : ${email}</label>
		<p id="email"></p>

		<label for="telephone">Téléphone : ${telephone}</label>
		<p id="telephone"></p>

		<label for="rue">Rue : ${rue}</label>
		<p id="rue"></p>

		<label for="codePostal">Code Postal : ${codePostal}</label>
		<p id="codePostal"></p>

		<label for="ville">Ville : ${ville}</label>
		<p id="ville"></p>
	</div>
</body>
</html>