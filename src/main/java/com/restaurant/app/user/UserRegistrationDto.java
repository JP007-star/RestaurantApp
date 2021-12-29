package com.restaurant.app.user;

public class UserRegistrationDto {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Long phoneNo;
	private boolean status;


	public UserRegistrationDto(){
		
	}

	public UserRegistrationDto(String id,String firstName, String lastName, String email, String password, String phoneNo, boolean status) {
		this.id = Long.valueOf(id);
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phoneNo = Long.valueOf(phoneNo);
		this.status = status;
	}

	public Long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(Long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
}