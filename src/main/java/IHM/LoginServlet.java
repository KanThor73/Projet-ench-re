package IHM;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BLL.UserManager;
import Exceptions.DALException;

/*
 * TODO empêcher un utilisateur connecté de venir ici
 * Il risquerait de se déconnecter en tapant des logins incorrects
 */

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserManager userMgr = UserManager.getInstanceOf();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getServletContext().getNamedDispatcher("ConnectJSP").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Récupération des paramètres du formulaire de connect.jsp
		String mdps = request.getParameter("password");
		String login = request.getParameter("username");
		
		if (userMgr.checkMdp(login, mdps)) { // si pseudo et mot de passe concordent
			try {
				int id = userMgr.getId(login); // récupération de l'id
				request.getSession().setAttribute("id", id); // set up de l'id, id non null = connecté
				getServletContext().getNamedDispatcher("Index").forward(request, response); // retour à l'index
			} catch (DALException e) {
				request.setAttribute("msgErreur","Problème d'accès aux données");
				getServletContext().getNamedDispatcher("ConnectJSP").forward(request, response);
			}
		} else {
			if (userMgr.checkPseudo(login)) { // si le login est correct
				request.setAttribute("msgErreur","Mot de passe incorrect");
			} else {
				request.setAttribute("msgErreur","Utilisateur inconnu, veuillez créer un compte");
			}
			getServletContext().getNamedDispatcher("ConnectJSP").forward(request, response); // retourne à la connexion
		}
	}
}
