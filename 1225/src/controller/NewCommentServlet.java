package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CommentDAO;

/**
 * Servlet implementation class NewCommentServlet
 */
public class NewCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewCommentServlet() {
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
		String user = request.getParameter("user");
		String content = request.getParameter("NewContent");
		
		CommentDAO commentDAO = new CommentDAO();
		String rs = commentDAO.insertComment(user,ISO3166,content);
		
		if(rs.equals("SUCCESS")) {
			request.setAttribute("Message", "新增成功");
			request.getRequestDispatcher("/Message.jsp").forward(request, response);;
		}
		else {
			request.setAttribute("Message", "新增失敗");
			request.getRequestDispatcher("/Message.jsp").forward(request, response);;
		}
	}

}
