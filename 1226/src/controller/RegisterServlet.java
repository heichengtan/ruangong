package controller;



import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.RegisterBean;
import model.RegisterDAO;

/**
 * Servlet implementation class RegisterServlet
 */

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 //Copying all the input parameters in to local variables
		 String nickname = request.getParameter("nickname");
		 String userName = request.getParameter("account");
		 String password = request.getParameter("password");
		 
		 RegisterBean registerBean = new RegisterBean();
		 //Using Java Beans - An easiest way to play with group of related data
		 registerBean.setNickname(nickname);
		 registerBean.setUserAccount(userName);
		 registerBean.setPassword(password); 
		 
		 RegisterDAO registerDao = new RegisterDAO();
		 
		 //The core Logic of the Registration application is present here. We are going to insert user data in to the database.
		 String userRegistered = registerDao.registerUser(registerBean);
		 
		 if(userRegistered.equals("SUCCESS"))   //On success, you can display a message to user on Home page
		 {
			 request.getRequestDispatcher("/RegisterSuccessful.jsp").forward(request, response);
		 }
		 else   //On Failure, display a meaningful message to the User.
		 {
			 request.setAttribute("errMessage", "Oops!這個Account或fullname太熱門了，已被註冊！");
			 request.getRequestDispatcher("/Register.jsp").forward(request, response);
		 }
	}

}
