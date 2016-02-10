package ebrithilapi.areas.t411.model;

public class Auth {
	public int uid;
	public String token;
	
	public Auth(int uid, String token) {
		super();
		this.uid = uid;
		this.token = token;
	}
	
	public Auth(){}
	
	public int getUid() {
		return uid;
	}
	
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}	
	
}
