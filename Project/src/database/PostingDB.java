package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import beans.Post;

public class Posting {

	private Connection conn;
	
	public Posting(Connection conn) {
		this.conn = conn;
	}
	/*
	 * Translate a Date object to human readable String
	 * in format as: Apr 12th, 2018
	 * */
	public String getReadable(Date date){
		SimpleDateFormat df_out = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
		String readable = df_out.format(date);
		return readable;
	}
	
	/*
	 * Create a posting
	 * 	if successfully created -> return the posting id
	 *  else (failed) -> return -1
	 * */
	public int newpost(String email, String title, String type, String description, String contactEmail, String contactName, String status) throws SQLException {

		//format and get current date time
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String now = formatter.format(date);
		
		String sql = "INSERT INTO Posting(User_Email, title, type, description,contactEmail, contactName, status, create_date, modified_date) VALUES(?,?,?,?,?,?,?,?,?)";
		//get returned generated key upon successful insertion
		PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		stmt.setString(1, email);
		stmt.setString(2, title);
		stmt.setString(3, type);
		stmt.setString(4, description);
		stmt.setString(5, contactEmail);
		stmt.setString(6, contactName);
		stmt.setString(7, status);
	    stmt.setString(8, now);
	    stmt.setString(9, now);
		
		stmt.executeUpdate();
	
		ResultSet rs = stmt.getGeneratedKeys();
		int pid = rs.next() ? rs.getInt(1) : -1;
		stmt.close();
		return pid;
	}
	
	/*
	 * Count # of postings posted by a user
	 * 	if no posting found, return 0
	 * 	else, return postings count
	 * */
	public int countPosting(String email) throws SQLException{
		PreparedStatement stmt = null;
		int count = 0;
		String sql = "SELECT COUNT(*) as count FROM Posting WHERE User_Email = ?";
		try{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt("count");
			}
		} catch (SQLException e) {
			System.out.println("SQL ERROR from Posting.java, countPosting(): "+e.getMessage());
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return count;
	}
	
	/*
	 * Retrieve postings posted by a specific user
	 * */
	public ArrayList<Post> selectPosting(String email) throws SQLException{
		
		PreparedStatement stmt = null;
		//store retrieved posting info into an arraylist of post records
		ArrayList<Post> res = new ArrayList<>();
		
		String sql = "SELECT P_id, status, title, description, type, create_date, modified_date FROM Posting WHERE User_Email = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {	
				int pid = rs.getInt("P_id");
				String status = rs.getString("status");
				String desc = rs.getString("description");
				String title = rs.getString("title");
				String type = rs.getString("type");
				String created = rs.getString("create_date");
				String modified = rs.getString("modified_date");

				Post p = new Post(pid, status, title, desc, type, created, modified);
				res.add(p);
			}

		} catch (SQLException e) {
			System.out.println("SQL ERROR from Posting.java, selectPosting(): "+e.getMessage());
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return res;
	}
	
	/*
	 * Publish a posting
	 * Auto-generate current date time and insert into database
	 * */
	public boolean publishPosting(int pid) throws SQLException {
		//format and get current date time
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String now = formatter.format(date);
		
		PreparedStatement stmt = null;
		String sql = "UPDATE Posting SET status = ?, modified_date = ? WHERE P_id = ?";
		try{
			stmt = conn.prepareStatement(sql);
	
			stmt.setString(1, "published");
			stmt.setString(2, now);
		    stmt.setInt(3, pid);
			
			stmt.executeUpdate();
		} catch(SQLException e) {
			System.out.println("SQL ERROR from Posting.java, publishPosting(): "+e.getMessage());
			return false;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return true;
	}
	
	/*
	 * Retrieve published postings under 'Items Wanted' category (order by modified date in descending order)
	 * Description for each Post object is truncated to the first sentence (just showing an abstract)
	 * */
	public ArrayList<Post> showPublished() throws SQLException{
		ArrayList<Post> res = new ArrayList<>();
		String sql = "SELECT P_id, status, title, description, type, create_date, modified_date FROM Posting WHERE type= ? AND status= ? ORDER BY ? DESC";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, "items wanted");
			stmt.setString(2, "published");
			stmt.setString(3, "modified_date");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {	
		
				int pid = rs.getInt("P_id");
				String status = rs.getString("status");
				String title = rs.getString("title");
				String desc = rs.getString("description");
				String type = rs.getString("type");
				String created = rs.getString("create_date");
				String modified = rs.getString("modified_date");
				//split the description paragraph with the first punctuation
				String abs = desc.split("[.!?~'\":]")[0];
				
				Post p = new Post(pid, status, title, abs, type, created, modified);
				
				//interpret date String as Date object
				DateFormat df_in = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
				try {
					Date c_date = df_in.parse(created);
					Date m_date = df_in.parse(modified);
					//convert Date object to human readable string & update the fields
					p.setM_date(getReadable(m_date));
					p.setC_date(getReadable(c_date));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				res.add(p);
			}

		} catch (SQLException e) {
			System.out.println("SQL ERROR from Posting.java, showPublished(): "+e.getMessage());
		} 
		return res;
	}
	
	/*
	 * Retrieve a published post by PID
	 * @param pid the posting ID to retrieve the specific posting
	 * The ID of returned posting would be -1 if given invalid PID (status != published/PID doesn't exist/etc..)
	 * */
	public Post getPosting(int pid) throws SQLException{
		Post res = new Post();
		String sql = "SELECT title, description, contactName, contactEmail, type, create_date, modified_date FROM Posting WHERE P_id= ? AND status = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, pid);
			stmt.setString(2, "published");
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {	
				String title = rs.getString("title");
				String desc = rs.getString("description");
				String type = rs.getString("type");
				String created = rs.getString("create_date");
				String modified = rs.getString("modified_date");
	
				res = new Post(pid, "published", title, desc, type, created, modified);
				res.setC_name(rs.getString("contactName"));
				res.setC_email(rs.getString("contactEmail"));
			}

		} catch (SQLException e) {
			System.out.println("SQL ERROR from Posting.java, getPosting(): "+e.getMessage());
		} 
		return res;
	}
}
	
	