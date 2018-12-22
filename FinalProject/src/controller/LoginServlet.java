package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.FavoriteCountryBean;
import model.FavoriteCountryDAO;
import model.LoginBean;
import model.LoginDAO;
import model.MainBean;
import model.MainDAO;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName = request.getParameter("username");
		String password = request.getParameter("password");

		LoginBean loginBean = new LoginBean();

		loginBean.setUserName(userName);

		loginBean.setPassword(password);

		LoginDAO loginDAO = new LoginDAO();

		MainDAO mainDAO = new MainDAO();
		ArrayList<MainBean> countryData = mainDAO.getCountryData();
		request.setAttribute("countryData", countryData);
		String userValidate = loginDAO.authenticateUser(loginBean);
		if (userValidate != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", userValidate);
			response.sendRedirect("Index.jsp");
		} else {
			request.setAttribute("errMessage", "Invalid user credentials");

			request.getRequestDispatcher("/Login.jsp").forward(request, response);
		}
	}

}
