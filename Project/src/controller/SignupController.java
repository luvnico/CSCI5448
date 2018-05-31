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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import beans.User;
import database.Account;

/**
 * Servlet implementation class LoginController
 */
//@WebServlet("/LoginController")
public class SignupController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DataSource ds;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignupController() {
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
		//HttpSession session = request.getSession(true);
		//PrintWriter out = response.getWriter();
		request.getRequestDispatcher("/signup.jsp").forward(request, response);
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

		Account account = new Account(conn);
		
		String email = request.getParameter("signupEmail");
		String password = request.getParameter("password");
		String repeatPassword = request.getParameter("repeatPassword");
		
		request.setAttribute("password", "");
		request.setAttribute("repeatPassword", "");
		
		if(!password.equals(repeatPassword)) {
			// Passwords don't match.
			request.setAttribute("signupMessage", "Passwords do not match.");
			request.getRequestDispatcher("/signup.jsp").forward(request, response);
		}
		else {
			User user = new User(email, password);
			
			if(!user.validate()) {
				// Password or email address has wrong format.
				// get message from the User bean
				request.setAttribute("signupMessage", user.getMessage());
				request.getRequestDispatcher("/signup.jsp").forward(request, response);
			}
			else {
				try {
					if(account.exists(email)) {
						// This email address already exists in the user database.
						request.setAttribute("signupMessage", "An account with this email address already exists!");
						request.getRequestDispatcher("/signup.jsp").forward(request, response);
					}
					else {
						// We create create the account.
						System.out.println("signup SUCCESS");
						account.create(email, password);
						// and set up the session
						session.setAttribute("signupEmail", email);
						request.getRequestDispatcher("/Dashboard").forward(request, response);
					}
				} catch (SQLException e) {
					request.getRequestDispatcher("/error.jsp").forward(request, response);
				}
			}
			
		}
	
	}
}