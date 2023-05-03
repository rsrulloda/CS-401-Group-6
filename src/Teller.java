public class Teller {


    public void loginEmployee(String username, String password) {

    }

    public void loginCustomer(String username, String password) {
        CustomerAccount currentCustomer = new CustomerAccount(username, password);
    }

    public void logoutEmployee() {
    }

    public void logoutCustomer() {
    }

    public void withdrawRequest(String accountNumber, double amount) {

    }

    public void depositRequest(String accountNumber, double amount) {
    }

    public void transfer(BankAccount account1, BankAccount account2, double amount) {
    }


    public void closeAcc(String accountNumber) {
    }

    public void openAcc() {
    }

    public void editAcc(String accountNumber, String nickname) {
    }
}
