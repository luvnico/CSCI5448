package beans;

public class User {
	private String email = "";
	private String password = "";
	private String message = "";
	
	public String getMessage() {
		return message;
	}

	public User() {
		
	}
	
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean validate() {
		// email & password are guaranteed not to be empty via form validation
		
		if(!email.matches("\\w+@\\w+\\.\\w+")) {
			message = "Invalid email address!";
			return false;
		}
		
		if(password.length() < 8) {
			message = "Password must have at least 8 characters.";
			return false;
		}
		else if(password.matches("\\w*\\s+\\w*")) {
			message = "Password cannot contain space.";
			return false;
		}
		
		return true;
	}

}