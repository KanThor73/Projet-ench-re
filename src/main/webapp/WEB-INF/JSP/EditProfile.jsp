<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>

<head>

<%@ include file="/WEB-INF/jspf/meta.jspf"%>
<link href="style/newAccount.css" rel="stylesheet">
<link href="style/editProfil.css" rel="stylesheet">
<title>Modifier Profil</title>

</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<main>
		<div class="container">
			<h3>Modifier profil</h3>
			<form action="<%=request.getContextPath()%>/EditerProfil"
				method="post">
				<div class="formContainer">

					<div class="formBody">

						<div class="formFragment">

							<label for="pseudo">Pseudo : </label> <input type="text"
								name="pseudo" id="pseudo" size="30" required
								autofocus="autofocus" value="${pseudo}">

						</div>

						<div class="formFragment">

							<label for="nom">Nom : </label> <input type="text" name="nom"
								id="id" size="30" value="${nom }" required>

						</div>

						<div class="formFragment">

							<label for="prenom">Prénom : </label> <input type="text"
								name="prenom" id="prenom" size="30" value="${prenom }" required>

						</div>

						<div class="formFragment">

							<label for="email">Email : </label> <input type="email"
								name="email" id="email" size="30" value="${email }" required>

						</div>

						<div class="formFragment">

							<label for="telephone">Téléphone : </label> <input type="text"
								name="telephone" id="telephone" size="30" value="${telephone }">

						</div>

						<div class="formFragment">

							<label for="rue">Rue : </label> <input type="text" name="rue"
								id="rue" size="30" value="${rue }" required>

						</div>

						<div class="formFragment">

							<label for="postal">Code postal : </label> <input type="text"
								name="postal" id="postal" size="30" value="${codePostal }"
								required>

						</div>

						<div class="formFragment">

							<label for="ville">Ville : </label> <input type="text"
								name="ville" id="ville" size="30" value="${ville }" required>

						</div>

						<div class="formFragment">
							<label for="mdp">Mot de passe actuel: </label> <input type="password" placeholder="${mdp}" name="mdp" id="mdp" size="30">
						</div>
						<div class="formFragment"></div>
						<div class="formFragment">
							<label for="mdp1">Nouveau mot de passe : </label> <input type="password" name="mdp1" id="mdp1" size="30"> 
						</div>
						<div class="formFragment">

							<label for="mdp2">Confirmation : </label> <input type="password" name="mdp2" id="mdp2" size="30">
						</div>
						<div class="formFragment credit">
							<label class="mgR-2" for="credit">Credit : </label> <label
								for="credit">${credit}</label>
						</div>
						<div class="formFragment"></div>

					</div>

					<!-- formBody / Corps formulaire -->

					<div class="formFooter">
						<input type="submit" value="MODIFIER" name="update"> 
						<input type="submit" value="SUPPRIMER" name="delete">
						<input type="submit" value="QUITTER" name="cancel"> 
					</div>
				</div>
			</form>
			<c:choose>
				<c:when test="${msg != null}">
					<div class="msgBox">
						<p>${msg}</p>
					</div>
				</c:when>
				<c:when test="${msgErreur != null}">
					<div class="errorBox">
						<p>${msgErreur}</p>
					</div>
				</c:when>
				<c:otherwise>
					<div class="holdBox">
						<p>HOLDBOX</p>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</main>
</body>

</html>
