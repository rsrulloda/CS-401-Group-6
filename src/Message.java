import java.io.Serializable;

public class Message implements Serializable {
	
	//For CustomerLogin and EmployeeLogin
	protected String username;
	protected String password;
	
	//For AccountInfo
	protected String accountType;
	protected String accountNumber;
	protected float balance;
	protected String nickname;
	
	//For LoginStatus
	protected Boolean login;
	protected String messageType;
	
	public Message(String messageType, String username, String password, String accountType, String accountNumber, float balance, String nickname, Boolean login) {
		
		this.messageType = messageType;
		this.username = username;
		this.password = password;
		this.accountType = accountType;
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.nickname = nickname;
		this.login = login;
		
	}
	
	public Message(String messageType) {
		this.messageType = messageType;
		
	}
		
	public String getMessageType() {
		return messageType;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getAccountType() {
		return accountType;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public float getBalance() {
		return balance;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public Boolean getLogin() {
		return login;
	}
}