package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import database.Account;

/**
 * Servlet implementation class itemsWantedController
 */
@WebServlet("/itemsWantedController")
public class itemsWantedController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DataSource ds;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public itemsWantedController() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init(ServletConfig config) throws ServletException {
		try {
			InitialContext initContext = new InitialContext();

			Context env = (Context) initContext.lookup("java:comp/env");

			ds = (DataSource) env.lookup("jdbc/CraigsList");

		} catch (NamingException e) {
			throw new ServletException();
		}
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("/itemWanted.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		PrintWriter out = response.getWriter();
		
		Connection conn = null;

		try {
			conn = ds.getConnection();
			out.println("Connected to database.");
		} catch (SQLException e) {
			out.println("Unable to connect to database.");
			System.out.println("Unable to connect to database.");
			return;
			//throw new ServletException();
		}
		
		
		/*if((session.getAttribute("loginEmail") == null) && (session.getAttribute("createEmail") == null)){
			request.getRequestDispatcher("/signup.jsp").forward(request, response);
		}*/
			
		//else{	

			Account account = new Account(conn);
			String contactEmail = request.getParameter("contactEmail");
			String contactName = request.getParameter("contactName");
			String title = request.getParameter("title");
			String type = "itemWanted";
			String description = request.getParameter("description");
			String status = "draft";
			
			request.setAttribute("title",title);
			request.setAttribute("description",description);
			
			
			
			
			//Posting posting = new Posting(email,title, type, description);
		
			
			try{
				String email = "";
				if( session.getAttribute("loginEmail") != null ){
					email = (String)session.getAttribute("loginEmail");
					
				}
				
				else if( session.getAttribute("signupEmail") != null ){
					email = (String)session.getAttribute("signupEmail");
					
				}
				account.post(email, title, type, description,contactEmail, contactName, status);
				request.getRequestDispatcher("/postpreview.jsp").forward(request, response);
			}catch (SQLException e) {
			
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
		//}
		
	
	
	}

}
