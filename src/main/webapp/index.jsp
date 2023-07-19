<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="BLL.UserManager"%>
<%@ page import="BO.User"%>

<!DOCTYPE html>
<html>
	<head>
<%@ include file="/WEB-INF/jspf/meta.jspf"%>
		<link href="style/index.css" rel="stylesheet" />
		<title>Les objets sont nos amis</title>
	</head>
	<body>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
		<h2>Liste des enchères</h2>
		<div class="container">
			<h3>Filtres :</h3>
			<form class="command">
				<div class="categories">
					<input type="text" name="recherche" placeholder="Le nom de l'article contient" />
					<div class="cate_container">
						<label for="categorie">Catégorie :</label>
						<select id="categorie" name="categorie">
							<option value="Informatique">Informatique</option>
							<option value="Ameublement">Ameublement</option>
							<option value="Vêtement">Vêtement</option>
							<option value="SportEtLoisir">Sport et loisir</option>
						</select>
					</div>
				</div>
				<div class="search-button">
					<input type="submit" value="Rechercher" name="search" />
				</div>
			</form>
			<div class="encheres">
				<div class="encadrer">
					<img src="#" alt="img1" />
					<div class="infos">
						<h4>PC gamer pour travailler</h4>
						<div class="prix">
							<p>
								<strong>Prix :</strong> 210 Points
							</p>
						</div>
						<div class="enchere">
							<p>
								<strong>Fin de l'enchère :</strong> 20 juillet 2023
							</p>
						</div>
						<div class="vendeur">
							<p>
								<strong>Vendeur :</strong><a href="#"></a> JOJO 44
							</p>
						</div>
					</div>
				</div>
				<div class="encadrer">
					<img src="#" alt="image2" />
					<div class="infos">
						<h4>Rocket stove pour riz et pâtes</h4>
						<div class="prix">
							<p>
								<strong>Prix :</strong> 185 points
							</p>
						</div>
						<div class="enchere">
							<p>
								<strong>Fin de l'enchère :</strong> 21 juillet 2023
							</p>
						</div>
						<form class="vendeur">
							<%
								int id = 1;
								UserManager userManager = UserManager.getInstanceOf();
								User user = userManager.selectByID(id);
							%>
							<p>
								<strong>Vendeur :</strong><a href="<%=request.getContextPath()%>/Profil?id=<%=id%>"><%=user.getPseudo()%></a>
							</p>
						</form>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>

