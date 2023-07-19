package IHM;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BLL.UserManager;
import BO.User;
import Exceptions.BLLException;

public class NewAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserManager userMgr = UserManager.getInstanceOf();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("msgErreur"); // reset message erreur
		getServletContext().getNamedDispatcher("NewAccountJSP").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Récupération des paramètres du formulaire de newAccount.jsp
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telNum = request.getParameter("telephone");
		if (telNum != null && telNum.isEmpty()) {
			telNum = null;
		}
		String rue = request.getParameter("rue");
		String codePoste = request.getParameter("postal");
		String ville = request.getParameter("ville");
		String mdp = request.getParameter("mdp");
		String mdp2 = request.getParameter("mdp2");
		
		HttpSession session = request.getSession();
		session.removeAttribute("msgErreur"); // reset
		
		if (!mdp.equals(mdp2)) {
			session.setAttribute("msgErreur", "Les mots de passe ne concordent pas");
			getServletContext().getNamedDispatcher("NewAccountJSP").forward(request, response);
		} else {
			
			User newUser = new User(pseudo, nom, prenom, email, telNum, rue, codePoste, ville, mdp, false); // pas administrateur
			
			try {
				userMgr.insert(newUser); // ajout de l'utilisateur
				session.setAttribute("isConnected", true); // connexion automatique
				session.setAttribute("pseudo", pseudo);
				getServletContext().getNamedDispatcher("Index").forward(request, response); // retourne à l'accueil si ok
			} catch (BLLException e) {
				session.setAttribute("msgErreur", e.getMessage()); // ajoute "BLLException - " TODO à enlever
				getServletContext().getNamedDispatcher("NewAccountJSP").forward(request, response);
			}
		}
	}
}
