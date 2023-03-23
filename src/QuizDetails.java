import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

public class QuizDetails extends JFrame implements ActionListener {

    private JLabel label1, label2;
    private JComboBox<Integer> numQuestions;
    private JComboBox<String> difficulty;
    private JButton submit;
    private ObjectId Id;

    public QuizDetails(ObjectId id) throws IOException, FontFormatException {

        // Set up the frame
        super("Quiz Options");
        setLocationRelativeTo(null);
        Id=id;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);

        InputStream is = LoginPage.class.getResourceAsStream("VT323-Regular.ttf");
        Font font = Font.createFont(Font.TRUETYPE_FONT, is);
        Font sizedFont = font.deriveFont(20f);

        // Create a panel to hold the components
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBackground(new java.awt.Color(227, 132, 255));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Set up the components
        label1 = new JLabel("Number of Questions:");
        label1.setFont(font.deriveFont(16f));
        panel.add(label1);

        // Add choices for the number of questions
        Integer[] questionChoices = {10, 20, 30, 40, 50};
        numQuestions = new JComboBox<>(questionChoices);
        panel.add(numQuestions);

        // Add label and choices for the difficulty
        label2 = new JLabel("Difficulty:");
        label2.setFont(font.deriveFont(17f));
        panel.add(label2);

        String[] difficultyChoices = {"Easy", "Medium", "Hard"};
        difficulty = new JComboBox<>(difficultyChoices);
        panel.add(difficulty);

        // Add the submit button
        submit = new JButton("Submit");
        submit.addActionListener(this);
        panel.add(submit);

        // Add the panel to the frame
        add(panel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            int num = (int) numQuestions.getSelectedItem();
            String level = (String) difficulty.getSelectedItem();
            level=level.toLowerCase(Locale.ROOT);
            // Handle the submission
            System.out.println("Number of Questions: " + num);
            System.out.println("Difficulty Level: " + level);
            FindIterable<Document> documents =DatabaseManager.database.getCollection("questionBank").find(new Document("difficulty", level)).limit(num);
            dispose();
            QuizApp q=new QuizApp(documents,num,level,Id);
            q.setVisible(true);
            q.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }
}