<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="BLL.ArticleManager"%>
<%@ page import="BLL.AuctionManager"%>
<%@ page import="BO.Article"%>
<%@ page import="BO.Auction"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Date"%>

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
	<%
	int id = 1;
	ArticleManager articleMgr = ArticleManager.getInstanceOf();
	Article article = articleMgr.selectByID(id);
	
	AuctionManager auctionMgr = AuctionManager.getInstanceOf();
	List<Auction> auctions = auctionMgr.selectByArticle(id);
	%>
    <div class="container">
        <h1>Details vente</h1>
        <div class="sub-container">
                <div class="fragment">
                    <label for="nom-article">Article : <%=article.getNom()%></label>
                </div>
                <div class="fragment">
               	    <label for="Description">Description : <%=article.getDescription()%></label>
               	</div>
                <div class="fragment">
                    <label for="catégories">Catégorie : <%=article.getCategorie()%></label>
                </div>
                <div class="fragment">
                    <label for="Meilleur_Offres">Meilleur offre :</label>
                </div>
                <div class="fragment">
                    <label for="Mise_A_Prix">Mise à prix : </label>
                </div>
                <div class="fragment">
                    <label for="Fin_enchère">Fin de l'enchère : <%=article.getDateFin().toString()%></label>
                </div>                 
        </div>
    </div>

</body>
</html>