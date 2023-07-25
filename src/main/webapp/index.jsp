<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="BLL.UserManager"%>
<%@ page import="BO.User"%>
<%@ page import="BLL.CategorieManager"%>
<%@ page import="BLL.ArticleManager"%>
<%@ page import="BO.Article"%>
<%@ page import="Exceptions.DALException"%>
<%@ page import="java.util.List"%>

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
		<div class="title">>
			<h1>Liste des enchères</h1>
		</div>
		<div class="container">
			<h3>Filtres :</h3>
			<form class="command" action="<%=request.getContextPath()%>" method="get">
				<div class="categories">
					<input type="text" name="recherche"
						placeholder="Le nom de l'article contient" />
					<div class="cate_container">
						<label for="categorie">Catégorie :</label>
						<select name="categorie" id="cat" size="1" >
						<%
						CategorieManager catMgr = CategorieManager.getInstanceOf();
						for (String cat : catMgr.selectAll()) {
						%>
							<option value="<%=cat%>"><%=cat%></option>
						<%}%>
						</select>
					</div>
				</div>
				<div class="search-button">
					<input type="submit" value="Rechercher" name="search" />
				</div>
			</form>
			<div class="encheres">
			<%
			try {
				ArticleManager articleMgr = ArticleManager.getInstanceOf();
				UserManager userMgr = UserManager.getInstanceOf();
				List<Article> articles = (request.getParameter("categorie") == null) ?
										articleMgr.selectAll() :
										articleMgr.selectByCategory(request.getParameter("categorie"));
				for (Article art : articles) {
			%>
				<div class="encadrer">
					<img src="img/imgArticle.svg" alt="img1" />
					<div class="infos">
						<a href="<%=request.getContextPath()%>/Auction?id=<%=art.getNoArticle()%>"><h4><%=art.getNom()%></h4></a>
						<div class="prix">
							<p>
								<strong>Prix :</strong> <%=(art.getPrixVente() == null) ? art.getPrixInit() : art.getPrixVente()%>
							</p>
						</div>
						<div class="enchere">
							<p>
								<strong>Fin de l'enchère :</strong> <%=art.getDateFin().toString()%>
							</p>
						</div>
						<div class="vendeur">
							<p>
								<%
								User user = userMgr.selectByID(art.getOwnerId());
								int idSeller = user.getNoUser();
								String pseudoSeller = user.getPseudo();
								%>
								<strong>Vendeur :</strong><a href="<%=request.getContextPath()%>/Profil?id=<%=idSeller%>"><%=pseudoSeller%></a>
							</p>
						</div>
					</div>
				</div>
				<%}
				} catch (DALException e) {
				/* ne rien faire */
				}%>
			</div>
		</div>
	</div>
</body>
</html>

