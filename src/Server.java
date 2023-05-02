import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

//Server class
class Server {
	
	protected static ArrayList<ClientHandler> currentClients;
	//protected ArrayList<EmployeeAccount> currentEmployees;
	protected static ArrayList<CustomerAccount> currentCustomers;
	protected static ArrayList<BankAccount> accounts;

	
	public static boolean verifyCustomerLogin(String Username, String Password){
		for (CustomerAccount account : currentCustomers) {
            if (account.getUsername().equals(Username) && account.getPassword().equals(Password)) {
	            	return true;
            	}
		}
		return false;
    }
	
	public static float withdraw(String accountNumber, float amount) {
		for(BankAccount account : accounts) {
			if(account.getAccountNumber().equals(accountNumber)) {
				amount = account.getBalance() - amount; 
			}
		}
		
		return amount;
	}
	
	public static float deposit(String accountNumber, float amount) {
		for(BankAccount account : accounts) {
			if(account.getAccountNumber().equals(accountNumber)) {
				amount = account.getBalance() + amount; 
			}
		}
		
		return amount;
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
		ServerSocket server = null;

		try {

			server = new ServerSocket(1234);
			server.setReuseAddress(true);

			while (true) {

				Socket client = server.accept();
				System.out.println("New client connected "+ client.getInetAddress().getHostAddress());
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
							out.writeObject(new Message("CustomerLogin"));
							System.out.println("CustomerLogin");
						}
					}
					
					else if (message.getMessageType().equals("LogoutCustomer")) {
						boolean logoutResult = logoutCustomer();
						if(logoutResult) {
							state =  message.getLogin();
							out.writeObject(new Message("success"));
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
