<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <%@ include file="/WEB-INF/jspf/meta.jspf"%>
    <link href="style/connect.css" rel="stylesheet" />
    <title>Connexion</title>
  </head>
  <body>
    <%@ include file="/WEB-INF/jspf/header.jspf"%>
    <div class="sub-contain">
      <div class="container">
        <form action="<%=request.getContextPath()%>/Connect" method="post">
          <!--label utilisateur + input="txt" -->
          <div class="user">
            <label for="username">Utilisateur :</label>
            <input type="text" id="username" name="username" required />
          </div>

          <!--label password + input="txt" -->
          <div class="password">
            <label for="password">Mot de passe :</label>
            <input type="text" id="password" name="password" required />
          </div>

          <div class="cnx">
            <div class="button-container">
              <input type="submit" value="Connexion" />
            </div>
            <div class="forgot-password">
              <div class="remember-me">
                <input type="checkbox" id="remember" name="remember" />
                <label for="remember">Se souvenir de moi</label>
              </div>
              <a href="#">Mot de passe oublié</a>
            </div>
          </div>
        </form>
        <div class="create-account-button">
          <button><a>Créer un compte</a></button>
        </div>
        <div>
          <label>${msgErreur}</label>
        </div>
      </div>
    </div>
  </body>
</html>
