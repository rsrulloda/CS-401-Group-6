import java.io.*;
import java.net.*;
import java.util.*;

public class ClientHandler{
	
	public enum Interface{
		ATM,
		Teller
	}
	
	protected String currentCustomer;
	protected String currentEmployee;
	protected Interface interFace;
	
	public ClientHandler(String interFace, String currentCustomer, String currentEmployee){
		
		if (interFace.equals("ATM")) {
		    this.interFace = Interface.ATM;
		}else if (interFace.equals("Teller")){
		    this.interFace = Interface.Teller;
		}
		
		this.currentCustomer = currentCustomer;
		this.currentEmployee = currentEmployee;
		
	}
	
	public void setInterface(Interface newInterface) {
		this.interFace =  newInterface;
	}
	
	public void setCurrentCustomer(String newCurrentCustomer) {
		this.currentCustomer =  newCurrentCustomer;
	}
	
	public void setCurrentEmployee(String newCurrentEmployee) {
		this.currentEmployee =  newCurrentEmployee;
	}
	
	public Interface getInterface() {
		return interFace;
	}
	
	public String getCurrentCustomer(String newCurrentCustomer) {
		return currentCustomer;
	}
	
	public String getCurrentEmployee(String newCurrentEmployee) {
		return currentEmployee;
	}
	
	public static void main(String args[]) throws IOException, ClassNotFoundException{
		
		Socket s = new Socket("localhost", 7777);
		
		ObjectOutputStream outputStream = new ObjectOutputStream(s.getOutputStream());
		ObjectInputStream inputStream = new ObjectInputStream(s.getInputStream());
		
		Message text = new Message("CustomerLogin", " ", " ", " ", " ", 0, " ", " ");
		outputStream.writeObject(text);
		text = (Message)inputStream.readObject();
		
		if(text.getStatus().equals("success")) {
			System.out.println("Login successful");
			
		}
		
        s.close();
	}
}
