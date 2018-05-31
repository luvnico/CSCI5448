package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import beans.Post;
import database.Posting;

/**
 * Servlet implementation class dashboardController
 */
@WebServlet("/DashboardController")
public class DashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DataSource ds;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
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
     * retrieve posting info from database
     */
	protected void retrieveData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		//get email from session
		String email = session.getAttribute("loginEmail") == null ? 
				(String)session.getAttribute("signupEmail") : (String)session.getAttribute("loginEmail");
				
		PrintWriter out = response.getWriter();
		Connection conn = null;

		try {
			conn = ds.getConnection();
			out.println("Connected to database.");
		} catch (SQLException e) {
			out.println("Unable to connect to database.");
			return;
		}
		
		//if the user has not logged in yet
		if(email == null){
			//redirect to sign-up page
			response.sendRedirect("signup.jsp");
		}else{
			//if the user has successfully logged in
			try {
				Posting p_conn = new Posting(conn);
				//retrieve posting info from account
				ArrayList<Post> result = p_conn.selectPosting(email);
				request.setAttribute("postings", result);
				int count = p_conn.countPosting(email);
				request.setAttribute("count", count);
				//forward posting info to dashboard.jsp
				request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("get is called!");
		retrieveData(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("post is called!");
		retrieveData(request, response);
	}

}