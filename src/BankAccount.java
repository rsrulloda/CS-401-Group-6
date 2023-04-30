public class BankAccount {

    public enum AccountType {
        Checking,
        Savings
    }

    private AccountType accountType;
    private String accountNumber;
    private float balance;
    private String nickname;

    public BankAccount(String accountType, String accountNumber) {
        if (accountType.equals("Checking")) {
            this.accountType = AccountType.Checking;
        } else if (accountType.equals("Savings")) {
            this.accountType = AccountType.Savings;
        }
        
        this.accountNumber = accountNumber;
        this.balance = 0;
    }
    
    public BankAccount(String accountType, String accountNumber, String nickname) {
        if (accountType.equals("Checking")) {
            this.accountType = AccountType.Checking;
        } else if (accountType.equals("Savings")) {
            this.accountType = AccountType.Savings;
        }
            
        this.accountNumber = accountNumber;
        this.balance = 0;
        this.nickname = nickname;
    }

    public AccountType getAccountType() {
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

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String toString() {
        return accountType + "," + accountNumber + "," + balance + "," + nickname;
    }

	public void setNickname(String newNickname) {
		nickname = newNickname;	
	}
}