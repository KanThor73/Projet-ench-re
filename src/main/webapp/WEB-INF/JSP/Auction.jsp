<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<%@ include file="/WEB-INF/jspf/meta.jspf"%>
<link href="style/Auction.css" rel="stylesheet" />
<meta charset="UTF-8">
<title>Enchère</title>
</head>
<body>
    <%@ include file="/WEB-INF/jspf/header.jspf"%>

    <div class="container">
        <h1>Details vente</h1>

        <div class="FormCntainer">
            <div class="FormBody">
                <div class="FormFragment">
                    <label for="nom-article">Articles : ${nom}</label>
                    <div class="FormFragment">
                        <label for="Description">Description : ${desc}</label>
                        <div class="FormFragment">
                            <label for="catégories">Catégories : ${cat}</label>
                            <div class="FormFragment">
                                <label for="Meilleur_Offres">Meilleur offre :</label>
                                <div class="FormFragment">
                                    <label for="Mise_A_Prix">Mise à prix :  ${prixVente}</label>
                                    <div class="FormFragment">
                                        <label for="Fin_enchère">Fin de l'enchère : ${dateFin}</label>
                                        
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>
</html>