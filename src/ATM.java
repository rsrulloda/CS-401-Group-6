
import java.io.*;
import java.net.*;
import java.util.*;

class ATM {
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private ObjectOutputStream out;
    private ObjectInputStream in;

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

    public void loginCustomer(String username, String password) throws IOException {
        Message loginCustomer = new Message("CustomerLogin", username, password);
        out.writeObject(loginCustomer);	
        
        System.out.println("Logging in customer");
    }
    
    public void logoutCustomer() throws IOException {
    	Message logoutCustomer = new Message("CustomerLogout");
    	out.writeObject(logoutCustomer);
    	
    	System.out.println("Logging out customer");
    }

    public void close() throws IOException {
        if (socket != null) {
            socket.close();
        }
    }

    public static void main(String[] args) throws IOException {
        ATM atm = new ATM();
        
        String username = "user";
        String password = "password";

        try {
            atm.loginCustomer(username, password);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            atm.close();

        }

        accountInfoArea.setText(sb.toString());
        messageLabel.setText("Welcome " + customerAccount.getUsername() + ".");
    }
}
