package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CommentBean;
import model.CommentDAO;

/**
 * Servlet implementation class Comment
 */
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentServlet() {
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
		String ISO3166 = request.getParameter("ISO3166");
		CommentDAO commentDAO = new CommentDAO();
		ArrayList<CommentBean> comment = commentDAO.getComment(ISO3166);
		System.out.println(comment.size());
		if(comment.size()!=0) {
			request.setAttribute("ISO3166", ISO3166);
			request.setAttribute("comment", comment);
			request.getRequestDispatcher("/Comment.jsp").forward(request, response);
		}
		else {
			request.setAttribute("ISO3166", ISO3166);
			request.setAttribute("comment", null);
			request.getRequestDispatcher("/Comment.jsp").forward(request, response);
		}
	}

}
