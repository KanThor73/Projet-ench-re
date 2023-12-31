<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>

<%@ include file="/WEB-INF/jspf/meta.jspf"%>
<link href="style/Auction.css" rel="stylesheet" />
<title>Enchères</title>
</head>
<body>
    <%@ include file="/WEB-INF/jspf/header.jspf"%>
    <div class="container">
        <h1>Details vente</h1>
        <div class="sub-container">
            <div class="fragment">
                <label for="nom-article"><strong>Article : </strong>${nom}</label>
            </div>
            <div class="fragment">
           	    <label for="Description"><strong>Description : </strong>${desc}</label>
           	</div>
            <div class="fragment">
                <label for="catégories"><strong>Catégorie : </strong>${cat}</label>
            </div>
            <div class="fragment">
                <label for="Mise_A_Prix"><strong>Mise à prix : </strong>${prixVente}</label>
            </div>
           	<%
           	if (!request.getAttribute("bestOfferer").toString().equals("")) {
           	%>
           	<div class="fragment">
                <label for="Meilleur_Offres"><strong>Meilleur offre : </strong>${bestOffer}</label>
	            <label for="Meilleur_Offrant"><strong>Offrant : </strong>${bestOfferer}</label>
            </div>
           	<%
           	}
               if (request.getAttribute("user") != null) {
               %>
               <div class="fragment solde">
	               	<label><strong>Votre solde : </strong>${solde}</label>
	                <form class="solde" action="<%=request.getContextPath()%>/Auction?id=${id}" method="post">
	                	<input type="number" name="relance" id="relance" value="${bestOffer+1}" min="${bestOffer+1}" step="1">
	                	<input type="submit" value="Enchérir">
	                </form>
                </div>
               <%}%>
            <div class="fragment">
                <label for="Fin_enchère"><strong>Fin de l'enchère : </strong>${dateFin}</label>
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
            
            <% if (request.getAttribute("proprio") != null) { // vérifier la date dans la servlet %>
            <div class="fragment">
            <div class="button-edit">
            	<button class="edit-button"><a href="<%=request.getContextPath()%>/Editer?id=${id}">Modifier</a></button>
            	</div>
            </div>
            <%}%>
        </div>
    </div>
</body>
</html>