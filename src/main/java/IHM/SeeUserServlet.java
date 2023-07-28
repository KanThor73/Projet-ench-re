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
		request.setCharacterEncoding("UTF-8");
		int idUser = Integer.parseInt(request.getParameter("id"));
		
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
			
			// si page perso de la personne connecté
			if (request.getSession().getAttribute("id") != null && idUser == (int) request.getSession().getAttribute("id")) {
				request.setAttribute("credit", user.getCredit());
			}
			
			getServletContext().getNamedDispatcher("SeeUserJSP").forward(request,response);
		} catch (DALException e) { // problème de connexion à la bdd
			response.sendRedirect("IndexServlet"); // retour à l'index
		} catch (IHMException e) { // requête d'un utilisateur inexistant
			response.sendRedirect("IndexServlet"); // retour à l'index
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}