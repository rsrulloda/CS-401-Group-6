import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class ATM_GUI {
    private JFrame frame;
    private JLabel userLabel, passLabel;
    private JTextField userText;
    private JPasswordField passText;
    private JButton loginButton, logoutButton;
    
    private ATM atm = new ATM();
    
    private int selectedAccountIndex;
    private int selectedTransferAccountIndex;
    private ArrayList<Message> accounts;

    public ATM_GUI() {
        frame = new JFrame("Customer Login");

        userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 10, 80, 25);
        frame.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(100, 10, 160, 25);
        frame.add(userText);

        passLabel = new JLabel("Password:");
        passLabel.setBounds(10, 40, 80, 25);
        frame.add(passLabel);

        passText = new JPasswordField(20);
        passText.setBounds(100, 40, 160, 25);
        frame.add(passText);

        loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        loginButton.addActionListener(new customerLogin());
        frame.add(loginButton);

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(100, 80, 80, 25);
        logoutButton.addActionListener(new logout());
        frame.add(logoutButton);

        frame.setSize(300, 150);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    // Function to allow JList to display multiline strings
    public class MultilineCellRenderer extends JTextArea implements ListCellRenderer<String> {
        public MultilineCellRenderer() {
            setLineWrap(true);
            setWrapStyleWord(true);
        }

        @Override
        public Component getListCellRendererComponent(JList list, String value, int index, boolean isSelected, boolean cellHasFocus) {
            setText(value);
            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }
            return this;
        }
    }
    
    public void mainFrame() throws ClassNotFoundException, IOException{
        // Create Frame
        frame = new JFrame("ATM GUI - Main");

        // Create Buttons and Label
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new customerLogout());
        JButton selectButton = new JButton("Select");
        selectButton.addActionListener(new select());
        JLabel label = new JLabel("Select an Account");

        // Puts Buttons in Bottom Panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.add(logoutButton);
        bottomPanel.add(selectButton);

        // Sets JList for Accounts
        
        JPanel panel = new JPanel();
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);

        accounts = atm.fetchAllAccountInfo();
        
        String[] accountsArray = new String[accounts.size()]; 
    	String account;  
        
        for (int i = 0; i < accounts.size(); i++) {
        	Message currentAccount = accounts.get(i);
        					
        	accountsArray[i] = "Account Type: " + currentAccount.getAccountType() + "\n"
					+ "Account Number: " + currentAccount.getAccountNumber() + "\n"
					+ "Balance: " + String.valueOf(currentAccount.getBalance()) + "\n"
        			+ "Nickname: " + currentAccount.getNickname() + "\n";
        } 
 
        JList accountList = new JList(accountsArray);
        accountList.setFont(new Font("Arial", Font.BOLD, 12));
        frame.add(accountList);


        // Add a ListSelectionListener to the accountList
        accountList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				selectedAccountIndex = accountList.getSelectedIndex();
			}
        });

        
        accountList.setCellRenderer(new MultilineCellRenderer());
        
        // Sets Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(label, BorderLayout.NORTH);
        mainPanel.add(accountList, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Sets Frame Settings
        frame.add(mainPanel);
        frame.setSize(600, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void transferFrame() throws ClassNotFoundException, IOException{
        // Create Frame
        frame = new JFrame("Transfer Window");

        // Create Buttons and Label
        JButton transferButton = new JButton("Transfer");
        transferButton.addActionListener(new transferAction());
        JLabel label = new JLabel("Select an Account");
        
        // Puts Buttons in Bottom Panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.add(transferButton);
        
        // Sets JList for Accounts
        JPanel panel = new JPanel();
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);

        accounts = atm.fetchAllAccountInfo();
        
        String[] accountsArray = new String[accounts.size()]; 
    	String account;  
        
        for (int i = 0; i < accounts.size(); i++) {
        	if (i == selectedAccountIndex) {
        		continue;
        	}
        	
        	Message currentAccount = accounts.get(i);
        					
        	accountsArray[i] = "Account Type: " + currentAccount.getAccountType() + "\n"
					+ "Account Number: " + currentAccount.getAccountNumber() + "\n"
					+ "Balance: " + String.valueOf(currentAccount.getBalance()) + "\n"
        			+ "Nickname: " + currentAccount.getNickname() + "\n";
        } 
 
        JList accountList = new JList(accountsArray);
        accountList.setFont(new Font("Arial", Font.BOLD, 12));
        frame.add(accountList);


        // Add a ListSelectionListener to the accountList
        accountList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				selectedTransferAccountIndex = accountList.getSelectedIndex();
			}
        });

        
        accountList.setCellRenderer(new MultilineCellRenderer());
        
        // Sets Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(label, BorderLayout.NORTH);
        mainPanel.add(accountList, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Sets Frame Settings
        frame.add(mainPanel);
        frame.setSize(600, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void accountFrame() {
        // Create Frame
        frame = new JFrame("atm GUI - Account");

        // Create Buttons and Label
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new exit());
        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(new withdraw());
        JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(new deposit());
        JButton transferButton = new JButton("Transfer");
        transferButton.addActionListener(new transfer());
        JButton editNameButton = new JButton("Edit Name");
        editNameButton.addActionListener(new editName());
        JLabel label = new JLabel("Current Account");

        // Sets Top Panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.add(label);

        // Sets Center Panel
        JPanel centerPanel = new JPanel();
        
        Message currentAccount = accounts.get(selectedAccountIndex);
        
        String accountInfoString = "Account Type: " + currentAccount.getAccountType() + "\n"
		+ "Account Number: " + currentAccount.getAccountNumber() + "\n"
		+ "Balance: " + String.valueOf(currentAccount.getBalance()) + "\n"
		+ "Nickname: " + currentAccount.getNickname();
        
        JTextArea displayAccountInfo = new JTextArea(accountInfoString);
        displayAccountInfo.setFont(new Font("Arial", Font.BOLD, 24));
        
        centerPanel.add(displayAccountInfo);

        // Sets Bottom Panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.add(exitButton);
        bottomPanel.add(withdrawButton);
        bottomPanel.add(depositButton);
        bottomPanel.add(transferButton);
        bottomPanel.add(editNameButton);

        // Sets Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Sets Frame Settings
        frame.add(mainPanel);
        frame.setSize(600, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void closeAccountFrame() {
        frame = new JFrame("Close Account");

        // Sets Buttons and Label
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new cancel());
        JButton proceedButton = new JButton("Proceed");
        proceedButton.addActionListener(new proceed());
        JLabel label = new JLabel("Are you sure the customer wishes to close this account?");

        // Sets Bottom Panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.add(cancelButton);
        bottomPanel.add(proceedButton);

        // Sets Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(label, BorderLayout.NORTH);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Sets Frame Settings
        frame.add(mainPanel);
        frame.setSize(400, 150);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private class customerLogin implements ActionListener { // completed
        public void actionPerformed(ActionEvent e) {
            String user = userText.getText().trim();
            String pass = String.valueOf(passText.getPassword()).trim();

            boolean loginResult = false;

            try {
                loginResult = atm.loginCustomer(user, pass);
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            
            // check user credentials here
            if (loginResult) {
                JOptionPane.showMessageDialog(null, "Login successful");
                frame.dispose(); // close the login frame

                try {
					mainFrame();
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password");
            }
        }
    }

    private class customerLogout implements ActionListener { // completed
        public void actionPerformed(ActionEvent e) {
            frame.dispose(); // close the login frame
        }
    }

    private class select implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            frame.dispose(); // close the login frame
            
            accounts.get(selectedAccountIndex);
            accountFrame();
            
            /*
            try {
				mainFrame();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			*/
        }
    }

    private class openAccount implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            frame.dispose(); // close the login frame
            
    		// Request the type
    		String accountType;
    		int choice;
    		
    		 String[] accountTypes = {
    				 	"Checking",
    				 	"Savings",
    				 	};
    		 
    		 choice = JOptionPane.showOptionDialog(null,
    				 "Select a bank account type", 
    				 "Account Type", 
    				 JOptionPane.OK_OPTION, 
    				 JOptionPane.QUESTION_MESSAGE, 
    				 null, 
    				 accountTypes,
    				 accountTypes[accountTypes.length - 1]);
    		 
    		if (choice == -1) {
    			return;		// dialog was cancelled
    		}
    		 
    		accountType = accountTypes[choice];
            
            try {
            	atm.openBankAccount(accountType);
				mainFrame();
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
    }

    private class changePassword implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            frame.dispose(); // close the login frame
            try {
				mainFrame();
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
    }

    private class closeAccount implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            frame.dispose(); // close the login frame
            try {
				mainFrame();
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
    }

    private class exit implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            frame.dispose(); // close the login frame
            try {
				mainFrame();
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
    }

    private class withdraw implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            frame.dispose(); // close the login frame
            
            Message currentAccount = accounts.get(selectedAccountIndex);
            
            // Request the withdrawal amount
            String input = JOptionPane.showInputDialog(null, "Please enter a withdrawal amount",
                    "Withdrawal", JOptionPane.QUESTION_MESSAGE);
            
            float amount; 
            
            if (input == null) {
                amount = 0;
            } else {
            	amount = Float.parseFloat(input);
            }
            
            try {
            	atm.withdrawRequest(currentAccount.getAccountNumber(), amount);
            	accounts = atm.fetchAllAccountInfo();
				accountFrame();
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
    }

    private class deposit implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            frame.dispose(); // close the login frame
            
            Message currentAccount = accounts.get(selectedAccountIndex);
            
            // Request the deposit amount
            String input = JOptionPane.showInputDialog(null, "Please enter a deposit amount",
                    "Deposit", JOptionPane.QUESTION_MESSAGE);
            
            float amount; 
            
            if (input == null) {
                amount = 0;
            } else {
            	amount = Float.parseFloat(input);
            }          
            try {
            	atm.depositRequest(currentAccount.getAccountNumber(), amount);
               	accounts = atm.fetchAllAccountInfo();
    			accountFrame();
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
    }

    private class transfer implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            frame.dispose(); // close the login frame
            try {
            	transferFrame();
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
    }
    
    private class transferAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            frame.dispose(); // close the login frame
            
            Message currentAccount1 = accounts.get(selectedAccountIndex);
            Message currentAccount2 = accounts.get(selectedTransferAccountIndex);
            // Request the deposit amount
            String input = JOptionPane.showInputDialog(null, "Please enter a transfer amount",
                    "Transfer", JOptionPane.QUESTION_MESSAGE);
            
            float amount;
            
            if (input == null) {
                amount = 0;
            } else {
            	amount = Float.parseFloat(input);
            } 
            
            
            try {
            	atm.transfer(currentAccount1.getAccountNumber(), currentAccount2.getAccountNumber(), amount);
               	accounts = atm.fetchAllAccountInfo();
    			accountFrame();
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
    }

    private class editName implements ActionListener {
    	
        public void actionPerformed(ActionEvent e) {
            frame.dispose(); // close the login frame
            
            Message currentAccount = accounts.get(selectedAccountIndex);
            
            // Request the deposit amount
            String input = JOptionPane.showInputDialog(null, "Please enter a nickname",
                    "Edit Nickname", JOptionPane.QUESTION_MESSAGE);
   
            String newNickname = String.valueOf(input);
            try {
            	
            	atm.editAccountNickname(currentAccount.getAccountNumber(), newNickname);
            	accounts = atm.fetchAllAccountInfo();
            	accountFrame();
    			
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
    }

    private class cancel implements ActionListener { // completed
        public void actionPerformed(ActionEvent e) {
            frame.dispose(); // close the login frame
            accountFrame();
        }
    }

    private class proceed implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            frame.dispose(); // close the login frame
            accountFrame();
        }
    }

    private class logout implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            frame.dispose(); // close the login frame
        }
    }


    public static void main(String[] args) {
        new ATM_GUI();
    }
}
