package bank.management.system;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HindiKeyboard extends JFrame implements ActionListener, WindowListener {
    JTextField textField, textDisplay;

    HindiKeyboard(JTextField textField) {
        this.textField = textField;

        setTitle("Hindi Keyboard");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel keyboardPanel = new JPanel(new GridLayout(5, 5));

        String[] hindiCharacters = {
            // Hindi characters here...
            "अ", "आ", "इ", "ई", "उ", "ऊ", "ऋ", "ए", "ऐ", "ओ", "औ",
            "क", "ख", "ग", "घ", "ङ", "च", "छ", "ज", "झ", "ञ",
            "ट", "ठ", "ड", "ढ", "ण", "त", "थ", "द", "ध", "न",
            "प", "फ", "ब", "भ", "म", "य", "र", "ल", "व", "श", "ष", "स", "ह",
            "ा", "ि", "ी", "ु", "ू", "े", "ै", "ो", "ौ"
        };

        for (String character : hindiCharacters) {
            JButton button = new JButton(character);
            button.setFont(new Font("Mangal", Font.PLAIN, 20));
            button.addActionListener(this);
            keyboardPanel.add(button);
        }

        textDisplay = new JTextField();
        textDisplay.setFont(new Font("Mangal", Font.PLAIN, 20));
        textDisplay.setEditable(false);

        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("ठीक है"); // Set the text to "ठीक है" (OK in Hindi)
        okButton.setFont(new Font("Mangal", Font.PLAIN, 16));
        okButton.addActionListener(this);
        buttonPanel.add(okButton);

        add(textDisplay, BorderLayout.NORTH);
        add(keyboardPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add a window listener to handle the keyboard window close event
        addWindowListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    if (e.getSource() instanceof JButton) {
        JButton buttonClicked = (JButton) e.getSource();
        String character = buttonClicked.getText();
        String currentText = textDisplay.getText();
        textDisplay.setText(currentText + character);
    } else if (e.getActionCommand().equals("ठीक है")) { // Check for the Hindi label
        // Close the keyboard without copying the text
        dispose(); // Close the keyboard window
    }
}


    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosing(WindowEvent e) {
        // Copy the text from textDisplay to the main text field (tf2)
        textField.setText(textDisplay.getText());
        dispose(); // Close the keyboard window
    }

    @Override
    public void windowClosed(WindowEvent e) {}

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}
}
