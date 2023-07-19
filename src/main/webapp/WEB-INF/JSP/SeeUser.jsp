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
	<h3> Afficher profil </h3>
	
<label for="pseudo">Pseudo :</label>
	<p id="pseudo"></p>
	
<label for="nom">Nom :</label>
<p id="nom"></p> 

<label for="prenom">Prénom :</label>
<p id="prenom"></p>

<label for="email">Email :</label>
<p id="email"></p>

<label for="telephone">Téléphone :</label>
<p id="telephone"></p>

<label for="rue">Rue :</label>
<p id="rue"></p>

<label for="codePostal">Code Postal :</label>
<p id="codePostal"></p>

<label for="ville">Ville :</label>
<p id="ville"></p>
</div>
	
	

</body>
</html>