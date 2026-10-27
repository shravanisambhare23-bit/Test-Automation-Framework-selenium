package com.ui.pojo;

public class User {
private String emailAddress;
private String password;

public User(String emailAddress, String password) {
	super(); // generated this parameterised constructor to pass data from CSV file
	this.emailAddress = emailAddress;
	this.password = password;
}
public String getEmailAddress() {
	return emailAddress;
}
public void setEmailAddress(String emailAdress) {
	this.emailAddress = emailAddress;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
@Override
public String toString() {
	return "User [emailAddress=" + emailAddress + ", password=" + password + "]";
}

}
