package IHM;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import BLL.UserManager;
import BO.User;
import Exceptions.DALException;
import Exceptions.IHMException;

public class SeeUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserManager userManager = UserManager.getInstanceOf();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idUser = (int) request.getSession().getAttribute("id");
		
		try {
			User user = userManager.selectByID(idUser);
			
			if (user == null) {
				throw new IHMException();
			}
			
			request.setAttribute("id", idUser);
			request.setAttribute("pseudo", user.getPseudo());
			request.setAttribute("nom", user.getNom());
			request.setAttribute("prenom", user.getPrenom());
			request.setAttribute("email", user.getEmail());
			request.setAttribute("telephone", user.getTelephone());
			request.setAttribute("rue", user.getRue());
			request.setAttribute("codePostal", user.getCodePostal());
			request.setAttribute("ville", user.getVille());
			
			getServletContext().getNamedDispatcher("SeeUserJSP").forward(request,response);
		} catch (DALException e) { // problème de connexion à la bdd
			getServletContext().getNamedDispatcher("Index").forward(request,response); // retour à l'index
		} catch (IHMException e) { // requête d'un utilisateur inexistant
			getServletContext().getNamedDispatcher("Index").forward(request,response); // retour à l'index
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
