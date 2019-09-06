import java.util.ArrayList;

class UserInfo {
	private static ArrayList<User> users;
	
	public UserInfo() {
		users = new ArrayList<User>();
	}
	
	public static void addUser(User user) {
		users.add(user);
	}
	
	public static ArrayList<User> getUsers() {
		return users;
	}
}

class User {
	private final String id;
	private final String pwd;
	
	public User(String id, String pwd) {
		this.id = id;
		this.pwd = pwd;
	}
	
	public String getId() {
		return id;
	}
	
	public String getPwd() {
		return pwd;
	}
}
