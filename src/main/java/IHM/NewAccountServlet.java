package IHM;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BLL.UserManager;
import BO.User;
import Exceptions.BLLException;
import Exceptions.DALException;

/*
 * TODO empêcher un utilisateur connecté de venir ici
 */

public class NewAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserManager userMgr = UserManager.getInstanceOf();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getNamedDispatcher("NewAccountJSP").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// Récupération des paramètres du formulaire de newAccount.jsp
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telNum = request.getParameter("telephone");
		if (telNum != null && telNum.isEmpty()) { // si telNum est vide
			telNum = null; // passage du telNum à null pour bdd
		}
		String rue = request.getParameter("rue");
		String codePoste = request.getParameter("postal");
		String ville = request.getParameter("ville");
		String mdp = request.getParameter("mdp");
		String mdp2 = request.getParameter("mdp2");
		
		if (!mdp.equals(mdp2)) {
			request.setAttribute("msgErreur", "Les mots de passe ne coïncident pas");
			getServletContext().getNamedDispatcher("NewAccountJSP").forward(request, response);
		} else {
			
			User newUser = new User(pseudo, nom, prenom, email, telNum, rue, codePoste, ville, mdp, false); // pas administrateur
			
			try {
				userMgr.insert(newUser); // ajout de l'utilisateur
				int id = userMgr.getId(pseudo); // récupération de l'id
				request.getSession().setAttribute("id", id); // set up de l'id, id non null = connecté
				response.sendRedirect("IndexServlet"); // retourne à l'accueil si bon déroulement
			} catch (DALException e) {
				request.setAttribute("msgErreur","Problème d'accès aux données");
				getServletContext().getNamedDispatcher("NewAccountJSP").forward(request, response);
			} catch (BLLException e) {
				request.setAttribute("msgErreur", e.getMessage());
				getServletContext().getNamedDispatcher("NewAccountJSP").forward(request, response);
			}
		}
	}
}
