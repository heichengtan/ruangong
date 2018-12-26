package model;

public class RegisterBean {
	 private String nickname;
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
	 public void setNickname(String nickname) {
	 this.nickname = nickname;
	 }
	 public String getNickname() {
	 return nickname;
	 }
	 
}
