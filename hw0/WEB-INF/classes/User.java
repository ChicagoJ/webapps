public class User {
	private String userName;
	private String password;
	private int level;
	
	public User(String uNa, String pwd, int lvl){
		setUserName(uNa);
		setPassword(pwd);
		setLevel(lvl);
	}
	public void setUserName(String uNa){
		this.userName = uNa;
	}
	public void setPassword(String pwd){
		this.password = pwd;
	}
	public void setLevel(int lvl){
		this.level = lvl;
	}
	public String getUserName(){
		return(userName);
	}
	public String getPassword(){
		return(password);
	}
	public int getLevel(){
		return(level);
	}
}