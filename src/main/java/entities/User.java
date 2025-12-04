package entities;

import java.sql.Date;

public class User {
	long userId;
	String firstName;
	String lastName;
	Date dob;
	String email;
	String password;
	String phone;
	int regAmount;
	String role;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
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
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getRegAmount() {
		return regAmount;
	}
	public void setRegAmount(int regAmount) {
		this.regAmount = regAmount;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
//	public User() {
//		super();
//		this.userId = 0;
//		this.firstName = "firstName";
//		this.lastName = "lastName";
//		this.dob = new Date(0);
//		this.email = "email";
//		this.password = "password";
//		this.phone = "phone";
//		this.regAmount = 0;
//		this.role = "role";
//	}
	public User(long userId, String firstName, String lastName, Date dob, String email, String password, String phone,
			int regAmount, String role) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.regAmount = regAmount;
		this.role = role;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob
				+ ", email=" + email + ", password=" + password + ", phone=" + phone + ", regAmount=" + regAmount
				+ ", role=" + role + "]";
	}
	
}
