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
    
    // For withdraw/deposit
    protected float amount;

    // For transfer
    protected BankAccount account1;
    protected BankAccount account2;
    
    
    // Withdraw/Deposit Message
    public Message(String messageType, String accountNumber, float amount) {
        this.messageType = messageType;
        this.amount = amount;
        this.accountNumber = accountNumber;
    }
    
    // GetAccountInfo Message
    public Message(String messageType, String accountNumber) {
    	this.messageType = messageType;
    	this.accountNumber = accountNumber;
    }
    
    public Message(String messageType, String accountType, String accountNumber, float balance, String nickname) {
    	this.messageType = messageType;
    	this.accountType = accountType;
    	this.accountNumber = accountNumber;
    	this.balance = balance;
    	this.nickname = nickname;
    }
    
    // EmployeeLogin/CustomerLogin/EditAccountNickname Messages
    public Message(String messageType, String input1, String input2) {
    	this.messageType = messageType;
    	
    	if (messageType.equals("EmployeeLogin") || messageType.equals("CustomerLogin")) {
            this.username = input1;
            this.password = input2;
    	} else if (messageType.equals("EditAccountNickname")) {
    		this.accountNumber = input1;
    		this.nickname = input2;
    	}
        
    }

    public Message(String messageType, BankAccount account1, BankAccount account2, float amount) {
        this.messageType = messageType;

        if (messageType.equals("Transfer")) {
            this.account1 = account1;
            this.account2 = account2;
            this.amount = amount;
        }
    }
    
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
    
    // Success/Failure/GetAllAccountInfo Messages
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
    
    public float getAmount() {
        return amount;
    }
}