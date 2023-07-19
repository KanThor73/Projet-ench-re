<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
<%@ include file="/WEB-INF/jspf/meta.jspf"%>
		<link href="/style/base.css" rel="stylesheet">
		<title>Créer un compte</title>
	</head>
	<body>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
		<h2>Créer un compte</h2>
		<form action="<%=request.getContextPath()%>/CreerCompte" method="post">
			<div id="formBody">
				<div class="formFragment">
					<label for="pseudo">Pseudo : </label>
					<input type="text" name="pseudo" id="pseudo" size="30" required autofocus="autofocus">
				</div>
				<div class="formFragment">
					<label for="nom">Nom : </label>
					<input type="text" name="nom" id="id" size="30" required>
				</div>
				<div class="formFragment">
					<label for="prenom">Prénom : </label>
					<input type="text" name="prenom" id="prenom" size="30" required>
				</div>
				<div class="formFragment">
					<label for="email">Email : </label>
					<input type="email" name="email" id="email" size="50" required>
				</div>
				<div class="formFragment">
					<label for="telephone">Téléphone : </label>
					<input type="text" name="telephone" id="telephone" size="10">
				</div>
				<div class="formFragment">
					<label for="rue">Rue : </label>
					<input type="text" name="rue" id="rue" size="30" required>
				</div>
				<div class="formFragment">
					<label for="postal">Code postal : </label>
					<input type="text" name="postal" id="postal" size="10" required>
				</div>
				<div class="formFragment">
					<label for="ville">Ville : </label>
					<input type="text" name="ville" id="ville" size="30" required>
				</div>
				<div class="formFragment">
					<label for="mdp">Mot de passe : </label>
					<input type="password" name="mdp" id="mdp" size="40" required>
				</div>
				<div class="formFragment">
					<label for="mdp2">Confirmation : </label>
					<input type="password" name="mdp2" id="mdp2" size="40" required>
				</div>
			</div> <!-- formBody / Corps formulaire -->
			<div id="formFooter">
				<input type="submit" value="Créer">
				<div class="button">
					<a href="<%=request.getContextPath()%>">Annuler</a>
				</div>
			</div>
		</form>
		<div class="errorBox">
			<p>${msgErreur}</p>
		</div>
	</body>
</html>