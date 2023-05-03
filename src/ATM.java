import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ATM {

 	private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private static ObjectOutputStream out;
    private static ObjectInputStream in;
    
    private static Message returnMessage;
    private ArrayList<Message> returnMessages;

    public ATM() {
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


    public boolean withdrawRequest(String accountNumber, float amount) throws IOException, ClassNotFoundException {
    	Message withdrawMessage = new Message("Withdraw", accountNumber, amount);
    	out.writeObject(withdrawMessage);
    	
    	returnMessage = (Message) in.readObject();
        
        System.out.println(returnMessage.getMessageType());
        
        if (returnMessage.getMessageType().equals("Success")) {
        	return true;
        }
        
        return false;
    	
    }

    public boolean depositRequest(String accountNumber, float amount) throws IOException, ClassNotFoundException {
    	Message depositMessage = new Message("Deposit", accountNumber,amount);
    	out.writeObject(depositMessage);
    	
    	returnMessage = (Message) in.readObject();
        
        System.out.println(returnMessage.getMessageType());
        
        if (returnMessage.getMessageType().equals("Success")) {
        	return true;
        }
        
        return false;
    }

    public void transfer(String account1, String account2, float amount) throws IOException {
    	Message transferMessage = new Message("Transfer", account1, account2, amount);
    	out.writeObject(transferMessage);
    }


    public void closeBankAccount(String accountNumber) {
    	
    }

    public void openBankAccount(String accountType) throws IOException {
    	Message openBankAccountMessage = new Message("OpenBankAccount", accountType);
    	out.writeObject(openBankAccountMessage);
    }
    
    public Message fetchAccountInfo(String accountNumber) throws ClassNotFoundException, IOException {
    	Message getAccountInfoMessage = new Message("GetAccountInfo", accountNumber);
    	out.writeObject(getAccountInfoMessage);
    	
    	returnMessage = (Message) in.readObject();
    	return returnMessage;
    }
    
    public ArrayList<Message> fetchAllAccountInfo() throws ClassNotFoundException, IOException {
    	Message getAllAccountInfoMessage = new Message("GetAllAccountInfo");
    	out.writeObject(getAllAccountInfoMessage);
    	
    	returnMessages = (ArrayList<Message>) in.readObject();
    	return returnMessages;
    }
    
    public void editAccountNickname(String accountNumber, String nickname) throws ClassNotFoundException, IOException {
    	Message editAccountNicknameMessage = new Message("EditAccountNickname", accountNumber, nickname);
    	out.writeObject(editAccountNicknameMessage);
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
