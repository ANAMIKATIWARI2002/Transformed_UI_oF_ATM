package bank.management.system;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

class EnglishKeyboard extends JFrame implements ActionListener {
    JTextField textField2;
    JTextField displayField; // Text field to display typed characters

    EnglishKeyboard(JTextField textField2) {
        this.textField2 = textField2;

        setTitle("English Keyboard");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel keyBoardPanel = new JPanel(new GridLayout(7, 5));
        
        String[] englishCharacters = {
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
            "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", " ", ".", ","
        };

        for (String character : englishCharacters) {
            JButton button = new JButton(character);
            button.setFont(new Font("Arial", Font.PLAIN, 20));
            button.addActionListener(this);
            keyBoardPanel.add(button);
        }

        // Create and add the display field outside the grid
        displayField = new JTextField();
        displayField.setFont(new Font("Arial", Font.PLAIN, 20));
        displayField.setEditable(false);
        
        add(displayField, BorderLayout.NORTH);
        add(keyBoardPanel, BorderLayout.CENTER);

        // Create and add the "OK" button at the bottom
        JButton okButton = new JButton("OK");
        okButton.setFont(new Font("Arial", Font.PLAIN, 20));
        okButton.addActionListener(this);
        add(okButton, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();
        String character = buttonClicked.getText();
        String currentText = displayField.getText(); // Use the display field
        displayField.setText(currentText + character);
        textField2.setText(currentText + character); // Fill the main text field as well
        
        if ("OK".equals(character)) {
            // Close the keyboard when "OK" button is clicked
            dispose();
        }
    }
}
