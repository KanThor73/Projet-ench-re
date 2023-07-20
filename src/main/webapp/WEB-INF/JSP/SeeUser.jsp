<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/jspf/meta.jspf"%>
<link href="style/SeeUser.css" rel="stylesheet">
<title>User</title>
</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<div class="user-infos">
		<h3>Afficher profil</h3>
		<div class="container">
			<label for="pseudo">Pseudo : ${pseudo}</label> <label for="nom">Nom
				: ${nom}</label> <label for="prenom">Prénom : ${prenom}</label> <label
				for="email">Email : ${email}</label> <label for="telephone">Téléphone
				: ${telephone}</label> <label for="rue">Rue : ${rue}</label> <label
				for="codePostal">Code Postal : ${codePostal}</label> <label
				for="ville">Ville : ${ville}</label>
		</div>
		<div class="formFooter">
			<input type="submit" value="Modifier">
		</div>
	</div>
</body>
</html>