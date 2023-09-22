package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class enterpin_hindi extends JFrame implements ActionListener {
    JButton withdraw, back;
    String pinnumber;
    private JPanel keyboardPanel;
    private int wrongAttempts = 0; // Counter for wrong PIN attempts
    private JTextField pinField; // Add a pin text field variable

    public enterpin_hindi(String pinnumber) {
        this.pinnumber = pinnumber;
        setSize(1300, 850);
        setLocation(100, 0);
        setLayout(null);
        setVisible(true);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1300, 850, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 1300, 850);
        add(image);

        JLabel text = new JLabel("अपना पिन नंबर दर्ज करें");
        text.setForeground(Color.BLACK);
        text.setFont(new Font("mangal", Font.BOLD, 16));
        text.setBounds(250, 250, 400, 20);
        image.add(text);

        pinField = new JTextField(); // Initialize the pin text field
        pinField.setFont(new Font("Raleway", Font.BOLD, 22));
        pinField.setBounds(250, 280, 320, 25);
        pinField.setBackground(Color.WHITE); // Set the initial background color to white
        image.add(pinField); // Add the pin text field

        JLabel text2 = new JLabel("प्रवेश करना");
        text2.setBounds(900, 410, 150, 64);
        text2.setForeground(Color.BLACK);
        text2.setFont(new Font("mangal", Font.BOLD, 20));
        image.add(text2);

        withdraw = new JButton("");
        withdraw.setBounds(1095, 408, 138, 64);
        image.add(withdraw);
        withdraw.addActionListener(this);

        JLabel text4 = new JLabel("पीछे जाए");
        text4.setBounds(900, 474, 150, 64);
        text4.setForeground(Color.BLACK);
        text4.setFont(new Font("mangal", Font.BOLD, 20));
        image.add(text4);

        back = new JButton("");
        back.setBounds(1095, 478, 138, 64);
        image.add(back);
        back.addActionListener(this);

        // Create and configure the keyboard panel
        keyboardPanel = new JPanel();
        keyboardPanel.setLayout(new GridLayout(4, 3)); // 4 rows and 3 columns
        keyboardPanel.setBounds(250, 320, 320, 320); // Position and size of the keyboard

        // Define the ATM keyboard buttons
        String[] buttonLabels = {
            "1", "2", "3",
            "4", "5", "6",
            "7", "8", "9",
            "", "0", "Clear"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this); // Add action listener to handle button clicks
            keyboardPanel.add(button);
        }

        image.add(keyboardPanel); // Add the keyboard panel to the main image panel
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == withdraw) {
            String number = pinField.getText(); // Use pinField instead of amount
            if (number.equals("")) {
                JLabel label8 = new JLabel("कृपया अपना पिन नंबर दर्ज करें");
                Font customFont = new Font("mangal", Font.BOLD, 16); // You can change the font properties here
                label8.setFont(customFont);
                JOptionPane.showMessageDialog(null, label8);
            } else {
                conn c = new conn();
                String q1 = "select * from login where pin='" + number + "'";
                try {
                    ResultSet rs = c.s.executeQuery(q1);
                    if (rs.next()) {
                        setVisible(false);
                        new exitpage_hindi().setVisible(true);
                    } else {
                        JLabel label9 = new JLabel("ग़लत पिन नंबर");
                            Font customFont4 = new Font("mangal", Font.BOLD, 16);
                            label9.setFont(customFont4);
                            JOptionPane.showMessageDialog(null, label9);
                        wrongAttempts++; // Increment the wrong attempts counter
                        if (wrongAttempts == 1) {
                            // Set the background color of pinField to yellow after the first wrong attempt
                            pinField.setBackground(Color.YELLOW);
                            JLabel label5 = new JLabel("ग़लत पिन, सावधानी से दर्ज करें");
                            Font customFont1 = new Font("mangal", Font.BOLD, 16);
                            label5.setFont(customFont1);
                            JOptionPane.showMessageDialog(null, label5);
                        } else if (wrongAttempts == 2) {
                            JLabel label6 = new JLabel("आखिरी अवसर, इसके बाद खाता ब्लॉक हो जाएगा");
                            Font customFont2 = new Font("mangal", Font.BOLD, 16);
                            label6.setFont(customFont2);
                            JOptionPane.showMessageDialog(null, label6);
                        } else if (wrongAttempts == 3) {
                            JOptionPane.showMessageDialog(null, "तीन ग़लत प्रयास, खाता ब्लॉक हो गया");
                            System.exit(0); // Exit the application after three wrong attempts
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Transactions_hindi(pinnumber).setVisible(true);
        } else {
            // Handle button clicks from the keyboard panel
            String buttonText = ((JButton) ae.getSource()).getText();
            if (buttonText.equals("Clear")) {
                pinField.setText(""); // Clear the pinField
            } else {
                // Append the button text to the pinField
                String currentPin = pinField.getText();
                pinField.setText(currentPin + buttonText);
            }
        }
    }

    public static void main(String args[]) {
        new enterpin_hindi("");
    }
}
