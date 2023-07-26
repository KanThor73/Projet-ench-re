<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="BLL.CategorieManager"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/jspf/meta.jspf"%>
<link href="style/EditAuction.css" rel="stylesheet" />
<meta charset="UTF-8">
<title>Modifier une enchère</title>
</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>

	<div class="container">
		<h1>Modifier une enchère</h1>
		
		<div class="sub-container">

			<form action="<%=request.getContextPath()%>/Editer" method="post">
				<input type="hidden" name="id" value="${id}" />

				<div class="fragment">

					<label for="nom">Nom de l'article :</label> <input type="text"
						id="nom" name="id" value="${nom}" />

				</div>

				<div class="fragment">

					<label for="description">Description :</label>
					<textarea id="desc" name="desc" cols="50" rows="6">${desc}</textarea>

				</div>

				<div class="formFragment">
							<label for="cat">Catégorie </label>
							<select name="cat" id="cat" value="${cat}" size="1">
							<%
							CategorieManager catMgr = CategorieManager.getInstanceOf();
							for (String cat : catMgr.selectAll()) {
							%>
								<option value="<%=cat%>"><%=cat%></option>
							<%}%>
							</select>
						</div>

				<div class="fragment">
					<label for="prixInit">Prix initial : </label>
					<input type="number" name="prixInit" id="prixInit" value="${prixInit}" min="0" step="1">
				</div>
				
				<div class="fragment">
					<label for="dateDebut">Début de l'enchère : </label>
					<input type="date" name="dateDebut" id="dateDebut" value="${dateDebut}" size="10" required>
				</div>
				
				<div class="fragment">
					<label for="dateFin">Fin de l'enchère : </label>
					<input type="date" name="dateFin" id="dateFin" value="${dateFin}" size="10" required>
				</div>
						
				<div class="fragment">
					<label for="rue">Rue :</label> <input type="text" name="rue"
						id="rue" value="${rue}" />
				</div>

				<div class="fragment">
					<label for="codePostal">Code postal :</label> <input type="text"
						name="codePostal" id="codePostal" value="${codePostal}" />
				</div>

				<div class="fragment">
					<label for="ville"> Ville :</label> <input type="text" name="ville"
						id="ville" value="${ville}" />
				</div>

				<div class="formFooter">
					<input type="submit" value="Enregistrer">
				</div>


			</form>
		</div>
	</div>

</body>
</html>