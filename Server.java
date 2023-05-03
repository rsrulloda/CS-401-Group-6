import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

//Server class
class Server {
	
	protected static ArrayList<ClientHandler> currentClients;
	protected ArrayList<EmployeeAccount> currentEmployees = new ArrayList<EmployeeAccount>();
	protected static ArrayList<CustomerAccount> currentCustomers = new ArrayList<CustomerAccount>();
	protected static ArrayList<BankAccount> accounts;

	
	public static boolean verifyCustomerLogin(String Username, String Password){
		for (CustomerAccount account : currentCustomers) {
            if (account.getUsername().equals(Username) && account.getPassword().equals(Password)) {
	            	return true;
            	}
		}
		return false;
    }
	
	public static boolean withdraw(String customerUsername, String accountNumber, float amount) {
		
		CustomerAccount currentCustomer = null;
		System.out.println("Number of current customers: " + currentCustomers.size());
		
		for(CustomerAccount account : currentCustomers) {
			if(account.getUsername().equals(customerUsername)) {
				System.out.println("Withdraw");
				currentCustomer = account;
			}
		}
		
		if (currentCustomer != null) {
			System.out.println(accountNumber);
		}
		
		for(BankAccount account :  currentCustomer.getAccounts()) {
			System.out.println(account.getAccountNumber());
			if(account.getAccountNumber().equals(accountNumber)) {
				account.withdraw(amount);
				System.out.println("Withdraw");
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean deposit(String customerUsername, String accountNumber, float amount) {
		CustomerAccount currentCustomer = null;
		System.out.println(currentCustomers.size());
		
		for(CustomerAccount account : currentCustomers) {
			if(account.getUsername().equals(customerUsername)) {
				System.out.println("Deposit");
				currentCustomer = account;
			}
		}
		
		for(BankAccount account :  currentCustomer.getAccounts()) {
			if(account.getAccountNumber().equals(accountNumber)) {
				account.deposit(amount);
				return true;
			}
		}
		
		return false;
	}
	
	public static float transfer(String account1, String account2, float amount) {

		for(BankAccount account : accounts) {
			if(account.getAccountNumber().equals(account1)) {
				amount = account.getBalance() - amount;
				account.getAccountNumber().equals(account2);
				
				if(account.getAccountNumber().equals(account2)){
					amount = account.getBalance() + amount;
				}
			}
		}
		
		return amount;
	}
	
    public void addAccount(String customerAccount) {

    	for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(customerAccount)) {
                accounts.add(account);
                break;
            }
        }
    }
    
    public void closeAccount(String accountNumber) {

    	for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                accounts.remove(account);
                break;
            }
        }
    }
    
    public void editAccount(String accountNumber, String nickname) {

    	for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                account.setNickname(nickname);
                break;
            }
        }
    }

	public static boolean logoutCustomer(){
		for (CustomerAccount account : currentCustomers) {
            if (account.getState()) {
	            	return true;
            	}
		}
		return false;
	}
	
	public static void main(String[] args)
	{
		CustomerAccount c1 = new CustomerAccount("Customer1", "123");
		currentCustomers.add(c1);
		c1.addAccount("Checking", 500);
		c1.addAccount("Savings", 1000);
		
		ServerSocket server = null;

		try {

			server = new ServerSocket(1234);
			server.setReuseAddress(true);

			while (true) {

				Socket client = server.accept();
				System.out.println("New client connected " + client.getInetAddress().getHostAddress());
				ClientHandler clientSock = new ClientHandler(client);
				new Thread(clientSock).start();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (server != null) {
				try {
					server.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// ClientHandler class
	private static class ClientHandler implements Runnable {
		private final Socket clientSocket;
		
		private String currentCustomer;
		private String currentEmployee;
		private boolean state;
		
		// Constructor
		public ClientHandler(Socket socket)
		{
			this.clientSocket = socket;
		}

		public void run()
		{   
	        // get the input and output stream from the connected socket
	        InputStream inputStream = null;
	        OutputStream outputStream = null;
	        
	        // create a ObjectInputStream and ObjectOutputStream so we can read and send data
	        ObjectInputStream in = null;
	        ObjectOutputStream out = null;

			try {
				// Initialize the streams
				inputStream = clientSocket.getInputStream();
				outputStream = clientSocket.getOutputStream();
				
				in = new ObjectInputStream(inputStream);
				out = new ObjectOutputStream(outputStream);
				
				Message message;
				
				while ( (message = (Message) in.readObject()) != null) {

					if (message.getMessageType().equals("CustomerLogin")) {
						boolean loginResult = verifyCustomerLogin(message.getUsername(), message.getPassword());
						
						if(loginResult) {
							currentCustomer = message.getUsername();
							out.writeObject(new Message("CustomerLogin"));
							System.out.println("CustomerLogin");
						}
					}
					
					else if (message.getMessageType().equals("Withdraw")) {
						boolean loginResult = withdraw(currentCustomer, message.getAccountNumber(), message.getAmount());
						if(loginResult) {
							out.writeObject(new Message("Withdraw"));
							System.out.println("Withdraw");
						}
					}
					
					else if (message.getMessageType().equals("Deposit")) {
						boolean loginResult = withdraw(currentCustomer, message.getAccountNumber(), message.getAmount());
						if(loginResult) {
							out.writeObject(new Message("Deposit"));
							System.out.println("Deposit");
						}
					}
					
					else if (message.getMessageType().equals("CustomerLogout")) {
						boolean loginResult = logoutCustomer();
						if(loginResult) {
							out.writeObject(new Message("CustomerLogout"));
							System.out.println("CustomerLogout");
						}
					}
				}
			}
			catch (EOFException e) {
				try {
					clientSocket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			} 
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			finally {
				try {
					if (outputStream != null || out != null) {
						outputStream.close();
					}
					if (inputStream != null || in != null) {
						inputStream.close();
						clientSocket.close();
					}
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
