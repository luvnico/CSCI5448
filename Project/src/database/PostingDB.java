package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostingDB {

	private Connection conn;

	public PostingDB(Connection conn) {
		this.conn = conn;
	}

	public void post(String email, String type, String title, String description) throws SQLException {

		String sql = "INSERT INTO Posting(User_Email, title, type, description) VALUES(?,?,?,?)";

		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setString(1, email);
		stmt.setString(2, title);
		stmt.setString(3, type);
		stmt.setString(4, description);
		

		stmt.executeUpdate();
		stmt.close();
	}
}
	
	