package bank.management.system;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HindiKeyboard extends JFrame implements ActionListener {
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

        /*JButton okButton = new JButton("ठीक है"); // Set the text to "ठीक है" (OK in Hindi)
        okButton.setFont(new Font("Mangal", Font.PLAIN, 16));
        okButton.addActionListener(this);*/

        add(textDisplay, BorderLayout.NORTH);
        add(keyboardPanel, BorderLayout.CENTER);
        //add(okButton, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();
        String character = buttonClicked.getText();
        if ("ठीक है".equals(character)) {
            // Close the keyboard window
            dispose();
        } else {
            String currentText = textDisplay.getText();
            textDisplay.setText(currentText + character);
            textField.setText(currentText + character);
        }
    }
}
