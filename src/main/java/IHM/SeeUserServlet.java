package IHM;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import BLL.UserManager;
import BO.User;

public class SeeUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idUser = Integer.parseInt(request.getParameter("id"));
		
		System.out.println(idUser);
		UserManager userManager = UserManager.getInstanceOf();
		User user = userManager.selectByID(idUser);
		
		request.setAttribute("pseudo", user.getPseudo());
		request.setAttribute("nom", user.getNom());
		request.setAttribute("prenom", user.getPrenom());
		request.setAttribute("email", user.getEmail());
		request.setAttribute("telephone", user.getTelephone());
		request.setAttribute("rue", user.getRue());
		request.setAttribute("codePostal", user.getCodePostal());
		request.setAttribute("ville", user.getVille());
		
		getServletContext().getNamedDispatcher("SeeUserJSP").forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
