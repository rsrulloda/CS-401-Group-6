import java.util.Date;

public class LedgerEntry {
    
    private final float amountChanged;
    private final String customerAccount;
    private final String accountNumber;
    private final String location;
    private final Date date;
    private final String details;
    private final String bankEmployee;
    
    public LedgerEntry(float amountChanged, String customerAccount, String accountNumber, String location, Date date) {
        this.amountChanged = amountChanged;
        this.customerAccount = customerAccount;
        this.accountNumber = accountNumber;
        this.location = location;
        this.date = date;
		this.details = "";
		this.bankEmployee = "";
    }
    
    public LedgerEntry(float amountChanged, String customerAccount, String accountNumber, String location, Date date, String bankEmployee) {
        this.amountChanged = amountChanged;
        this.customerAccount = customerAccount;
        this.accountNumber = accountNumber;
        this.location = location;
        this.date = date;
		this.details = "";
        this.bankEmployee = bankEmployee;
    }
    
    public float getAmountChanged() {
        return amountChanged;
    }
    
    public String getCustomerAccount() {
        return customerAccount;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public String getLocation() {
        return location;
    }
    
    public Date getDate() {
        return date;
    }
    
    public String getDetails() {
        return details;
    }
    
    public String getBankEmployee() {
        return bankEmployee;
    }
}
