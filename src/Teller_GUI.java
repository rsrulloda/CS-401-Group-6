import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Teller_GUI {
    private JFrame frame;
    private JLabel userLabel, passLabel;
    private JTextField userText;
    private JPasswordField passText;
    private JButton loginButton;
    
    private Teller teller = new Teller();

    public Teller_GUI() {
        frame = new JFrame("Teller GUI - Teller Login");

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
        loginButton.addActionListener(new tellerLogin());
        frame.add(loginButton);

        frame.setSize(300, 150);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void customerLoginFrame() {
        frame = new JFrame("Teller GUI - Customer Login");

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

        frame.setSize(300, 150);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void mainFrame(){
        // Create Frame
        frame = new JFrame("Teller GUI - Main");

        // Create Buttons and Label
        JButton logoutButton = new JButton("Logout");
        JButton selectButton = new JButton("Select");
        JButton openAccountButton = new JButton("Open Account");
        JButton changePasswordButton = new JButton("Change Password");
        JLabel label = new JLabel("Select an Account");

        // Puts Buttons in Bottom Panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.add(logoutButton);
        bottomPanel.add(selectButton);
        bottomPanel.add(openAccountButton);
        bottomPanel.add(changePasswordButton);

        // Sets ScrollPane for Accounts
        JPanel panel = new JPanel();
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);

        for (int i = 0; i < 10; i++) {
            JButton button = new JButton(String.valueOf(i));
            button.setPreferredSize(new Dimension(500, 200));
            panel.add(button);
        }

        JScrollPane scrollPane = new JScrollPane(panel);



        // Sets Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(label, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Sets Frame Settings
        frame.add(mainPanel);
        frame.setSize(600, 700);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void accountFrame() {
        // Create Frame
        frame = new JFrame("Teller GUI - Account");

        // Create Buttons and Label
        JButton closeAccountButton = new JButton("Close Account");
        JButton exitButton = new JButton("Exit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton depositButton = new JButton("Deposit");
        JButton transferButton = new JButton("Transfer");
        JButton editNameButton = new JButton("Edit Name");
        JLabel label = new JLabel("Current Account");

        // Sets Top Panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.add(label);
        topPanel.add(closeAccountButton);

        // Sets Center Panel
        JPanel centerPanel = new JPanel();


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
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void closeAccountFrame() {
        frame = new JFrame("Close Account");

        // Sets Buttons and Label
        JButton cancelButton = new JButton("Cancel");
        JButton proceedButton = new JButton("Proceed");
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

    private class tellerLogin implements ActionListener  {
        public void actionPerformed(ActionEvent e) {
            String user = userText.getText().trim();
            String pass = String.valueOf(passText.getPassword()).trim();

            boolean loginResult = false;
            try {
				loginResult = teller.loginEmployee(user, pass);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
            
            // check user credentials here
            if (loginResult) {
                JOptionPane.showMessageDialog(null, "Login successful");
                frame.dispose(); // close the login frame
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password");
            }
        }
    }

    private class customerLogin implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String user = userText.getText();
            String pass = String.valueOf(passText.getPassword());

            
            
            // check user credentials here
            if (user.equals("admin") && pass.equals("password")) {
                JOptionPane.showMessageDialog(null, "Login successful");
                frame.dispose(); // close the login frame

                mainFrame();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password");
            }
        }
    }


    public static void main(String[] args) {
        new Teller_GUI();
    }
}
