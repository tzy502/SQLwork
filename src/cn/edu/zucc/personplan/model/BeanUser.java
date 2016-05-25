package cn.edu.zucc.personplan.model;

public class BeanUser {
	public static BeanUser currentLoginUser=null;
	private String UserId;
	private String Password;
	private String UserName;
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	
}
