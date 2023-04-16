public class BankAccount {

    public enum AccountType {
        Checking,
        Savings
    }

    private AccountType accountType;
    private String accountNumber;
    private float balance;
    private String nickname;

    public BankAccount(String accountType, String accountNumber, float balance) {
        if (accountType.equals("Checking")) {
            this.accountType = AccountType.Checking;
        } else if (accountType.equals("Savings")) {
            this.accountType = AccountType.Savings;
        }
        
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
    
    public BankAccount(String accountType, String accountNumber, float balance, String nickname) {
        if (accountType.equals("Checking")) {
            this.accountType = AccountType.Checking;
        } else if (accountType.equals("Savings")) {
            this.accountType = AccountType.Savings;
        }
            
        this.accountNumber = accountNumber;
        this.balance = balance;
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
		// TODO Auto-generated method stub
		nickname = newNickname;
	
	}
}