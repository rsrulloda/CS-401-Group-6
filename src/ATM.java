public class ATM 
{
	private Customer currentCustomer;
	
    public void loginCustomer(Message<CustomerLogin> loginMessage)
    {
        CustomerLogin login = loginMessage.getData();
        currentCustomer = new Customer(login.getUsername(), login.getPassword());
    }
    
    public void logoutCustomer(Message<CustomerLogout> logoutMessage)
    {
        currentCustomer = null;
    }
    
    public void withdrawRequest(int accountNumber, double amount) 
    {
        if (currentCustomer != null) 
        {
        	
        } 
        else
        {
        	
        }
    }
    
    public void depositRequest(int accountNumber, double amount)
    {
        if (currentCustomer != null) 
        {
        	
        } 
        else 
        {
        	
        }
    }
    
    public void transfer(int account1, int account2, double amount)
    {
        if (currentCustomer != null)
        {
        	
        } 
        else 
        {
        	
        }
    }
}
