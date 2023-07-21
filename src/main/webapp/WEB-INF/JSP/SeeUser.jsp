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
			if (session.getAttribute("id") != null && session.getAttribute("id") == request.getAttribute("id")) {
			%>
			<h3>MON PROFIL</h3>
			<%
			} else {
			int id = Integer.parseInt(request.getParameter("id"));
			UserManager userManager = UserManager.getInstanceOf();
			User user = userManager.selectByID(id);
			String pseudo = user.getPseudo();
			%>
			<h3>PROFIL DE ${pseudo}</h3>
			<%
			}
			%>
			<div class="container">
				<label for="pseudo">Pseudo : ${pseudo}</label> <label for="nom">Nom
					: ${nom}</label> <label for="prenom">Prénom : ${prenom}</label> <label
					for="email">Email : ${email}</label> <label for="telephone">Téléphone
					: ${telephone}</label> <label for="rue">Rue : ${rue}</label> <label
					for="codePostal">Code Postal : ${codePostal}</label> <label
					for="ville">Ville : ${ville}</label>

			</div>
		</div>
	</div>
	<div class="formFooter">
		<%
		if (session.getAttribute("id") != null && session.getAttribute("id") == request.getAttribute("id")) { // profil du user connecté
		%>
		<form class="vendeur">
			<%
			int id = Integer.parseInt(request.getParameter("id"));
			UserManager userManager = UserManager.getInstanceOf();
			User user = userManager.selectByID(id);
			%>
			<a href="<%=request.getContextPath()%>/EditProfileServlet?id=<%=id%>">MODIFIER</a>
		</form>
		<%
		}
		%>
	</div>
</body>
</html>