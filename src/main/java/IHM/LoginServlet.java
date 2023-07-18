package IHM;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BLL.UserManager;
import BO.User;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserManager userManag = UserManager.getInstanceOf();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		String mdps = request.getParameter("password");
		String login = request.getParameter("username");
		User user = new User(login, mdps); // a tester car comparaison uniquement de ces deux champs pour la connexion
		System.out.println(user.toString());
		List<User> users = userManag.selectAll();
		request.setAttribute("msgErreur", " ");

		// change l'attribut de session en true si l'identifiant fait parti de la bdd

		if (mdps == null || mdps.isEmpty() == true || login == null || login.isEmpty() == true) {
			request.setAttribute("msgErreur", "Les deux champs doivent obliogatoirement etre renseignes");
		} else {
			for (User userCheck : users) {
				if (user.getPseudo().equals(userCheck.getPseudo())
						&& user.getMotDePasse().equals(userCheck.getMotDePasse())) {
					session.setAttribute("userOk", true);
					int userId = userCheck.getNoUser();
					System.out.println("Utilisateur" + userId + "connecte");
				} else {
					request.setAttribute("msgErreur","Utilisateur non valide, veuillez vous creer un profil de connexion");
					System.out.println("Aucune correspondance avec la BDD");
				}
			}
		}

		System.out.println("Le statut de connexion est : " + session.getAttribute("userOk"));
		request.getRequestDispatcher("index.jsp").forward(request, response); // Renseigner la jsp acceuil quand elle
																				// sera cree
	}
}
