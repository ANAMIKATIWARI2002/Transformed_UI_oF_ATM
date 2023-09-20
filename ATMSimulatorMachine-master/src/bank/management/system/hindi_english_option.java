package bank.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class hindi_english_option extends JFrame implements ActionListener{
    JButton hindi,english;
    String pinnumber;
    public hindi_english_option(String pinnumber) {
        this.pinnumber=pinnumber;
        setUndecorated(true);
        
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

        JLabel text = new JLabel("please select your language");
        text.setForeground(Color.BLACK);
        text.setFont(new Font("System", Font.BOLD,22));
        text.setBounds(200, 140, 1000, 400);
        image.add(text);
        
        
        JLabel text2 = new JLabel("कृपया अपनी भाषा चुनें");
        text2.setForeground(Color.BLACK);
        text2.setFont(new Font("mangal", Font.BOLD, 22));
        text2.setBounds(200, 218, 1000, 400);
        image.add(text2);
        
        JLabel text6=new JLabel("English");
        text6.setBounds(900,540,200,64);
        text6.setForeground(Color.BLACK);
        text6.setFont(new Font("System",Font.BOLD,20));
        image.add(text6);
        
        english =new JButton("");
        english.setBounds(1095,550,138,64);
        image.add(english);
        english.addActionListener(this);
        
        JLabel text7=new JLabel("हिंदी");
        text7.setBounds(900,618,150,64);
        text7.setForeground(Color.BLACK);
        text7.setFont(new Font("mangal",Font.BOLD,20));
        image.add(text7);
        
        hindi = new JButton("");
        hindi.setBounds(1095,620,138,64);
        image.add(hindi);
        hindi.addActionListener(this);

        //setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == hindi) {
            setVisible(false);
            new Transactions_hindi(pinnumber).setVisible(true);
        }
        else{
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }
    }

    public static void main(String args[]) {
        new hindi_english_option("");
    }
}
