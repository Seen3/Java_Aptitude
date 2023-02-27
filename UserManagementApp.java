import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//WE WILL BE USING MONGO ATLAS, so the connection part has to be changed

//We need to install these 2 clients
import com.mongodb.*;
import com.mongodb.client.*;

public class UserManagementApp extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private MongoClient mongoClient;
    private MongoDatabase database;
    private JTextField usernameField;
    private JTextField passwordField;

    public UserManagementApp() {
        // Initialize the MongoDB client and database
        mongoClient = MongoClients.create();
		//Here we actually need to connect to mongiURI from atlas
        database = mongoClient.getDatabase("mydb");

        // Set up the GUI
        setTitle("Admin Panal");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the layout
        setLayout(new GridLayout(2, 2));

        // Add a "Add User" button
        JButton addUserButton = new JButton("Add User");
        addUserButton.addActionListener(this);
        add(addUserButton);

        // Add a "Modify User" button
        JButton modifyUserButton = new JButton("Modify User");
        modifyUserButton.addActionListener(this);
        add(modifyUserButton);

        // Add a "Delete User" button
        JButton deleteUserButton = new JButton("Delete User");
        deleteUserButton.addActionListener(this);
        add(deleteUserButton);

        // Add a "View User" button
        JButton viewUserButton = new JButton("View User"); //This will also function as generateReport, instead of just viewUser, we also get their scores which are stored with userData
        viewUserButton.addActionListener(this);
        add(viewUserButton);

        // Display the GUI
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

        // Insert the user into the database
        MongoCollection<Document> collection = database.getCollection("users");
        Document doc = new Document("username", username).append("password", password);
        collection.insertOne(doc);

        // Show a message dialog to indicate success
        JOptionPane.showMessageDialog(this, "User added successfully.");
    }

    private void modifyUser() {
        // Get the username and password from the text fields
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Update the user in the database
        MongoCollection<Document> collection = database.getCollection("users");
        Document filter = new Document("username", username);
        Document update = new Document("$set", new Document("password", password));
        UpdateResult result = collection.updateOne(filter, update);

        // Show a message dialog to indicate success or failure
        if (result.getModifiedCount() == 1) {
            JOptionPane.showMessageDialog(this, "User modified successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "User not found.");
        }
    }

    private void deleteUser() {
       // Get the username from the text field
	   String username = usernameField.getText();
	     // Delete the user from the database
    MongoCollection<Document> collection = database.getCollection("users");
    Document filter = new Document("username", username);
    DeleteResult result = collection.deleteOne(filter);

    // Show a message dialog to indicate success or failure
    if (result.getDeletedCount() == 1) {
        JOptionPane.showMessageDialog(this, "User deleted successfully.");
    } else {
        JOptionPane.showMessageDialog(this, "User not found.");
    }
}

private void viewUser() {
    // Get the username from the text field
    String username = usernameField.getText();

    // Find the user in the database
    MongoCollection<Document> collection = database.getCollection("users");
    Document filter = new Document("username", username);
    Document result = collection.find(filter).first();

    // Show the user's password in a message dialog
    if (result != null) {
        String password = result.getString("password");
        JOptionPane.showMessageDialog(this, "Password for user " + username + ": " + password);
		//Add the report logic here
		
		
		
		
		
		
		
    } else {
        JOptionPane.showMessageDialog(this, "User not found.");
    }
}

public static void main(String[] args) {
    new UserManagementApp();
}
}