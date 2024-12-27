package com.ecommerce.dto;

import java.util.Objects;

public class UserRegistrationDTO {
	
	private String userName;
	private String password;
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
	
	public UserRegistrationDTO(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	public UserRegistrationDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "UserRegistrationDTO [userName=" + userName + ", password=" + password +"]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(password, userName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRegistrationDTO other = (UserRegistrationDTO) obj;
		return Objects.equals(password, other.password) && Objects.equals(userName, other.userName);
	}
	
	
}
