<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/jspf/meta.jspf"%>
<link href="style/SeeUser.css" rel="stylesheet">
<title>User</title>
</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<div class="user-container">
		<div class="user-infos">
			<%
			if (session.getAttribute("id") != null && session.getAttribute("id").toString().equals(request.getAttribute("id").toString())) {
			%>
			<h3>MON PROFIL</h3>
			<%
			} else {
			%>
			<h3>PROFIL DE ${pseudo}</h3>
			<%
			}
			%>
			<div class="container">
				<label for="pseudo"><strong>Pseudo : </strong>${pseudo}</label> <label for="nom"><strong>Nom
					: </strong>${nom}</label> <label for="prenom"><strong>Prénom : </strong>${prenom}</label> <label
					for="email"><strong>Email : </strong>${email}</label> <label for="telephone"><strong>Téléphone
					: </strong>${telephone}</label> <label for="rue"><strong>Rue : </strong>${rue}</label> <label
					for="codePostal"><strong>Code Postal : </strong>${codePostal}</label> <label
					for="ville"><strong>Ville : </strong>${ville}</label>
				
				<%
				if (session.getAttribute("id") != null && session.getAttribute("id") == request.getAttribute("id")) {
				%>
				<label for="credit"><strong>Credit : </strong>${credit}</label>
				<%
				}
				%>

			</div>
		</div>
	</div> 
	<div class="formFooter">
		<%
		if (session.getAttribute("id") != null && session.getAttribute("id").toString().equals(request.getAttribute("id").toString())) { // profil du user connecté
		%>
		<form class="vendeur">
			<a href="<%=request.getContextPath()%>/EditerProfil?id=<%=request.getParameter("id")%>">MODIFIER</a>
		</form>
		<%
		}
		%>
	</div>
</body>
</html>