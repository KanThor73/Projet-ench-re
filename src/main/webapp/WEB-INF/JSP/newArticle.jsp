<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="BLL.CategorieManager"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/jspf/meta.jspf"%>
<link href="style/newAccount.css" rel="stylesheet"> <!-- TODO newArticle.css -->
<title>Nouvelle vente</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<main>
		<div class="container">
			<h2>Nouvelle vente</h2>
			<form action="<%=request.getContextPath()%>/Vente" method="post">
				<div class="formContainer">
					<div class="formBody">
						<div class="formFragment">
							<label for="nomArticle">Article : </label>
							<input type="text" name="nomArticle" id="nomArticle" size="30" required autofocus="autofocus">
						</div>
						
						<div class="formFragment">
							<label for="description">Description : </label>
							<textarea name="description" id="description" cols="50" rows="6"></textarea>
						</div>
						
						<div class="formFragment">
							<label for="categorie">Catégorie </label>
							<select name="categorie" id="categorie" size="1">
							<%
							CategorieManager catMgr = CategorieManager.getInstanceOf();
							for (String cat : catMgr.selectAll()) {
							%>
								<option value="<%=cat%>"><%=cat%></option>
							<%}%>
							</select>
						</div>
						
						<!-- TODO photo de l'article -->
						
						<div class="formFragment">
							<label for="prixInit">Prix initial : </label>
							<input type="number" name="prixInit" id="prixInit" value="0" min="0" step="1">
						</div>
						
						<div class="formFragment">
							<label for="dateDebut">Début de l'enchère : </label>
							<input type="date" name="dateDebut" id="dateDebut" size="10" required>
						</div>
						
						<div class="formFragment">
							<label for="dateFin">Fin de l'enchère : </label>
							<input type="date" name="dateFin" id="dateFin" size="10" required>
						</div>
						
						<div class="formFragment">
							<label for="rue">Rue : </label>
							<input type="text" name="rue" id="rue" size="30" value="${rue}" required>
						</div>
						
						<div class="formFragment">
							<label for="codePostal">Code Postal : </label>
							<input type="text" name="codePostal" id="codePostal" size="30" value="${codePostal}" required>
						</div>
						
						<div class="formFragment">
							<label for="ville">Ville : </label>
							<input type="text" name="ville" id="ville" size="30" value="${ville}" required>
						</div>
					</div>
					<div class="formFooter">
						<input type="submit" value="Enregistrer">
						<div class="button">
							<a href="<%=request.getContextPath()%>">Annuler</a>
						</div>
					</div>
				</div>
			</form>
			<div class="errorBox">
				<p>${msgErreur}</p>
			</div>
		</div>
	</main>
</body>
</html>