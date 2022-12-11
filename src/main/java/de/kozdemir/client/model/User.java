package de.kozdemir.client.model;

public class User {
	private int id;
	private String userName;
	private String password;
	private String email;
	private int status;
	
	
	
	
	public User() {
		
	}
	
	public User(int id, String userName, String password, String email, int status) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.status = status;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=").append(id).append(", userName=").append(userName).append(", password=")
				.append(password).append(", email=").append(email).append(", status=").append(status).append("]");
		return builder.toString();
	}
	
	

}
