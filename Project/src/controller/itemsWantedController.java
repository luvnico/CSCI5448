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

import database.Posting;

/**
 * Servlet implementation class itemsWantedController
 */
@WebServlet("/itemsWanted")
public class ItemsWantedController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource ds;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemsWantedController() {
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
		System.out.println("get pid:"+request.getParameter("pid"));
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
		}
		String action = request.getParameter("action");
		/*if(action.equals("create")){
			//you need to add hidden input in the form @postpreview.jsp first
		} else if(action.equals("edit")){
			
		} else{
			System.out.println("Unrecognised action");
		}*/
		Posting p_conn = new Posting(conn);
		String contactEmail = request.getParameter("contactEmail");
		String contactName = request.getParameter("contactName");
		String title = request.getParameter("title");
		String type = "items wanted";
		String description = request.getParameter("description");
		String status = "draft";
		
		request.setAttribute("title",title);
		request.setAttribute("description",description);
		request.setAttribute("status", status);

		try{
			String email = "";
			if( session.getAttribute("loginEmail") != null ){
				email = (String)session.getAttribute("loginEmail");
			}
			else if( session.getAttribute("signupEmail") != null ){
				email = (String)session.getAttribute("signupEmail");
			}
			int pid = p_conn.newpost(email, title, type, description,contactEmail, contactName, status);
			// get pid of the new posting created & forward to preview
			request.setAttribute("pid", pid);
			request.getRequestDispatcher("/postpreview.jsp").forward(request, response);
		}catch (SQLException e) {
			System.out.println("ERROR from itemsWantedController: "+e.getMessage());
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}

	}

}