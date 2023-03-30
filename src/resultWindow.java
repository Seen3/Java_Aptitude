import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ResultWindow extends JFrame implements ActionListener {
    private JLabel userLabel;
    private JButton okButton;
    private JLabel easyLabel;
    private JLabel mediumLabel;
    private JLabel hardLabel;

    public ResultWindow(String username, int easy, int medium, int hard) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(540, 250);

        userLabel = new JLabel("Here are the results for " + username);

        //connect to database here
        easyLabel = new JLabel("Easy: " + easy);
        mediumLabel = new JLabel("Medium: " + medium);
        hardLabel = new JLabel("Hard: " + hard);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(20, 20, 0, 20);
        panel.add(userLabel, c);

        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets = new Insets(10, 20, 0, 20);
        panel.add(easyLabel, c);

        c.gridy = 2;
        panel.add(mediumLabel, c);

        c.gridy = 3;
        panel.add(hardLabel, c);

        okButton = new JButton("OK");
        okButton.addActionListener(this);
        c.gridy = 4;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(20, 0, 20, 0);
        panel.add(okButton, c);

        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == okButton) {
            dispose(); // close the window when OK button is clicked
        }
    }
}
