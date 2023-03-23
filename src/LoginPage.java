import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

import org.bson.Document;
import org.bson.types.ObjectId;

public class LoginPage extends JFrame implements ActionListener {
    private JLabel titleLabel,usernameLabel,passwordLabel;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JButton loginButton,cancelButton;

    public LoginPage() throws FontFormatException, IOException {
        InputStream is = LoginPage.class.getResourceAsStream("VT323-Regular.ttf");
        Font font = Font.createFont(Font.TRUETYPE_FONT, is);
        Font sizedFont = font.deriveFont(20f);

        setTitle("Login Page");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new java.awt.Color(227, 132, 255));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);

        titleLabel = new JLabel("Login Page");
        titleLabel.setFont(font.deriveFont(30f));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(titleLabel, constraints);

        usernameLabel = new JLabel("Username");
        constraints.gridx = 0;
        usernameLabel.setFont(font.deriveFont(18f));
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panel.add(usernameLabel, constraints);

        usernameTextField = new JTextField(20);
        usernameTextField.setBackground(new java.awt.Color(255, 163, 253));
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panel.add(usernameTextField, constraints);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(font.deriveFont(18f));
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        panel.add(passwordLabel, constraints);

        passwordField = new JPasswordField(20);
        passwordField.setBackground(new java.awt.Color(255, 163, 253));

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        panel.add(passwordField, constraints);

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        loginButton.setBackground(new java.awt.Color(143, 93, 255));
        loginButton.setFont(sizedFont);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        panel.add(loginButton, constraints);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        cancelButton.setBackground(new java.awt.Color(143, 93, 255));
        cancelButton.setFont(sizedFont);
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        panel.add(cancelButton, constraints);

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameTextField.getText();
            String password = new String(passwordField.getPassword());
            System.out.println(username+" "+password);

            Document document=DatabaseManager.database.getCollection("loginData").find(new Document("username", username)).first();
            String storedPass=document.getString("password");
            System.out.println(storedPass+" "+password);
            if(!storedPass.equals(password))
            {
                JOptionPane.showMessageDialog(null, "Invalid Login!");
            }
            else{
                System.out.println("Correct Password");
                dispose();
                try {
                    ObjectId id = document.getObjectId("_id");
                    if(!id.toString().equals("641af8ed6f8a623fc9dc1351")){
                        QuizDetails q = new QuizDetails(id);
                    }
                    else{
                        AdminDash a=new AdminDash();
                    }

                } catch (Exception q) {
                    System.out.println("Error");
                    // Handle the exception here
                }
            }
            /*
            System.out.println("Correct Password");
            dispose();
            try {
                QuizDetails q = new QuizDetails();
            } catch (Exception q) {
                System.out.println("Error");
                // Handle the exception here
            }*/
        } else if (e.getSource() == cancelButton) {
            dispose();
        }
    }
}
