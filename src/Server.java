import java.io.*;
import java.net.*;
import java.util.*;


public class Server{
	
	private ArrayList<ClientHandler> currentClients;
	private ArrayList<CustomerAccount> currentCustomers;
	private ArrayList<BankAccount> accounts;
	
	public Server(){
		this.currentClients = new ArrayList<ClientHandler>();
		this.currentCustomers = new ArrayList<CustomerAccount>();
		
	}
	
	public void verifyCustomerLogin(String Username, String Password){
		for (CustomerAccount account : currentCustomers) {
            if (account.getUsername().equals(Password)) {
            	System.out.println("Login Successful");
                break;
            }
        }
	}
	
	public void verifyEmployeeLogin(String Username, String Password){
		
	}
	
	public void logoutCustomer(){
		
	}
	
	public void logoutEmployee(){
		
	}
	
	public void withdraw(String accountNumber, float amount){
		
	}
	
	public void deposit(String accountNumber, float amount){
		
	}
	
	public void transfer(String account1, String account2, float amount){
		
	}
	
	public void returnBalances(){
		
	}
	
	public void addAccount(String customerAccount){
		CustomerAccount newAccount = new CustomerAccount(" "," ");
        currentCustomers.add(newAccount);
	}
	
	public void closeAccount(String accountNumber){
		for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                accounts.remove(account);
                break;
            }
        }
	}
	
	public void editAccount(String accountNumber, String nickname){
		for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                account.setNickname(nickname);
                break;
            }
        }
	}
	
	public void open(){
		
	}
	
	public void log(){
		
	}
	
	public static void main(String args[]) throws IOException, ClassNotFoundException{
		
		ServerSocket ss = new ServerSocket(7777);
		System.out.println("Server is running on port: " + 7777);
		Socket socket = ss.accept();
		
		System.out.println("ClientHandler connected");
		
		ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
		ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
		
		Message text = (Message)inputStream.readObject();
		
		if (text.getMessageType().equals("CustomerLogin")) {
			
			text.setStatus("success");
            outputStream.writeObject(text);
            
		}
		
		ss.close();
	}
}

