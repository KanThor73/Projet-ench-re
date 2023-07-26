package IHM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BLL.AuctionManager;


@WebServlet("/EditAuctionServlet")
public class EditAuctionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private AuctionManager auctionManager = AuctionManager.getInstanceOf();
       
       
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	//recuperation ID de l'enchère a modifier
    	
    	
    	
    	
		
		
		request.getRequestDispatcher("/WEB-INF/JSP/EditAuction.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
