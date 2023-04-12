import java.util.ArrayList;

public class CustomerAccount {
    private String username;
    private String password;
    private boolean inUse;
    private ArrayList<BankAccount> accounts;

    public CustomerAccount(String username, String password) {
        this.username = username;
        this.password = password;
        this.inUse = false;
        this.accounts = new ArrayList<BankAccount>();
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

    public ArrayList<BankAccount> getAccounts() {
        return accounts;
    }

    public void setPassword(String newPassword) {
        password = newPassword;
    }

    public void addAccount(String type, double balance) {
        BankAccount newAccount = new BankAccount(type, balance);
        accounts.add(newAccount);
    }

    public void addAccount(String type, double balance, String nickname) {
        BankAccount newAccount = new BankAccount(type, balance, nickname);
        accounts.add(newAccount);
    }

    public void editAccount(int accountNumber, String newNickname) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                account.setNickname(newNickname);
                break;
            }
        }
    }

    public void removeAccount(int accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                accounts.remove(account);
                break;
            }
        }
    }

    public String toString() {
        
    }
}
