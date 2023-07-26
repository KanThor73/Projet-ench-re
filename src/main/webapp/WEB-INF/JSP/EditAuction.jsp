<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/jspf/meta.jspf"%>
<link href="style/Auction.css" rel="stylesheet" />
<meta charset="UTF-8">
<title>Modifier une enchère</title>
</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>

	<div class="container">
		<h1>Modifier une enchère</h1>
		<div class="sub-container">

			<form action="EditAuctionServlet" method="post">
				<input type="hidden" name="id" value="${id}" />

				<div class="fragment">

					<label for="nom">Nom de l'article :</label> <input type="text"
						id="nom" name="id" value="${nom}" />

				</div>

				<div class="fragment">

					<label for="description">Description :</label>
					<textarea id="desc" name="desc">${desc}</textarea>

				</div>

				<div class="fragment">
					<label for="categorie">Catégorie :</label> <input type="text"
						id="cat" name="cat" value="${cat}" />
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