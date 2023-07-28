<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/jspf/meta.jspf"%>
<link href="style/WinAuction.css" rel="stylesheet">
<title>Fin d'enchères</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<div class="center-content">
	<div class="container">
	<c:choose>
		<c:when test="${delete != null}">
			<div class="msgBox">
				<p>
					La date de fin d'enchère de votre article est dépassée.<br>
					<br>
					Aucun acheteur, votre article va être retiré.
				</p>
				<form action="<%=request.getContextPath()%>/FinEncheres?id=<%=request.getParameter("id")%>" method="post">
					<input type="submit" value="Valider" name="noAuction">
				</form>
			</div>
		</c:when>
		<c:when test="${proprio != null}">
			<div class="msgBox">
				<p>
					La date de fin d'enchères est atteinte.<br>
					<br>
					En attente du retrait par ${contactPseudo}.
				</p>
			</div>
			<div class="contact">
				<h3>Contact : </h3>
				<a href="<%=request.getContextPath()%>/Profil?id=${contactId}">${contactPseudo}</a>
			</div>
		</c:when>
		<c:when test="${winner != null}">
			<div class="msgBox">
				<p>
					Vous avez remporté cette enchère !<br>
					<br>
					Veuillez retirer l'article à : ${rue}, ${codePostal} ${ville}.<br>
				</p>
				
				<form action="<%=request.getContextPath()%>/FinEncheres?id=<%=request.getParameter("id")%>" method="post">
					<input type="submit" value="Retrait effectué" name="auction">
				</form>
			</div>
			<div class="contact">
				<h3>Contact : </h3>
				<a href="<%=request.getContextPath()%>/Profil?id=${contactId}">${contactPseudo}</a>
			</div>
		</c:when>
		<c:otherwise>
			<div class="msgBox">
				<p>
					Un problème est survenu, veuillez retourner à l'accueil<br>
				</p>
				
				<div class="button-back">
				<a href="<%=request.getContextPath()%>/IndexServlet">Retour</a>
				</div>
					
			</div>
		</c:otherwise>
	</c:choose>
	</div>
	</div>
	<c:choose> 
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
</body>
</html>