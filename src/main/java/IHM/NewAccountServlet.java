package IHM;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BLL.UserManager;
import BO.User;
import Exceptions.BLLException;

@WebServlet("/NewAccountServlet")
public class NewAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserManager UserMgr = UserManager.getInstanceOf();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("get");
		request.getRequestDispatcher("/WEB-INF/JSP/newAccount.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Je suis là");
		
		// Récupération des paramètres du formulaire de newAccount.jsp
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telNum = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String codePoste = request.getParameter("postal");
		String ville = request.getParameter("ville");
		String mdp = request.getParameter("mdp");
		String mdp2 = request.getParameter("mdp2");
		
		System.out.println(mdp + mdp2);
		
		// Vérification des paramètres suivant les conditions de la base de données
		StringBuilder errorMsg = new StringBuilder("");
		if (pseudo == null || pseudo.isEmpty() || pseudo.length() > 30) {
			errorMsg.append("Pseudo incorrect<br>");
		}
		if (nom == null || nom.isEmpty() || nom.length() > 30) {
			errorMsg.append("Nom incorrect<br>");
		}
		if (prenom == null || prenom.isEmpty() || prenom.length() > 30) {
			errorMsg.append("Prenom incorrect<br>");
		}
		if (email == null || email.isEmpty() || email.length() > 50) {
			errorMsg.append("Email incorrect<br>");
		}
		if (telNum != null && telNum.length() > 15) {
			errorMsg.append("Numéro de téléphone incorrect<br>");
		}
		if (rue == null || rue.isEmpty() || rue.length() > 30) {
			errorMsg.append("Rue incorrecte<br>");
		}
		if (codePoste == null || codePoste.isEmpty() || codePoste.length() > 10) {
			errorMsg.append("Code postal incorrect<br>");
		}
		if (ville == null || ville.isEmpty() || ville.length() > 30) {
			errorMsg.append("Ville incorrect<br>");
		}
		if (mdp == null || mdp.length() < 1 || mdp.length() > 40 ||
			mdp2 == null || mdp2.length() < 1 || mdp2.length() > 40 || //pas sur d'avoir besoin de celui ci comme les deux doivent etre identique?
			!mdp.toString().equals(mdp2.toString())) { // si la confirmation est incorrecte
			errorMsg.append("Mot de passe incorrect<br>");
		}
		
		// Vérification de l'unicité du pseudo et du mail
		if (UserMgr.checkPseudo(pseudo)) { // si le pseudo n'est pas disponible
			errorMsg.append("Pseudo non disponible<br>");
		}
		if (UserMgr.checkEmail(email)) { // si le mail n'est pas disponible
			errorMsg.append("Adresse mail dèjà utilisée<br>");
		}
		
		HttpSession session = request.getSession();
		
//		if (errorMsg.isEmpty()) {  n'accepte pas les SB
		
		if (errorMsg.length() == 0) { // s'il n'y a aucune erreur
			User newUser = new User(pseudo, nom, prenom, email, telNum, rue, codePoste, ville, mdp, false); // pas administrateur
			
			try {
				UserMgr.insert(newUser); // ajout de l'utilisateur à la bdd
				System.out.println("Oui 1");
			} catch (BLLException e) {
				System.out.println("Non 1");
				System.out.println(errorMsg);
				e.printStackTrace();
				errorMsg.append("Problème de connexion à la base de données<br>");
				session.setAttribute("msgErreur", errorMsg.toString()); // envoi du message d'erreur
				request.getRequestDispatcher("/WEB-INF/JSP/newAccount.jsp").forward(request, response);
			}
			session.setAttribute("isConnected", true); // connnecte l'utilisateur
			session.setAttribute("pseudo", pseudo);
			
			// connecter user
			request.getRequestDispatcher("/index.jsp").forward(request, response); // retourne à l'accueil
		} else {
			System.out.println("Non2");
			System.out.println(errorMsg);
			session.setAttribute("msgErreur", errorMsg.toString()); // envoi du message d'erreur
			request.getRequestDispatcher("/WEB-INF/JSP/newAccount.jsp").forward(request, response);
		}
	}
}
