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
 * Servlet implementation class PreviewController
 */
@WebServlet("/previewController")
public class PreviewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource ds;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PreviewController() {
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
		// TODO Auto-generated method stub
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
			return;
		}

		Posting p_conn = new Posting(conn);
		String action = request.getParameter("action");
		if(action.equals("edit")){
			request.getRequestDispatcher("/postpreview.jsp").forward(request, response);
		}
		if(action.equals("publish")){
			int pid = Integer.parseInt(request.getParameter("pid"));
			try {
				p_conn.publishPosting(pid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("ERROR from PreviewController, doPost(): "+e.getMessage());
			}
			request.getRequestDispatcher("/Dashboard").forward(request, response);
		}
		else {
			System.out.println("WARN From PreviewController, doPost(): Unrecognised action");
			return;
		}
	}

}
