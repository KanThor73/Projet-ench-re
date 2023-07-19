package IHM;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BLL.UserManager;
import BO.User;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserManager UserManag = UserManager.getInstanceOf();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getServletContext().getNamedDispatcher("ConnectJSP").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("isConnected",false); // a placer dans la page d'acceuil

		String mdps = request.getParameter("password");
		String login = request.getParameter("username");
		System.out.println("mot de passe : "+ mdps + "login : " + login);
		User user = new User(login, mdps); // a tester car comparaison uniquement de ces deux champs pour la connexion
		
		if (UserManag.checkMdp(login, mdps)) {
			session.setAttribute("isConnected", true);
			request.setAttribute("pseudo", "Connecte en tant que " + login);
			getServletContext().getNamedDispatcher("Index").forward(request, response);
			int userId = user.getNoUser();
			System.out.println("Utilisateur : " + userId + " connecte");
		} else {
			request.setAttribute("msgErreur","Utilisateur non valide, veuillez vous creer un profil de connexion");
			System.out.println("Aucune correspondance avec la BDD");
		}

		System.out.println("Le statut de connexion est : " + session.getAttribute("isConnected"));
		getServletContext().getNamedDispatcher("ConnectJSP").forward(request, response); // Renseigner la jsp acceuil quand elle
																				// sera cree
	}
}
