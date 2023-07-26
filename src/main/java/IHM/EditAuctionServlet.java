package IHM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BLL.AuctionManager;
import BO.Auction;
import Exceptions.DALException;

@WebServlet("/EditAuctionServlet")
public class EditAuctionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AuctionManager auctionManager = AuctionManager.getInstanceOf();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// recuperation ID de l'enchère a modifier

		String idAuctionString = request.getParameter("id");
		if (idAuctionString != null && !idAuctionString.isEmpty()) {
			try {
				int idAuction = Integer.parseInt(idAuctionString);
				Auction auction = auctionManager.selectByID(idAuction, idAuction);

				if (auction != null) {
					// Envoyer les informations de l'enchère à la JSP de modification
					request.setAttribute("id", auction.getNoArticle());
					request.setAttribute("nom", auction.getNoUtilisateur());
					

					request.getRequestDispatcher("/WEB-INF/JSP/EditAuction.jsp").forward(request, response);
					return;
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
				request.setAttribute("msgErreur", e.getMessage());
			} catch (DALException e) {
				e.printStackTrace();
			}
		}

		request.getRequestDispatcher("/WEB-INF/JSP/EditAuction.jsp").forward(request, response);
	}

	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String idAuctionString = request.getParameter("id");
		String newNom = request.getParameter("nom");
		String newDesc = request.getParameter("desc");
		String newCat = request.getParameter("cat");
		

		doGet(request, response);
	}

}
