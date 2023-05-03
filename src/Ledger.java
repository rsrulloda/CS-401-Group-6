import java.util.Date;
import java.io.FileWriter; 
import java.io.IOException;


public class Ledger {
	
		private static final String LEDGER_FORMAT = "%s %s %s %s      %s";
		
		public float amnt;			
		public Date time;		
		public String typ;
		public int account;
		public int counter=0;
		
		public Ledger(Date open, int accNum, float amount, String type ) {
			
			amnt = amount;
			time = open;
			typ = type;
			account=accNum;
			counter++;
			
		}
		
	
		public String toFile() {
			
			
			
			String text= String.format(LEDGER_FORMAT,counter, time, typ,account, "$"+amnt );
			 
			try {
				 
		            // Create a FileWriter object
		            // to write in the file
		            FileWriter fWriter = new FileWriter(
		                "filename");
		 
		            // Writing into file
		            // Note: The content taken above inside the
		            // string
		            fWriter.write(text);
		 
		            // Printing the contents of a file
		            System.out.println(text);
		 
		            // Closing the file writing connection
		            fWriter.close();
		 
		            // Display message for successful execution of
		            // program on the console
		            System.out.println(
		                "File is created successfully with the content.");
		            
		        }
		 
		        // Catch block to handle if exception occurs
		        catch (IOException e) {
		 
		            // Print the exception
		            System.out.print(e.getMessage());
		        }
		}
		

}