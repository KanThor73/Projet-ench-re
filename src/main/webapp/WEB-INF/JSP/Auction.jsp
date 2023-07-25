<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="BLL.ArticleManager"%>
<%@ page import="BLL.AuctionManager"%>
<%@ page import="BO.Article"%>
<%@ page import="BO.Auction"%>
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
	<%int id = 1;
	ArticleManager articleMgr = ArticleManager.getInstanceOf();
	Article article = articleMgr.selectByID(id);
	
	AuctionManager auctionMgr = AuctionManager.getInstanceOf();
	Auction auction = auctionMgr.selectByUser(id);%>
	<%=auction.getMontantEnchere()%>
    <div class="container">
        <h1>Details vente</h1>

        <div class="FormContainer">
            <div class="FormBody">
                <div class="FormFragment">
                    <label for="nom-article">Article : </label> <%=article.getNom()%>
                    <div class="FormFragment">
                        <label for="Description">Description : <%=article.getDescription()%></label>
                        <div class="FormFragment">
                            <label for="catégories">Catégorie : <%=article.getCategorie()%></label>
                            <div class="FormFragment">
                                <label for="Meilleur_Offres">Meilleur offre :</label>
                                <div class="FormFragment">
                                    <label for="Mise_A_Prix">Mise à prix : </label>
                                    <div class="FormFragment">
                                        <label for="Fin_enchère">Fin de l'enchère : <%= article.getDateFin().toLocaleString()%></label>
                                        
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