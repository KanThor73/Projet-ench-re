package IHM;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import BLL.UserManager;
import BO.User;
import Exceptions.DALException;
import Util.Hashing;

@WebServlet("/EditerProfil")
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserManager userManager = UserManager.getInstanceOf();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int idUser = (int) request.getSession().getAttribute("id");

		try {
			User user = userManager.selectByID(idUser);

			// transforme le mot de passe en * ==> non hackable depuis le web
			String mdpClair = user.getMotDePasse();

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < mdpClair.length(); i++) {
				sb.append("* ");
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
			request.setAttribute("mdp", sb.toString());
			request.setAttribute("credit", String.valueOf(user.getCredit()));

		} catch (DALException e) {
			e.printStackTrace();
			request.setAttribute("msgErreur", e.getMessage());
		}
		request.getRequestDispatcher("/WEB-INF/JSP/EditProfile.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// recuperer le nouveau mot de passe et sa confirmation
		UserManager userMg = UserManager.getInstanceOf();

		String mdp = request.getParameter("mdp");// mot de passe actuel de l'utilisateur ==> utilise pour la suppression
		String mdp1 = request.getParameter("mdp1");
		String mdp2 = request.getParameter("mdp2");
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String tel = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String cp = request.getParameter("postal");
		String ville = request.getParameter("ville");

		if (request.getParameter("update") != null) { // l'utilisateur a clique sur modifier

			// tester si l'utilisateur veut changer son mdp ou pas et tester si le mot de
			// passe a changer est identique a sa confirmation et different de l'ancien

			// recuperer les valeurs a mofifiees saisies par l'ulisateur et les valeurs non
			// modifiees

			// a modifier quand nous auront la servlet index avec l'id de session

			try {
				User user1 = userMg.selectByPseudo(pseudo);
				String[] mdpTab = Hashing.hashPassword(mdp);// hashing
				if (mdp1.isEmpty() && mdp2.isEmpty()) { // l'utilisateur ne veut pas modifier son mdps
//					System.out.println(user1.getMotDePasse());
//					System.out.println(mdp);
					if (Hashing.ckeckPassword(user1.getMotDePasse(), mdpTab[0], user1.getSalt())) {// verif mdps
						User user2 = new User((user1.getNoUser()), pseudo, nom, prenom, email, tel, rue, cp, ville, mdpTab[0],
								user1.getCredit(), (user1.estAdministrateur() ? 1 : 0),mdpTab[1]);
						userMg.update(user2);
						request.setAttribute("msg", "Profil modifie avec succes!");
					} else {
						request.setAttribute("msgErreur",
								"Attention - Le mot de passe est obligatoire pour la modification");
					}
				} else if (Hashing.ckeckPassword(user1.getMotDePasse(), mdpTab[0], user1.getSalt()) && !mdp1.isEmpty() && !mdp2.isEmpty() && mdp1.equals(mdp2) && !mdp1.equals(mdp)) {// si l'utilisateur veut modifier son mdps et qu'il remplit les conditions
					User user2 = new User((user1.getNoUser()), pseudo, nom, prenom, email, tel, rue, cp, ville, mdpTab[0],
							user1.getCredit(), (user1.estAdministrateur() ? 1 : 0),mdpTab[1]);
					userMg.update(user2);
					request.setAttribute("msg", "Profil modifie avec succes!");
				} else if (!mdp1.isEmpty() && mdp2.isEmpty() || mdp1.isEmpty() && !mdp2.isEmpty()) {
					request.setAttribute("msgErreur",
							"Attention - Le mot de passe et sa confirmation doivent être identiques");
				} else if (mdp1 != mdp2) {
					request.setAttribute("msgErreur",
							"Attention - Le mot de passe et sa confirmation doivent être identiques");
				} else if (mdp1 == mdp) {
					request.setAttribute("msgErreur",
							"Attention - Le nouveau mot de passe doit être différent de l'ancien");
				}
			} catch (DALException e) {
				e.printStackTrace();
				request.setAttribute("msgErreur", e.getMessage());
			}
			doGet(request, response); // renvoie dans la methode doGet pour mettre a jour les champs modifies et
										// afficher tous les champs
		} else if (request.getParameter("delete") != null) {// l'utilisateur a clique sur supprimer
			try {
				
				User user1 = userMg.selectByPseudo(pseudo);
				
				if (mdp != null && (user1.getMotDePasse()).equals(mdp) && (mdp1 == null || mdp1.equals("")) && (mdp2 == null || mdp2.equals(""))) { // verif mdps
					userMg.delete(user1.getNoUser());
					request.getSession().setAttribute("id", null);
					response.sendRedirect("IndexServlet");
				} else if (mdp == null || mdp.equals("") || Hashing.ckeckPassword(user1.getMotDePasse(), mdp, user1.getSalt())) {
					request.setAttribute("msgErreur",
							"Attention - Le nouveau mot de passe est obligatoire pour la suppression");
					doGet(request, response);
				} else if ((mdp1 != null || !mdp1.equals("")) && (mdp2 != null || !mdp2.equals(""))) {
					request.setAttribute("msgErreur",
							"Attention - Modification du mot de passe impossible pour la suppression");
					doGet(request, response);
				}
			} catch (DALException e) {
				e.printStackTrace();
				request.setAttribute("msgErreur", e.getMessage());
				request.setAttribute("msg",
						"Impossible de supprimer ce profil, veuillez vous rapprocher d'un administrateur.");
			}

		} else if (request.getParameter("cancel") != null) {// annulation des modifs
			response.sendRedirect("IndexServlet");
		}
	}
}
