import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.types.ObjectId;


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
    private String[][] questions;

    // Quiz Answers temporary
    private int[] answers = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    // Current Question Index
    private int currentQuestion = 0;
    private int numQuestions=0;
    private ObjectId Id;
    private String Difficulty;

    // Constructor
    public QuizApp(FindIterable<Document> documents, int numq, String diff, ObjectId id) {

        this.questions=ObjectConverter.convert(documents);
        numQuestions=numq;
        Difficulty=diff;
        Id=id;
        //System.out.println(this.questions);
        // Set Title
        setTitle("Java Aptitude App");

        // Set Size
        setSize(600, 400);
        setLocationRelativeTo(null);
        // Create Panel
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new java.awt.Color(227, 132, 255));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

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
        String[] questionListItems = new String[numQuestions];
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
        //System.out.println(index+" "+questions[index]);
        questionLabel.setText(this.questions[index][0]);
        option1.setText(this.questions[index][1]);
        option2.setText(this.questions[index][2]);
        option3.setText(this.questions[index][3]);
        option4.setText(this.questions[index][4]);
        String correct=this.questions[index][5];
        optionsGroup.clearSelection();
        questionList.setSelectedIndex(index);
        ActionListener[] listeners1 = option1.getActionListeners();
        for (ActionListener listener : listeners1) {
            option1.removeActionListener(listener);
        }

        ActionListener[] listeners2 = option2.getActionListeners();
        for (ActionListener listener : listeners2) {
            option2.removeActionListener(listener);
        }

        ActionListener[] listeners3 = option3.getActionListeners();
        for (ActionListener listener : listeners3) {
            option3.removeActionListener(listener);
        }

        ActionListener[] listeners4 = option4.getActionListeners();
        for (ActionListener listener : listeners4) {
            option4.removeActionListener(listener);
        }
        option1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (option1.getText().equals(correct)) {
                    answers[index]=1;
                } else {
                    answers[index]=0;
                }
            }
        });

        option2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (option2.getText().equals(correct)) {
                    answers[index]=1;
                } else {
                    answers[index]=0;
                }
            }
        });

        option3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (option3.getText().equals(correct)) {
                    answers[index]=1;
                } else {
                    answers[index]=0;
                }
            }
        });

        option4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (option4.getText().equals(correct)) {
                    answers[index]=1;
                } else {
                    answers[index]=0;
                }
            }
        });
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
                if (answers[i] == 1) {
                    correctAnswers++;
                }
            }
            JOptionPane.showMessageDialog(this, "You got " + correctAnswers + " out of "+ numQuestions+" questions correct!");
            int percentage=(int)(((float)correctAnswers/numQuestions)*100);
            System.out.println(Id+" "+percentage+" "+correctAnswers+" "+numQuestions);
            UpdateResult result=DatabaseManager.database.getCollection("loginData").updateOne(Filters.eq("_id", Id), new Document("$set", new Document("score."+Difficulty, percentage)));
            System.out.println(result.getModifiedCount());
            dispose();
        }
    }
}