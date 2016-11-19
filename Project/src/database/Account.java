package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.howtodoinjava.hashing.password.demo.bcrypt.BCrypt;

public class Account {

	private Connection conn;

	public Account(Connection conn) {
		this.conn = conn;
	}

	public boolean login(String email, String password) throws SQLException {

		String sql = "select password_hash from Authentication where emailAddress=?";

		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setString(1, email);

		ResultSet rs = stmt.executeQuery();
		
		String password_hash = "";
		boolean matched = false;
		while (rs.next()){
			password_hash = rs.getString("password_hash");
		}
		stmt.close();
		
		matched = BCrypt.checkpw(password, password_hash);
		return matched;
		
	
		/*
		int count = 0;

		if (rs.next()) {
			count = rs.getInt("count");
		}
		
		rs.close();

		if (count == 0) {
			return false;
		} else {
			return true;
		}*/
	}
	
	public void create(String email, String password) throws SQLException {
		
		String generatedSecuredPasswordHash = BCrypt.hashpw(password, BCrypt.gensalt(12));
         
		String sql1 = "insert into User (emailAddress) values (?)";
		PreparedStatement stmt1 = conn.prepareStatement(sql1);
		stmt1.setString(1, email);
		stmt1.executeUpdate();
		stmt1.close();
		
		
		String sql2 = "insert into Authentication (emailAddress,password_hash) values (?,?)";
		PreparedStatement stmt2 = conn.prepareStatement(sql2);
		stmt2.setString(1, email);
		stmt2.setString(2, generatedSecuredPasswordHash);
		stmt2.executeUpdate();
		stmt2.close();
		
	}
	
	public boolean exists(String email) throws SQLException {

		String sql = "select count(*) as count from User where emailAddress=?";

		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setString(1, email);

		ResultSet rs = stmt.executeQuery();

		int count = 0;

		if (rs.next()) {
			count = rs.getInt("count");
		}
		
		rs.close();

		if (count == 0) {
			return false;
		} else {
			return true;
		}
	}
}
