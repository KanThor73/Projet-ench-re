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
import Exceptions.DALException;

@WebServlet("/EditProfileServlet")
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserManager userManager = UserManager.getInstanceOf();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		int idUser = (int) session.getAttribute("id");

		try {
			User user = userManager.selectByID(idUser);

			// transforme le mot de passe en * ==> non hackable depuis le web
			String mdpClair = user.getMotDePasse();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < mdpClair.length(); i++) {
				sb.append("*");
			}

			// envoie les valeur des inputs a la jsp pour le preremplissage

			request.setAttribute("id", user.getNoUser());
			request.setAttribute("pseudo", user.getPseudo());
			request.setAttribute("nom", user.getNom());
			request.setAttribute("prenom", user.getPrenom());
			request.setAttribute("email", user.getEmail());
			request.setAttribute("telephone", user.getTelephone());
			request.setAttribute("rue", user.getRue());
			request.setAttribute("codePostal", user.getCodePostal());
			request.setAttribute("ville", user.getVille());
			request.setAttribute("mdps", sb.toString());
			request.setAttribute("credit", String.valueOf(user.getCredit()));

		} catch (DALException e) {
			e.printStackTrace();
			request.setAttribute("msgErreur", e.getMessage());
		}

		request.getRequestDispatcher("/WEB-INF/JSP/EditProfile.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// recuperer le nouveau mot de passe et sa confirmation
		UserManager userMg = UserManager.getInstanceOf();

		String mdp = request.getParameter("mdp");// mot de passe actuel de l'utilisateur ==> utilise pour la suppression
		String mdp1 = request.getParameter("mdp1");
		String mdp2 = request.getParameter("mdp2");
		String pseudo = request.getParameter("pseudo");

		if (request.getParameter("update") != null) { // l'utilisateur a clique sur modifier

			// tester si l'utilisateur veut changer son mdp ou pas et tester si le mot de
			// passe a changer est identique a sa confirmation et different de l'ancien

			// recuperer les valeurs a mofifiees saisies par l'ulisateur et les valeurs non
			// modifiees
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String email = request.getParameter("email");
			String tel = request.getParameter("telephone");
			String rue = request.getParameter("rue");
			String cp = request.getParameter("postal");
			String ville = request.getParameter("ville");

			// a modifier quand nous auront la servlet index avec l'id de session

			try {
				if (mdp1.isEmpty() && mdp2.isEmpty()) { // l'utilisateur ne veut pas modifier son mdps
					User user1 = userMg.selectByPseudo(pseudo);
					if (user1.getMotDePasse() == mdp) {// verif mdps
						User user2 = new User((user1.getNoUser()), pseudo, nom, prenom, email, tel, rue, cp, ville, mdp,
								user1.getCredit(), (user1.estAdministrateur() ? 1 : 0));
						userMg.update(user2);
						request.setAttribute("msg", "Profil modifie avec succes!");
					} else {
						request.setAttribute("msgErreur",
								"Attention - Le mot de passe est obligatoire pour la modification");
					}
				} else if (!mdp1.isEmpty() && !mdp2.isEmpty() && mdp1 == mdp2 && mdp1 != mdp) {// l'utilisateur veut
																								// modifier son mdps et
																								// qu'il remplit les
																								// conditions
					User user1 = userMg.selectByPseudo(pseudo);
					User user2 = new User((user1.getNoUser()), pseudo, nom, prenom, email, tel, rue, cp, ville, mdp1,
							user1.getCredit(), (user1.estAdministrateur() ? 1 : 0));
					userMg.update(user2);
					request.setAttribute("msg", "Profil modifie avec succes!");
				} else if (!mdp1.isEmpty() && mdp2.isEmpty() || mdp1.isEmpty() && !mdp2.isEmpty()) {
					request.setAttribute("msgErreur",
							"Attention - Le mot de passe et sa confirmation doivent etre identique");
				} else if (mdp1 != mdp2) {
					request.setAttribute("msgErreur",
							"Attention - Le mot de passe et sa confirmation doivent etre identique");
				} else if (mdp1 == mdp) {
					request.setAttribute("msgErreur",
							"Attention - Le nouveau mot de passe doit etre different de l'ancien");
				}
			} catch (DALException e) {
				e.printStackTrace();
				request.setAttribute("msgErreur", e.getMessage());
			}
			doGet(request, response); // renvoie dans la methode doGet pour mettre a jour les champs modifies et
										// afficher tous les champs
		} else if (request.getParameter("delete") != null) {// l'utilisateur a clique sur supprimer

			if (mdp != null && mdp != "") {// tester le mdp pour autoriser la suppression
				try {
					User user1 = userMg.selectByPseudo(pseudo);
					if (user1.getMotDePasse() == mdp) { // verif mdps
						userMg.delete(user1.getNoUser());
						request.setAttribute("msg", "Profil supprime avec succes!");
						getServletContext().getNamedDispatcher("Index").forward(request, response);
					}
				} catch (DALException e) {
					e.printStackTrace();
					request.setAttribute("msgErreur", e.getMessage());
					request.setAttribute("msg",
							"Impossible de supprime ce profil, veuillez vous rapprocher d'un administrateur.");
				}
			}

			doGet(request, response); // renvoie dans la methode doGet pour mettre a jour les champs modifies et
										// afficher tous les champs
		} else if (request.getParameter("cancel") != null) {// annulation des modifs
			getServletContext().getNamedDispatcher("Index").forward(request, response);
		}
	}

}
