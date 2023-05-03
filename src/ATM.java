import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ATM_GUI extends JFrame implements ActionListener
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JButton loginButton;
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JTextArea accountInfoArea;
    private final JLabel messageLabel;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private CustomerAccount customerAccount;

    public ATM_GUI() 
    {
        super("ATM");

        // create and configure login panel
        loginButton = new JButton("Log In");
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        loginButton.setBackground(new Color(46, 204, 113));
        loginButton.setForeground(Color.WHITE);
        usernameField = new JTextField(10);
        passwordField = new JPasswordField(10);
        JPanel loginPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        loginPanel.setBorder(BorderFactory.createTitledBorder("Login"));
        loginPanel.add(new JLabel("Username:"));
        loginPanel.add(usernameField);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel());
        loginPanel.add(loginButton);

        //Create and configure account info panel
        accountInfoArea = new JTextArea(10, 30);
        accountInfoArea.setEditable(false);
        JPanel accountInfoPanel = new JPanel(new BorderLayout(10, 10));
        accountInfoPanel.setBorder(BorderFactory.createTitledBorder("Account Information"));
        accountInfoPanel.add(new JScrollPane(accountInfoArea), BorderLayout.CENTER);

        //Create and configure main panel
        messageLabel = new JLabel("Enter your username and password to log in.");
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.add(loginPanel, BorderLayout.NORTH);
        mainPanel.add(accountInfoPanel, BorderLayout.CENTER);
        mainPanel.add(messageLabel, BorderLayout.SOUTH);

        loginButton.addActionListener(this);

        add(mainPanel);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new ATM_GUI();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = String.valueOf(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty())
            {
                messageLabel.setText("Please enter your username and password.");
                return;
            }

            try 
            {
                //Create socket and streams
                Socket socket = new Socket("localhost", 1234);
                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());

                //Send login request
                out.writeObject(new Message("CustomerLogin"));

                //Receive login response
                Message response = (Message) in.readObject();

                if (response.login) 
                {
                    //Receive customer account data
                    customerAccount = (CustomerAccount) in.readObject();
                    updateAccountInfo();
                } 
                else 
                {
                    messageLabel.setText("Wrong username or password.");
                }

                //Close streams and socket
                out.close();
                in.close();
                socket.close();

            } catch (IOException | ClassNotFoundException exception) {
                exception.printStackTrace();
            }
        }
    }

    private void updateAccountInfo() 
    {
        ArrayList<BankAccount> accounts = customerAccount.getAccounts();
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-15s%-15s%-15s%-15s%n", "Type", "Number", "Balance", "Nickname"));

        for (BankAccount account : accounts) 
        {
            sb.append(String.format("%-15s%-15s$%-14.2f%-15s%n",
                    account.getAccountType(),
                    account.getAccountNumber(),
                    account.getBalance(),
                    account.getNickname()));
        }

        accountInfoArea.setText(sb.toString());
        messageLabel.setText("Welcome " + customerAccount.getUsername() + ".");
    }
}
