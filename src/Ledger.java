import java.util.ArrayList;
import java.util.Date;

public class Ledger {
    
    private final ArrayList<LedgerEntry> ledgerEntries;
    
    public Ledger() {
        ledgerEntries = new ArrayList<>();
    }
    
    public void addEntry(float amountChanged, String customerAccount, String accountNumber, String location, Date date) {
        LedgerEntry entry = new LedgerEntry(amountChanged, customerAccount, accountNumber, location, date);
        ledgerEntries.add(entry);
    }
    
    public void addEntry(float amountChanged, String customerAccount, String accountNumber, String location, Date date, String bankEmployee) {
        LedgerEntry entry = new LedgerEntry(amountChanged, customerAccount, accountNumber, location, date, bankEmployee);
        ledgerEntries.add(entry);
    }
    
    public void loadEntries(String filename) {
    	
    }
    
    public ArrayList<LedgerEntry> getLedgerEntries() {
        return ledgerEntries;
    }
}
