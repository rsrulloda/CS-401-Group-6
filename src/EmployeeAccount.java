public class EmployeeAccount {
	private String username;
	private String password;
	private boolean inUse;
	
	public EmployeeAccount(String username, String password) {
		this.username = username;
		this.password = password;
		this.inUse = false;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public boolean getState() {
		return inUse;
	}
	
}
