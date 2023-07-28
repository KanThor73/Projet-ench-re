<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/jspf/meta.jspf"%>
<link href="style/index.css" rel="stylesheet" />
<%@ include file="/WEB-INF/jspf/mediaQueries.jspf"%>
<title>Les objets sont nos amis</title>
</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<div class="sub-contain">
		<div class="title">
			<h1 id="h1">Liste des enchères</h1>
		</div>
		<div class="container">
		<div>
			<h3>Filtres :</h3>
			<form class="command" action="IndexServlet" method="post">
				<div class="categories">
					<input type="text" name="recherche"
						placeholder="Le nom de l'article contient" />
					<div class="cate_container">
						<label for="categorie">Catégorie :</label> <select
							name="categorie" id="cat" size="1">
							<option value="toutes" selected>toutes</option>
							<c:forEach var="categorie" items="${categories}">
								<option value="${categorie}">${categorie}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="search-button">
					<input type="submit" value="Rechercher" name="search" />
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
			<div class="encheres">
				<c:forEach var="article" items="${articles}">
					<div class="encadrer">
						<img src="img/imgArticle.svg" alt="img1" />
						<div class="infos">
							<a href="<%=request.getContextPath()%>/Auction?id=${article.getNoArticle()}"><h4>${article.getNom()}</h4></a>
							<div class="prix">
								<p>
									<strong>Prix : </strong>${article.getPrixVente()}
								</p>
							</div>
							<div class="enchere">
								<p>
									<strong>Fin de l'enchère : </strong>${article.getDateFin()}<br>
									${article.isEnded()}
								</p>
							</div>
							<div class="vendeur">
								<p>
									<strong>Vendeur : </strong><a href="<%=request.getContextPath()%>/Profil?id=${article.getOwnerId()}">${article.getPseudo()}</a>
								</p>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>