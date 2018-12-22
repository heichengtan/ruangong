package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CommentDAO;

/**
 * Servlet implementation class UpdateCommentServlet
 */
public class UpdateCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCommentServlet() {
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
		String commentNum=request.getParameter("comment");
		String content = request.getParameter("UpdateContent");
		
		CommentDAO commentDAO = new CommentDAO();
		String rs = commentDAO.updateComment(commentNum,content);
		if(rs.equals("SUCCESS")) {
			request.setAttribute("Message", "�ק令�\");
			request.getRequestDispatcher("/Message.jsp").forward(request, response);;
		}
		else {
			request.setAttribute("Message", "�ק異��");
			request.getRequestDispatcher("/Message.jsp").forward(request, response);;
		}
	}

}
