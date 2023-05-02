import java.io.*;
import java.net.*;
import java.util.*;

// Client class
class ClientHandler {
	
	// driver code
	public static void main(String[] args) throws IOException
	{

		try (Socket socket = new Socket("localhost", 1234)) {
			
			// writing to server
	        // Input stream socket and output stream socket.
	        InputStream inputStream = socket.getInputStream();
	        OutputStream outputStream = socket.getOutputStream();

	        // Create object output and input stream to send and receive objects
	        ObjectOutputStream out = new ObjectOutputStream(outputStream);
	        ObjectInputStream in = new ObjectInputStream(inputStream);

			// object of scanner class
			Scanner sc = new Scanner(System.in);
	        Message returnMessage = null;

	        // Create and send a login message
	        System.out.println("Attemping Connection...");
	        
	        Message customerLogin = new Message("CustomerLogin");
	        out.writeObject(customerLogin);
	        
	        Message logoutCustomer = new Message("LogoutCustomer");
	        out.writeObject(logoutCustomer);

	        try {
	        	// When a success message is returned, write to console
				returnMessage = (Message) in.readObject();
				
				if (returnMessage.getMessageType().equals("CustomerLogin")) {
					System.out.println("Success!");
				}
				
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
	        
	        try {
	        	// When a success message is returned, write to console
				returnMessage = (Message) in.readObject();
				
				if (returnMessage.getMessageType().equals("LogoutCustomer")) {
					System.out.println("Success!");
				}
				
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
	        
	        sc.close();
	        System.out.println("Closing socket");
	        socket.close();
	    }
	}
}
