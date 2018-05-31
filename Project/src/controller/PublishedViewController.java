package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

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
import javax.sql.DataSource;

import beans.Post;
import database.Posting;

/**
 * Servlet implementation class PublishedViewController
 */
@WebServlet("/publishedView")
public class PublishedViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource ds;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublishedViewController() {
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		PrintWriter out = response.getWriter();
		Connection conn = null;
		int pid = Integer.parseInt(request.getParameter("pid"));

		try {
			conn = ds.getConnection();
			out.println("Connected to database.");
		} catch (SQLException e) {
			out.println("Unable to connect to database.");
			return;
		}
		
		try {
			Posting p_conn = new Posting(conn);
			//retrieve posting info from account
			Post post = p_conn.getPosting(pid);
			//if the returned PID is -1, then the requested PID is not valid (not published/doesn't exist)
			if(post.getPid() == -1)
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			//else, send to publishedView.jsp
			request.setAttribute("post", post);
			request.getRequestDispatcher("/publishedView.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println("ERROR from PublishedViewController, doGet(): "+e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
