package IHM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BLL.UserManager;
import BO.User;

@WebServlet("/EditProfileServlet")
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserManager userManager = UserManager.getInstanceOf();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int idUser = Integer.parseInt(request.getParameter("id"));

		User user = userManager.selectByID(idUser);

		request.setAttribute("id", idUser);
		request.setAttribute("pseudo", user.getPseudo());
		request.setAttribute("nom", user.getNom());
		request.setAttribute("prenom", user.getPrenom());
		request.setAttribute("email", user.getEmail());
		request.setAttribute("telephone", user.getTelephone());
		request.setAttribute("rue", user.getRue());
		request.setAttribute("codePostal", user.getCodePostal());
		request.setAttribute("ville", user.getVille());

		request.getRequestDispatcher("/WEB-INF/JSP/EditProfile.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		faire css rajouter champ et enlever champs en trop
//		tester les mdps et retourner un bool pour rajouter une condition dans le if
		
		if (request.getParameter("update") != null) {

		} else if (request.getParameter("delete") != null) {

		}
		request.getRequestDispatcher("/WEB-INF/JSP/EditProfile.jsp").forward(request, response);
	}

}
