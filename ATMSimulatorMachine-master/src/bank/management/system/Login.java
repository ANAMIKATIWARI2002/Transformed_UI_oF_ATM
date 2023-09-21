package bank.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JLabel text, l2, l3;
    JTextField tf1,tf2;
    JPasswordField pf1;
    JButton b1, b2;
    String pinnumber;
    HindiKeyboard hindiKeyboard; 
    EnglishKeyboard englishKeyboard;

    Login() {
        setTitle("AUTOMATED TELLER MACHINE");
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

        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/card1.jpg"));
        Image i5 = i4.getImage().getScaledInstance(200, 300, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel image2 = new JLabel(i6);
        image2.setBounds(800, 300, 200, 300);
        image.add(image2);

        text = new JLabel("WELCOME TO ATM");
        text.setBounds(250, 200, 700, 35);
        text.setForeground(Color.BLACK);
        text.setFont(new Font("osward", Font.BOLD, 38));
        image.add(text);

        text = new JLabel("एटीएम में आपका स्वागत है");
        text.setBounds(250, 280, 700, 35);
        text.setForeground(Color.BLACK);
        text.setFont(new Font("mangal", Font.BOLD, 38));
        image.add(text);

        l2 = new JLabel("NAME:");
        l2.setFont(new Font("Raleway", Font.BOLD, 28));
        l2.setForeground(Color.BLACK);
        l2.setBounds(250, 450, 375, 30);
        image.add(l2);
        
        tf2 = new JTextField(15);
        tf2.setBounds(410, 450, 230, 40);
        tf2.setFont(new Font("arial", Font.BOLD, 14));
        add(tf2);

        l2 = new JLabel("नाम:");
        l2.setFont(new Font("mangal", Font.BOLD, 28));
        l2.setForeground(Color.BLACK);
        l2.setBounds(250, 490, 375, 30);
        image.add(l2);

        tf1 = new JTextField(15);
        tf1.setBounds(410, 490, 230, 40);
        tf1.setFont(new Font("mangal", Font.BOLD, 14));
        add(tf1);
        
        l3 = new JLabel("PIN:");
        l3.setFont(new Font("Raleway", Font.BOLD, 28));
        l3.setForeground(Color.BLACK);
        l3.setBounds(250, 540, 375, 30);
        image.add(l3);

        pf1 = new JPasswordField(15);
        pf1.setBounds(410, 540, 230, 40);
        pf1.setFont(new Font("arial", Font.BOLD, 14));
        add(pf1);
        
        b1 = new JButton("SIGN IN");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("Arial", Font.BOLD, 16));
        b1.setBounds(490, 600, 125, 40);
        image.add(b1);
        b1.addActionListener(this);

        b2 = new JButton("CLEAR");
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.setFont(new Font("Arial", Font.BOLD, 16));
        b2.setBounds(640, 600, 125, 40);
        image.add(b2);
        b2.addActionListener(this);

        getContentPane().setBackground(Color.BLACK);

        // Initialize the HindiKeyboard and EnglishKeyboard
        hindiKeyboard = new HindiKeyboard(tf1);
        englishKeyboard = new EnglishKeyboard(tf2);

        tf1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                // Show the Hindi keyboard when the text field is clicked
                hindiKeyboard.setVisible(true);
                englishKeyboard.setVisible(false); // Hide the English keyboard
            }
        });

        tf2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                // Show the English keyboard when the text field is clicked
                englishKeyboard.setVisible(true);
                hindiKeyboard.setVisible(false); // Hide the Hindi keyboard
            }
        });
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b2) {
            tf1.setText("");
            tf2.setText("");
            pf1.setText("");
        } else if (ae.getSource() == b1) {
            conn c = new conn();
            String Name = tf2.getText();
            String Hname= tf1.getText();
            String pin=pf1.getText();
            String q1 = "select * from login where name='" + Name + "' and hname='" + Hname + "' and pin='" + pin + "'";
            try {
                ResultSet rs = c.s.executeQuery(q1);
                if (rs.next()) {
                    setVisible(false);
                    new hindi_english_option(pinnumber).setVisible(true);
                } else {
                    JLabel label4 = new JLabel("ग़लत कार्ड नंबर ");
                    Font customFont = new Font("mangal", Font.BOLD, 16);
                    label4.setFont(customFont);
                    JOptionPane.showMessageDialog(null, label4);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {
        new Login().setVisible(true);
    }
}
