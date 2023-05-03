import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

//Server class
class Server {
	
	protected static ArrayList<ClientHandler> currentClients;
	protected static ArrayList<CustomerAccount> currentCustomers = new ArrayList<CustomerAccount>();
	protected static ArrayList<EmployeeAccount> currentEmployees = new ArrayList<EmployeeAccount>();
	protected static ArrayList<BankAccount> accounts;

	
	public static boolean verifyCustomerLogin(String Username, String Password){
		for (CustomerAccount account : currentCustomers) {
            if (account.getUsername().trim().equals(Username) && account.getPassword().trim().equals(Password)) {
	            	return true;
            	}
		}
		return false;
    }
	
	public static boolean verifyEmployeeLogin(String Username, String Password){
		
		System.out.println("Trying to authenticate user, password: " + Username + "," + Password);
		
		for (EmployeeAccount account : currentEmployees) {
			System.out.println("Employee has user: " + account.getUsername() + " and pass: " + account.getPassword());
			
            if (account.getUsername().equals(Username) && account.getPassword().equals(Password)) {
	            	return true;
        	}
		}
		return false;
    }
	
	public static boolean withdraw(String customerUsername,String accountNumber, float amount) {
		
		CustomerAccount currentCustomer = null;
		System.out.println(currentCustomers.size());
		
		for(CustomerAccount account : currentCustomers) {
			System.out.println(account.getUsername());
			System.out.println(customerUsername);
			if(account.getUsername().equals(customerUsername)) {
				System.out.println("Account found");
				currentCustomer = account;
			}
		}
		
		for(BankAccount account :  currentCustomer.getAccounts()) {
			if(account.getAccountNumber().equals(accountNumber)) {
				account.withdraw(amount);
				System.out.println(account.getBalance());
			}
		}
		
		return true;
	}
	
	public static boolean deposit(String customerUsername,String accountNumber, float amount) {
		
		CustomerAccount currentCustomer = null;
		System.out.println(currentCustomers.size());
		
		for(CustomerAccount account : currentCustomers) {
			System.out.println(account.getUsername());
			System.out.println(customerUsername);
			if(account.getUsername().equals(customerUsername)) {
				System.out.println("Account found");
				currentCustomer = account;
			}
		}
		
		for(BankAccount account :  currentCustomer.getAccounts()) {
			if(account.getAccountNumber().equals(accountNumber)) {
				account.deposit(amount);
				System.out.println(account.getBalance());
			}
		}
		
		return true;
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
	
    public static void addAccount(String customerUsername, String type) {
    	
		CustomerAccount currentCustomer = null;
		System.out.println(currentCustomers.size());
		
		for(CustomerAccount account : currentCustomers) {
			System.out.println(account.getUsername());
			System.out.println(customerUsername);
			if(account.getUsername().equals(customerUsername)) {
				System.out.println("Account found");
				currentCustomer = account;
			}
		}
		
		if (currentCustomer != null) {
			currentCustomer.addAccount(type);
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
	
	private static void loadEmployeeAccounts() {
		
		try {
			File file = new File("employeeaccounts.txt");
			Scanner scanner = new Scanner(file); // finds file
			scanner.useDelimiter(",|\\n"); // uses commas and new line to separate values
			int count = 0;

			while(scanner.hasNext()) { // keeps looping until no more values found
				String username = scanner.next().trim();

				if(!username.equals("")) {
					String password = scanner.next().trim();
					
					System.out.println(username);

					EmployeeAccount c = new EmployeeAccount(username, password);
					currentEmployees.add(c);
				} else {
					break;
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}

	}
	
	public static void loadCustomerAccounts() {

		try {
			File file = new File("customeraccounts.txt");
			Scanner scanner = new Scanner(file); // finds file
			scanner.useDelimiter(",|\\n"); // uses commas and new line to separate values
			int count = 0;

			while(scanner.hasNext()) { // keeps looping until no more values found
				String username = scanner.next().trim();

				if(!username.equals("")) {
					String password = scanner.next().trim();

					CustomerAccount c = new CustomerAccount(username, password);
					currentCustomers.add(c);
				} else {
					break;
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
	}
	
	public static ArrayList<Message> getAllAccountInfo(String customerUsername) {
        CustomerAccount currentCustomer = null;
        System.out.println(currentCustomers.size());
        
        ArrayList<Message> allAccountInfo = new ArrayList<Message>();
        
        for(CustomerAccount account : currentCustomers) {
            System.out.println(account.getUsername());
            System.out.println(customerUsername);
            if(account.getUsername().equals(customerUsername)) {
                System.out.println("Account found");
                currentCustomer = account;
            }
        }
        
        for(BankAccount account :  currentCustomer.getAccounts()) {
            allAccountInfo.add(new Message("AccountInfo", account.getAccountType(), account.getAccountNumber(), account.getBalance(), account.getNickname()));
        }
        
        return allAccountInfo;
        
    }
	
	public static void main(String[] args)
	{
		CustomerAccount c1 = new CustomerAccount("Customer5", "123");
		currentCustomers.add(c1);
		c1.addAccount("Checking", 500);
		c1.addAccount("Savings", 300);
		
		loadEmployeeAccounts();
		
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
							out.writeObject(new Message("Success"));
							System.out.println("CustomerLogin");
						}
						else {
							out.writeObject(new Message("Failure"));
						}
					}
					
					if (message.getMessageType().equals("CustomerLogout")) {
						currentCustomer = "";

						out.writeObject(new Message("Success"));
						System.out.println("CustomerLogout");
					
					}
					
					if (message.getMessageType().equals("EmployeeLogin")) {
						boolean loginResult = verifyEmployeeLogin(message.getUsername(), message.getPassword());
					
						System.out.println(loginResult);
						
						if(loginResult) {
							currentEmployee = message.getUsername();
							out.writeObject(new Message("Success"));
							System.out.println("EmployeeLogin");
						}
						else {
							out.writeObject(new Message("Failure"));
						}
					}
					
					if (message.getMessageType().equals("Withdraw")) {
						boolean loginResult = withdraw(currentCustomer, message.getAccountNumber(), message.getAmount());
						
						if(loginResult) {
							out.writeObject(new Message("Success"));
							System.out.println("Withdraw");
						}
					}
					
					if (message.getMessageType().equals("Deposit")) {
						boolean loginResult = deposit(currentCustomer, message.getAccountNumber(), message.getAmount());
						
						if(loginResult) {
							out.writeObject(new Message("Success"));
							System.out.println("Deposit");
						}
					}
					
					if (message.getMessageType().equals("GetAllAccountInfo")) {
						ArrayList<Message> messages = getAllAccountInfo(currentCustomer);
						
						out.writeObject(messages);
					}
					
					if (message.getMessageType().equals("OpenBankAccount")) {
						addAccount(currentCustomer, "Checking");
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
