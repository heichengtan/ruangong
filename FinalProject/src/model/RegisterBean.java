package model;

public class RegisterBean {
	 private String fullName;
	 private String Account;
	 private String userName;
	 private String password;
	 
	 public String getUserAccount() {
	 return Account;
	 }
	 public void setUserAccount(String userName) {
	 this.Account = userName;
	 }
	 public String getPassword() {
	 return password;
	 }
	 public void setPassword(String password) {
	 this.password = password;
	 }
	 public void setFullName(String fullName) {
	 this.fullName = fullName;
	 }
	 public String getFullName() {
	 return fullName;
	 }
	 
}
