import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuizGUI extends JFrame implements ActionListener {

    private JLabel label1, label2;
    private JComboBox<Integer> numQuestions;
    private JComboBox<String> difficulty;
    private JButton submit;

    public QuizGUI() {

        // Set up the frame
        super("Quiz Options");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);

        // Create a panel to hold the components
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Set up the components
        label1 = new JLabel("Number of Questions:");
        panel.add(label1);

        // Add choices for the number of questions
        Integer[] questionChoices = {10, 20, 30, 40, 50};
        numQuestions = new JComboBox<>(questionChoices);
        panel.add(numQuestions);

        // Add label and choices for the difficulty
        label2 = new JLabel("Difficulty:");
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

            // Handle the submission
            System.out.println("Number of Questions: " + num);
            System.out.println("Difficulty Level: " + level);
        }
    }

    public static void main(String[] args) {
        QuizGUI quiz = new QuizGUI();
    }
}
