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
import model.RegionInformationBean;
import model.RegionInformationDAO;

/**
 * Servlet implementation class RegionInformationServlet
 */
public class RegionInformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegionInformationServlet() {
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
		String user=request.getParameter("user");
		
		if(!user.equals("null")) {
			request.setAttribute("user",user);
			System.out.println("REGSERVlogin");
		}
		else{
				System.out.println("REGSERVnonlogin");
				request.setAttribute("user", null);
		}
		
		//request.setAttribute("user", user);
		ArrayList<RegionInformationBean> regionInformation = new ArrayList<RegionInformationBean>();
		
		RegionInformationDAO regionInformationDAO = new RegionInformationDAO();
		regionInformation = regionInformationDAO.getRegionInformation(ISO3166);

		
		if(regionInformation!=null) {
			request.setAttribute("ISO3166", ISO3166);
			request.setAttribute("countryData", regionInformation);
			request.getRequestDispatcher("/RegionInformation.jsp").forward(request, response);
		}
		else {	
			request.setAttribute("ISO3166", ISO3166);
			request.setAttribute("countryData", null);
			request.getRequestDispatcher("/RegionInformation.jsp").forward(request, response);
		}
			
	}

}
