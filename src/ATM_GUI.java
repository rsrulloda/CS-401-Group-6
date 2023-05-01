import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ATM_GUI extends JFrame implements ActionListener 
{
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton withdrawButton, depositButton, balanceButton, loginButton;
    private JLabel balanceLabel;

    public ATM_GUI() 
    {
        setTitle("ATM");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setResizable(false);
        setLocationRelativeTo(null);

        JLabel label1 = new JLabel("ID:");
        JLabel label2 = new JLabel("PIN:");
        textField = new JTextField(10);
        passwordField = new JPasswordField(10);

        loginButton = new JButton("Login");
        withdrawButton = new JButton("Withdraw");
        depositButton = new JButton("Deposit");
        balanceButton = new JButton("Check Balance");

        loginButton.addActionListener(this);
        withdrawButton.addActionListener(this);
        depositButton.addActionListener(this);
        balanceButton.addActionListener(this);

        withdrawButton.setEnabled(false);
        depositButton.setEnabled(false);
        balanceButton.setEnabled(false);

        balanceLabel = new JLabel("Balance: $0.00");

        JPanel panel1 = new JPanel(new GridLayout(2, 2));
        panel1.add(label1);
        panel1.add(textField);
        panel1.add(label2);
        panel1.add(passwordField);

        JPanel panel2 = new JPanel(new GridLayout(4, 1));
        panel2.add(loginButton);
        panel2.add(withdrawButton);
        panel2.add(depositButton);
        panel2.add(balanceButton);

        JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel3.add(balanceLabel);

        setLayout(new BorderLayout());
        add(panel1, BorderLayout.CENTER);
        add(panel2, BorderLayout.EAST);
        add(panel3, BorderLayout.SOUTH);

        withdrawButton.setVisible(false);
        depositButton.setVisible(false);
        balanceButton.setVisible(false);
        
        JLabel titleLabel = new JLabel("ATM", SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        JButton button = (JButton) e.getSource();

        if (button == loginButton)
        {
            withdrawButton.setEnabled(true);
            depositButton.setEnabled(true);
            balanceButton.setEnabled(true);
            loginButton.setEnabled(false);
            
            withdrawButton.setVisible(true);
            depositButton.setVisible(true);
            balanceButton.setVisible(true);
        } 
        else if (isLoggedIn()) 
        {
            actionPerformed1(e);
        } 
        else 
        {
            JOptionPane.showMessageDialog(this, "Please log in first.");
        }
    }

    public void actionPerformed1(ActionEvent e) 
    {
        JButton button = (JButton) e.getSource();

        if (button == withdrawButton)
        {
            String amountString = JOptionPane.showInputDialog(this, "Enter amount to withdraw:");
            
            if (amountString != null)
            {
                double amount = Double.parseDouble(amountString);
                
                if (amount > 0 && amount <= getBalance()) 
                {
                    balanceLabel.setText("Balance: $" + String.format("%.2f", getBalance() - amount));
                    JOptionPane.showMessageDialog(this, "Withdrawal successful.");
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a positive number less than or equal to your balance.");
                }
            }
        } 
        else if (button == depositButton) 
        {
            String amountString = JOptionPane.showInputDialog(this, "Enter amount to deposit:");
            
            if (amountString != null)
            {
                double amount = Double.parseDouble(amountString);
                
                if (amount > 0) 
                {
                    balanceLabel.setText("Balance: $" + String.format("%.2f", getBalance() + amount));
                    JOptionPane.showMessageDialog(this, "Deposit successful.");
                }
                else
                {
                	JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a positive number.");
                }
            }
        } 
        else if (button == balanceButton) 
        {
        	JOptionPane.showMessageDialog(this, "Your balance is: $" + String.format("%.2f", getBalance()));      	
        }
                	
    }
    
    private boolean isLoggedIn()
    {
    	//Should be updated to check if user is actually logged in
        return true; 
    }

    private double getBalance()
    {
    	//Should be updated to retrieve actual account balance
        return 1000.00; 
    }

    public static void main(String[] args) 
    {
        ATM_GUI atm = new ATM_GUI();
        atm.setVisible(true);
    }
}            
