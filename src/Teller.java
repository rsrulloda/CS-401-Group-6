import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Teller {

 	private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private static ObjectOutputStream out;
    private static ObjectInputStream in;
    
    private static Message returnMessage;
    private ArrayList<Message> returnMessages;

    public Teller() {
        try {
			socket = new Socket("localhost", 1234);
	        inputStream = socket.getInputStream();
	        outputStream = socket.getOutputStream();
	        out = new ObjectOutputStream(outputStream);
	        in = new ObjectInputStream(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public static boolean loginCustomer(String username, String password) throws IOException, ClassNotFoundException {
        Message loginCustomer = new Message("CustomerLogin", username, password);
        out.writeObject(loginCustomer);	
        
        System.out.println("Logging in customer");
        
        returnMessage = (Message) in.readObject();
        
        if (returnMessage.getMessageType().equals("Success")) {
        	return true;
        }
        
        return false;
    }
    
    public void logoutCustomer() throws IOException {
    	Message logoutCustomer = new Message("CustomerLogout");
    	out.writeObject(logoutCustomer);
    	
    	System.out.println("Logging out customer");
    }

    public static boolean loginEmployee(String username, String password) throws IOException, ClassNotFoundException {
        Message loginEmployee = new Message("EmployeeLogin", username, password);
        out.writeObject(loginEmployee);	
        
        System.out.println("Logging in employee");
        
        returnMessage = (Message) in.readObject();
        
        System.out.println(returnMessage.getMessageType());
        
        if (returnMessage.getMessageType().equals("Success")) {
        	return true;
        }
        
        return false;
    }


    public void logoutEmployee() throws IOException {
    	Message logoutEmployee = new Message("EmployeeLogout");
    	out.writeObject(logoutEmployee);
    	
    	System.out.println("Logging out employee");
    }


    public void withdrawRequest(String accountNumber, float amount) throws IOException {
    	Message withdrawMessage = new Message("Withdraw", accountNumber, amount);
    	out.writeObject(withdrawMessage);
    	
    }

    public void depositRequest(String accountNumber, float amount) throws IOException {
    	Message depositMessage = new Message("Deposit", accountNumber,amount);
    	out.writeObject(depositMessage);
    }

    public void transfer(BankAccount account1, BankAccount account2, float amount) {
    }


    public void closeAcc(String accountNumber) {
    }

    public void openAcc() {
    }

    public void editAcc(String accountNumber, String nickname) {
    }
    
    // Close the socket
    public void close() throws IOException {
        if (socket != null) {
            socket.close();
        }
    }

    /*
    public static void main(String[] args) throws IOException, ClassNotFoundException {
    	Teller teller = new Teller();
        
        String username = "employee1";
        String password = "123";

        try {
            boolean loginResult = loginEmployee(username, password);
            if (loginResult) {
            	System.out.println("Login successful!");
            } else {
            	System.out.println("Login failed.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            teller.close();
        }
    }
    */
}
