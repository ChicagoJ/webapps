public class User {
	private String userId;
	private String password;
	private int level;
	
	public User(String uId, String pwd, int lvl){
		setUserId(uId);
		setPassword(pwd);
		setLevel(lvl);
	}
	public void setUserId(String uId){
		this.userId = uId;
	}
	public void setPassword(String pwd){
		this.password = pwd;
	}
	public void setLevel(int lvl){
		this.level = lvl;
	}
	public String getUserId(){
		return(userId);
	}
	public String getPassword(){
		return(password);
	}
	public int getLevel(){
		return(level);
	}

	// public static void main(String[] args) {
	// 	System.out.println("Hello");
	// }
}