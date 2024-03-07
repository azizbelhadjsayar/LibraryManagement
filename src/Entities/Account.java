package Entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Account {
	private int id;
	private String username;
	private String password;
	private String email;
	private String role;
	private String dateOfCreation;
	

	public Account(String username, String password, String email, String role) {

		this.id=0;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		
	    LocalDateTime today = LocalDateTime.now();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedDate = today.format(formatter);
	    
		this.dateOfCreation = formattedDate;
	}
	
	public Account(int id, String username, String password, String email, String role, String dateOfCreation) {
		this.id=id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.dateOfCreation = dateOfCreation;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getDateOfCreation() {
		return dateOfCreation;
	}
	public void setDateOfCreation(String dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", role=" + role + ", dateOfCreation=" + dateOfCreation + "]";
	}

	
}
