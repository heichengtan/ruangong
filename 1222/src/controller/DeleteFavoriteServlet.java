package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.FavoriteCountryBean;
import model.FavoriteCountryDAO;
import model.MainBean;
import model.MainDAO;

/**
 * Servlet implementation class DeleteFavoriteServlet
 */

public class DeleteFavoriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteFavoriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ISO3166 = request.getParameter("selectCountry");
		String user = request.getParameter("user");
		FavoriteCountryBean favoriteCountryBean = new FavoriteCountryBean();
		favoriteCountryBean.setISO3166(ISO3166);
		favoriteCountryBean.setUser(user);
		FavoriteCountryDAO favoriteCountryDAO = new FavoriteCountryDAO();
		String message = favoriteCountryDAO.DeleteFavoriteCountry(favoriteCountryBean);
		
		
		if(message.equals("SUCCESS"))   //On success, you can display a message to user on Home page
		 {
			request.setAttribute("Message", "§R°£¦¨¥\");
			request.getRequestDispatcher("/Message.jsp").forward(request, response);
		 }
		 else   //On Failure, display a meaningful message to the User.
		 {
			request.setAttribute("Message", message);
			request.getRequestDispatcher("/Message.jsp").forward(request, response);
		 }
		
	}

}
