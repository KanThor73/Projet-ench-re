<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<%@ include file="/WEB-INF/jspf/meta.jspf"%>
<link href="style/Auction.css" rel="stylesheet" />
<meta charset="UTF-8">
<title> Enchère</title>
</head>
<body>
    <%@ include file="/WEB-INF/jspf/header.jspf"%>
    <div class="container">
        <h1>Details vente</h1>
        <div class="sub-container">
            <div class="fragment">
                <label for="nom-article">Article : ${nom}</label>
            </div>
            <div class="fragment">
           	    <label for="Description">Description : ${desc}</label>
           	</div>
            <div class="fragment">
                <label for="catégories">Catégorie :${cat}</label>
            </div>
            <div class="fragment">
                <label for="Meilleur_Offres">Meilleur offre : ${bestOffer}</label>
                <% if (request.getParameter("bestOfferer") != "") {%>
                <div class="fragment">
                <label for="Meilleur_Offrant">Offrant : ${bestOfferer}</label>
                <% }
                if (request.getAttribute("user") != null) {%>
                <form action="<%=request.getContextPath()%>/Auction?id=${id}" method="post">
                	<input type="number" name="relance" id="relance" value="${bestOffer}" min="${bestOffer}" step="1">
                	</div>
                	 <!-- TODO bouton submit -->
                </form>
                <%}%>
            </div>
            <div class="fragment">
                <label for="Mise_A_Prix">Mise à prix : ${prixVente}</label>
            </div>
            <div class="fragment">
                <label for="Fin_enchère">Fin de l'enchère : ${dateFin})</label>
            </div>
            
          
            <% if (request.getAttribute("proprio") != null) { // vérifier la date dans la servlet %>
            <div class="fragment">
            	<label>Modifier</label>
            </div>
            <%}%>
        </div>
    </div>
      <div class="auction-button">
            
            <button>Enchérir</button>
            
            </div>

</body>
</html>