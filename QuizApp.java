import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class QuizApp extends JFrame implements ActionListener {

    // GUI Components
    private JPanel panel;
    private JLabel questionLabel;
    private JRadioButton option1, option2, option3, option4;
    private JButton nextButton, prevButton, submitButton;
    private ButtonGroup optionsGroup;
    private JList<String> questionList;
    private JScrollPane questionListScroller;

    // Quiz Questions placeholder, to be imported from Mongo
    private String[][] questions = {
        {"What is the capital of France?", "Paris", "Berlin", "Madrid", "Rome"},
        {"What is the largest planet in our solar system?", "Jupiter", "Saturn", "Mars", "Venus"},
        {"What is the name of the tallest mountain in the world?", "Mount Everest", "Mount Kilimanjaro", "Mount Fuji", "Mount Rushmore"},
        {"Who is the current CEO of Tesla?", "Elon Musk", "Jeff Bezos", "Bill Gates", "Mark Zuckerberg"},
        {"What is the largest continent in the world?", "Asia", "Africa", "North America", "Europe"},
        {"What is the smallest country in the world by land area?", "Vatican City", "Monaco", "Nauru", "Tuvalu"},
        {"What is the chemical symbol for Gold?", "Au", "Ag", "Cu", "Fe"},
        {"What is the name of the longest river in the world?", "Nile", "Amazon", "Mississippi", "Yangtze"},
        {"Who is the author of the Harry Potter series?", "J.K. Rowling", "Stephen King", "George R.R. Martin", "Dan Brown"},
        {"What is the largest ocean in the world?", "Pacific", "Atlantic", "Indian", "Arctic"}
    };

    // Quiz Answers temporary
    private int[] answers = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    // Current Question Index
    private int currentQuestion = 0;

    // Constructor
    public QuizApp() {

        // Set Title
        setTitle("Java Aptitude App");

        // Set Size
        setSize(600, 400);

        // Create Panel
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Create Question Label
        questionLabel = new JLabel();
        panel.add(questionLabel, BorderLayout.NORTH);

        // Create Options
        option1 = new JRadioButton();
        option2 = new JRadioButton();
        option3 = new JRadioButton();
        option4 = new JRadioButton();

        // Create Options Group
        optionsGroup = new ButtonGroup();
        optionsGroup.add(option1);
        optionsGroup.add(option2);
        optionsGroup.add(option3);
        optionsGroup.add(option4);

        // Create Next Button
        nextButton = new JButton("Next");
        nextButton.addActionListener(this);

        // Create Previous Button
        prevButton = new JButton("Prev");
        prevButton.addActionListener(this);

        // Create Submit Button
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);

        // Create Question List
        String[] questionListItems = new String[10];
        for (int i = 0; i < questionListItems.length; i++) {
            questionListItems[i] = "Question " + (i + 1);
        }
        questionList = new JList<String>(questionListItems);
        questionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
       
    // Create Question List Scroller
    questionListScroller = new JScrollPane(questionList);
    questionListScroller.setPreferredSize(new Dimension(150, 0));

    // Add Components to Panel
    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new GridLayout(5, 1));
    centerPanel.add(option1);
    centerPanel.add(option2);
    centerPanel.add(option3);
    centerPanel.add(option4);
    centerPanel.add(submitButton);
    panel.add(centerPanel, BorderLayout.CENTER);
    JPanel southPanel = new JPanel();
    southPanel.setLayout(new FlowLayout());
    southPanel.add(prevButton);
    southPanel.add(nextButton);
    panel.add(southPanel, BorderLayout.SOUTH);
    panel.add(questionListScroller, BorderLayout.WEST);

    // Add Panel to Frame
    add(panel);

    // Set Default Question
    setQuestion(currentQuestion);
}

// Set Question
public void setQuestion(int index) {
    questionLabel.setText(questions[index][0]);
    option1.setText(questions[index][1]);
    option2.setText(questions[index][2]);
    option3.setText(questions[index][3]);
    option4.setText(questions[index][4]);
    optionsGroup.clearSelection();
    switch (answers[index]) {
        case 1:
            option1.setSelected(true);
            break;
        case 2:
            option2.setSelected(true);
            break;
        case 3:
            option3.setSelected(true);
            break;
        case 4:
            option4.setSelected(true);
            break;
        default:
            break;
    }
    questionList.setSelectedIndex(index);
}

// Handle Action Events
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == nextButton) {
        if (currentQuestion < 9) {
            currentQuestion++;
            setQuestion(currentQuestion);
        }
    } else if (e.getSource() == prevButton) {
        if (currentQuestion > 0) {
            currentQuestion--;
            setQuestion(currentQuestion);
        }
    } else if (e.getSource() == submitButton) {
        int correctAnswers = 0;
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == getCorrectAnswerIndex(i)) {
                correctAnswers++;
            }
        }
        JOptionPane.showMessageDialog(this, "You got " + correctAnswers + " out of 10 questions correct!");
    }
}

// Get Correct Answer Index
public int getCorrectAnswerIndex(int questionIndex) {
    for (int i = 1; i < questions[questionIndex].length; i++) {
        if (questions[questionIndex][i].startsWith("*")) {
            return i - 1;
        }
    }
    return -1;
}

public static void main(String[] args) {
    QuizApp quizApp = new QuizApp();
    quizApp.setVisible(true);
    quizApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
}