import com.mongodb.client.model.Filters;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;





public class AdminDash extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JLabel usernameLabel, passwordLabel;
    private JTextField usernameField;
    private JTextField passwordField;
    private JLabel questionLabel, option1Label,option2Label,option3Label,option4Label,optionCorrectLabel;
    private JTextField questionField;
    private JTextField option1Field;
    private JTextField option2Field;
    private JTextField option3Field;
    private JTextField option4Field;
    private JTextField optionCorrectField;

    private String[] difficulty={"Easy","Medium","Hard"};
    private JComboBox<String> difficultyDropdown;



    public AdminDash() {
        setTitle("Admin Panal");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel userPanel = new JPanel(new GridLayout(0, 2));
        JPanel questionPanel = new JPanel(new GridLayout(0, 2));



        usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        usernameLabel.setForeground(Color.DARK_GRAY);

        usernameField = new JTextField();
        usernameField.setMaximumSize(new Dimension(100, 20));


        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 18));
        passwordLabel.setForeground(Color.DARK_GRAY);
        passwordField = new JPasswordField();

        JButton addUserButton = new JButton("Add User");
        addUserButton.addActionListener(this);
        addUserButton.setFont(new Font("Arial", Font.BOLD, 18));
        addUserButton.setForeground(Color.DARK_GRAY);

        JButton modifyUserButton = new JButton("Modify User");
        modifyUserButton.addActionListener(this);
        modifyUserButton.setFont(new Font("Arial", Font.BOLD, 18));
        modifyUserButton.setForeground(Color.DARK_GRAY);


        JButton deleteUserButton = new JButton("Delete User");
        deleteUserButton.addActionListener(this);
        deleteUserButton.setFont(new Font("Arial", Font.BOLD, 18));
        deleteUserButton.setForeground(Color.DARK_GRAY);

        JButton viewUserButton = new JButton("View User");
        viewUserButton.addActionListener(this);
        viewUserButton.setFont(new Font("Arial", Font.BOLD, 18));
        viewUserButton.setForeground(Color.DARK_GRAY);


        userPanel.add(usernameLabel);
        userPanel.add(usernameField);
        userPanel.add(passwordLabel);
        userPanel.add(passwordField);
        userPanel.add(addUserButton);
        userPanel.add(modifyUserButton);
        userPanel.add(deleteUserButton);
        userPanel.add(viewUserButton);




        // Add a "Delete User" button



        questionLabel=new JLabel("Question");
        questionField=new JTextField();
        option1Label=new JLabel("Option 1:");
        option1Field=new JTextField();
        option2Label=new JLabel("Option 2:");
        option2Field=new JTextField();
        option3Label=new JLabel("Option 3:");
        option3Field=new JTextField();
        option4Label=new JLabel("Option 4:");
        option4Field=new JTextField();
        optionCorrectLabel=new JLabel("Option Correct:");
        optionCorrectField=new JTextField();

        difficultyDropdown=new JComboBox<String>(difficulty);
        //difficultyDropdown.setBounds(50, 50,90,20);  

        JButton addQuestionButton = new JButton("Add Question");
        addQuestionButton.addActionListener(this);

        JButton modQuestionButton = new JButton("Modify Question");
        modQuestionButton.addActionListener(this);

        JButton delQuestionButton = new JButton("Delete Question");
        delQuestionButton.addActionListener(this);

        questionPanel.add(questionLabel);
        questionPanel.add(questionField);
        questionPanel.add(option1Label);
        questionPanel.add(option1Field);
        questionPanel.add(option2Label);
        questionPanel.add(option2Field);
        questionPanel.add(option3Label);
        questionPanel.add(option3Field);
        questionPanel.add(option4Label);
        questionPanel.add(option4Field);
        questionPanel.add(optionCorrectLabel);
        questionPanel.add(optionCorrectField);
        questionPanel.add(new JLabel("Difficulty:"));
        questionPanel.add(difficultyDropdown);
        questionPanel.add(addQuestionButton);
        questionPanel.add(modQuestionButton);
        questionPanel.add(delQuestionButton);


        JPanel mainPanel = new JPanel(new GridLayout(0, 2));
        mainPanel.add(userPanel);
        mainPanel.add(questionPanel);
        add(mainPanel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Add User")) {
            addUser();
        } else if (command.equals("Modify User")) {
            modifyUser();
        } else if (command.equals("Delete User")) {
            deleteUser();
        } else if (command.equals("View User")) {
            viewUser();
        }
    }

    private void addUser() {
        // Get the username and password from the text fields
        String username = usernameField.getText();
        String password = passwordField.getText();
        System.out.println(username+" "+password);
        DatabaseManager.database.getCollection("loginData").insertOne(new Document("username",username).
                append("password",password).append("score",new Document("easy",0).append("medium",0).append("hard",0)));

    }

    private void modifyUser() {
        // Get the username and password from the text fields
        String username = usernameField.getText();
        String password = passwordField.getText();

        System.out.println(username+" "+password);
        DatabaseManager.database.getCollection("loginData").updateOne(Filters.eq("username", username), new Document("$set", new Document("password", password)));
    }

    private void deleteUser() {
        // Get the username from the text field
        String username = usernameField.getText();
        String password =passwordField.getText();
        System.out.println(username+" "+password);
        DatabaseManager.database.getCollection("loginData").deleteOne(Filters.eq("username", username));
    }

    private void viewUser() {
        // Get the username from the text field
        String username = usernameField.getText();
        //New window to show results.

    }
}