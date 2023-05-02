import java.io.*;
import java.net.*;
import java.util.*;

// Client class
class ClientHandler{
	
	// driver code
	public static void main(String[] args)
	{
		// establish a connection by providing host and port
		// number
		try (Socket clientSocket = new Socket("localhost", 1234)) {
			
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
				message = (Message) in.readObject();
				System.out.println("Login Success");
				
				while ( (message = (Message) in.readObject()) != null) {
					if(message.getMessageType().equals("success")) {
						System.out.println("Login Success");
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
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
