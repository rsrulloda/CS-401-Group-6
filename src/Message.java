import java.io.Serializable;

public class Message implements Serializable {
	
	public enum MessageType{
		CustomerLogin,
		EmployeeLogin,
		AccountInfo,
		LoginStatus,
		LogoutCustomer,
		LogoutEmployee
	}
	
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
	protected MessageType messageType;
	
	public Message(String messageType, String username, String password, String accountType, String accountNumber, float balance, String nickname, Boolean login) {
		if (messageType.equals("CustomerLogin")) {
            this.messageType = MessageType.CustomerLogin;
        } else if (messageType.equals("EmployeeLogin")) {
            this.messageType = MessageType.EmployeeLogin;
        } else if (messageType.equals("AccountInfo")) {
            this.messageType = MessageType.AccountInfo;
        } else if (messageType.equals("LoginStatus")) {
            this.messageType = MessageType.LoginStatus;
        } else if (messageType.equals("LogoutCustomer")) {
            this.messageType = MessageType.LogoutCustomer;
        } else if (messageType.equals("LogoutEmployee")) {
            this.messageType = MessageType.LogoutEmployee;
        }
		
		this.username = username;
		this.password = password;
		this.accountType = accountType;
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.nickname = nickname;
		this.login = login;
		
	}
	
	public MessageType getMessageType() {
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
	
	public String toString() {
		return username + "," + password + "," + accountType + "," + accountNumber + "," + balance + "," + nickname + "," + login;
	}
}