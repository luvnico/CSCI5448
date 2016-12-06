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
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import beans.Posting;
import beans.User;
import database.Account;


/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DataSource ds;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");

		if (action == null) {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		else if(action.equals("login2")) {
			session.setAttribute("loginEmail", "");
			request.setAttribute("loginPassword", "");
			session.setAttribute("message", "");
			request.getRequestDispatcher("/signup.jsp").forward(request, response);
		}
		else if(action.equals("signup")) {
			session.setAttribute("email", "");
			session.setAttribute("password", "");
			session.setAttribute("repeatpassword", "");
			session.setAttribute("message", "");
			request.getRequestDispatcher("/signup.jsp").forward(request, response);
		}
		
		else if(action.equals("createposting")) {
			session.setAttribute("email", "");
			session.setAttribute("title", "");
			session.setAttribute("type", "");
			session.setAttribute("description", "");
			request.getRequestDispatcher("/createposting.jsp").forward(request, response);
		}
		
		
		else {
			out.println("unrecognised action");
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// use connection
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(true);

		String action = request.getParameter("action");

		if (action == null) {
			out.println("unrecognised action");
			return;
		}

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

		Account account = new Account(conn);
		
		if(action.equals("login2")) {
			String email = request.getParameter("loginEmail");
			String password = request.getParameter("loginPassword");
			
			session.setAttribute("loginEmail", email);
			//session.setAttribute("loginPassword", password);
			
			request.setAttribute("loginEmail", email);
			request.setAttribute("loginPassword", password);
			
			User user = new User(email, password);
			
			try {
				if(account.login(email, password)) {
					request.getRequestDispatcher("/loginsuccess.jsp").forward(request, response);
				}
				else {
					request.setAttribute("message", "email address or password not recognised");
					request.getRequestDispatcher("/signup.jsp").forward(request, response);
				}
			} catch (SQLException e) {

				request.setAttribute("email", "Database error: please try again later.");
				request.getRequestDispatcher("/newaccount.jsp").forward(request, response);
				
			}
			
		}
		else if(action.equals("signup")) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String repeatPassword = request.getParameter("repeatPassword");
			
			//request.setAttribute("email", email);
			session.setAttribute("signupEmail", email);
			request.setAttribute("password", "");
			request.setAttribute("repeatPassword", "");
			//request.setAttribute("message", "");
			
			if(!password.equals(repeatPassword)) {
				// Passwords don't match.
				request.setAttribute("message", "Passwords do not match.");
				request.getRequestDispatcher("/createaccount.jsp").forward(request, response);
			}
			else {
				User user = new User(email, password);
				
				if(!user.validate()) {
					// Password or email address has wrong format.
					request.setAttribute("message", user.getMessage());
					request.getRequestDispatcher("/createaccount.jsp").forward(request, response);
				}
				else {
					try {
						if(account.exists(email)) {
							// This email address already exists in the user database.
							request.setAttribute("message", "An account with this email address already exists");
							request.getRequestDispatcher("/signup.jsp").forward(request, response);
						}
						else {
							// We create create the account.
							account.create(email, password);
							request.getRequestDispatcher("/home.jsp").forward(request, response);
						}
					} catch (SQLException e) {
						
						request.getRequestDispatcher("/error.jsp").forward(request, response);
					}
				}
				
			}
		}
		
		
		else if(action.equals("createposting")) {
			if((session.getAttribute("loginEmail") == null) && (session.getAttribute("createEmail") == null)){
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
				
			else{	

				String title = request.getParameter("title");
				String type = request.getParameter("type");
				String description = request.getParameter("description");
				
				//Posting posting = new Posting(email,title, type, description);
			
				
				try{
					String email = "";
					if( session.getAttribute("loginEmail") != null ){
						email = (String)session.getAttribute("loginEmail");
						
					}
					
					else if( session.getAttribute("createEmail") != null ){
						email = (String)session.getAttribute("createEmail");
						
					}
					//account.post(email, title, type, description);
					request.getRequestDispatcher("/home.jsp").forward(request, response);
				}catch (SQLException e) {
				
					request.getRequestDispatcher("/error.jsp").forward(request, response);
				}
			}
			
		}
		
		
		else {
			out.println("unrecognised action");
		}
		

		
		
		
		
		try {
			conn.close();
		} catch (SQLException e) {
			throw new ServletException();
		}
		//session.invalidate();
	}
	

	
	public void createAccount() {
		
	}

}
