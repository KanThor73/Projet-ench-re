package IHM;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BLL.UserManager;
import BO.User;
import Exceptions.BLLException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserManager userManag = UserManager.getInstanceOf();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		String mdps = request.getParameter("password");
		String login = request.getParameter("username");
		User user = new User(login, mdps); // a tester car comparaison uniquement de ces deux champs pour la connexion
		List<User> users = userManag.selectAll();

		// change l'attribut de session en true si l'identifiant fait parti de la bdd pour un control positif par le filtre
		
		if (request.getParameter("signIn") != null) {
			request.setAttribute("msgErreur", " ");
			if (mdps == null || mdps.isEmpty() == true || login == null || login.isEmpty() == true) {
				request.setAttribute("msgErreur", "Les deux champs doivent obliogatoirement etre renseignes");
			} else {
				for (User userCheck : users) {
					if (user.getPseudo().equals(userCheck.getPseudo()) && user.getMotDePasse().equals(userCheck.getMotDePasse())) {
						session.setAttribute("userOk", true);
						int userId = userCheck.getNoUser();
						System.out.println("Utilisateur" + userId + "connecte");
					}else {
						request.setAttribute("msgErreur", "Utilisateur non valide, veuillez vous creer un profil de connexion");
					}
				}
			}
		} else if (request.getParameter("signUp") != null) {
			request.setAttribute("msgErreur", " ");
			for (User user1 : users) {
				if (user.equals(user1)) {
					request.setAttribute("msgErreur", "L'utilisateur existe deja");
				} else {
					session.setAttribute("userOk", true);
					try {
						userManag.insert(user);
					} catch (BLLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		System.out.println("Le statut de connexion est : " + session.getAttribute("userOk"));
		request.getRequestDispatcher("/WEB-INF/ ").forward(request, response); // Renseigner la jsp quand elle sera cree
	}

}
