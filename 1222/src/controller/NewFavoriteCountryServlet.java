package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.FavoriteCountryDAO;
import model.NewFavoriteCountryBean;

/**
 * Servlet implementation class NewFavoriteCountry
 */

public class NewFavoriteCountryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewFavoriteCountryServlet() {
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
		String user = request.getSession().getAttribute("user").toString();
		String ISO3166 = request.getParameter("ISO3166");
		
		NewFavoriteCountryBean newFavoriteCountryBean = new NewFavoriteCountryBean();
		newFavoriteCountryBean.setISO3166(ISO3166);
		newFavoriteCountryBean.setUser(user);
		
		FavoriteCountryDAO FavoriteCountryDAO = new FavoriteCountryDAO();
		String createFavoriteCountry = FavoriteCountryDAO.createFavoriteCountry(newFavoriteCountryBean);
		
		if(createFavoriteCountry.equals("SUCCESS"))   //On success, you can display a message to user on Home page
		 {
			request.setAttribute("Message", "我的最愛新增成功");
			request.getRequestDispatcher("/Message.jsp").forward(request, response);
		 }
		 else   //On Failure, display a meaningful message to the User.
		 {
			request.setAttribute("Message", createFavoriteCountry);
			request.getRequestDispatcher("/Message.jsp").forward(request, response);
		 }
	}

}
