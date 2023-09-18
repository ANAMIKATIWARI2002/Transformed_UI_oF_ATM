package bank.management.system;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener{
    JLabel text,l2,l3;
    JTextField tf1;
    JPasswordField pf2;
    JButton b1,b2;
  
    Login(){
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
        
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image i5 = i4.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel l11 = new JLabel(i6);
        l11.setBounds(325, 260, 100, 100);
        image.add(l11);
        
        text=new JLabel("WELCOME TO ATM");
        text.setBounds(450,300,700,35);
        text.setForeground(Color.BLACK);
        text.setFont(new Font("Osward",Font.BOLD,38));
        image.add(text);
        
        l2 = new JLabel("Card No:");
        l2.setFont(new Font("Raleway", Font.BOLD, 28));
        l2.setForeground(Color.BLACK);
        l2.setBounds(400,450,375,30);
        image.add(l2);
        
        tf1 = new JTextField(15);
        tf1.setBounds(600,450,230,40);
        tf1.setFont(new Font("Arial", Font.BOLD, 14));
        add(tf1);
        
        l3 = new JLabel("PIN:");
        l3.setFont(new Font("Raleway", Font.BOLD, 28));
        l3.setForeground(Color.BLACK);
        l3.setBounds(400,520,375,30);
        image.add(l3);
        
        pf2 = new JPasswordField(15);
        pf2.setFont(new Font("Arial", Font.BOLD, 14));
        pf2.setBounds(600,520,230,40);
        add(pf2);
                
        b1 = new JButton("SIGN IN");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("Arial", Font.BOLD, 16));
        b1.setBounds(490,600,125,40);
        image.add(b1);
        b1.addActionListener(this);
        
        b2 = new JButton("CLEAR");
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.setFont(new Font("Arial", Font.BOLD, 16));
        b2.setBounds(640,600,125,40);
        image.add(b2);
        b2.addActionListener(this);
        
        getContentPane().setBackground(Color.BLACK);
        
    }
    @Override

    public void actionPerformed(ActionEvent ae){
       if(ae.getSource()==b2){
           tf1.setText("");
           pf2.setText("");
       }else if(ae.getSource()== b1){
           conn c=new conn();
           String cardNumber=tf1.getText();
           String pinNumber=pf2.getText();
           String q1="select * from login where cardnumber='"+cardNumber+"' and pin='"+pinNumber+"'";
           try{
               ResultSet rs=c.s.executeQuery(q1);
               if(rs.next()){
                   setVisible(false);
                   new Transactions(pinNumber).setVisible(true);
               }else {
                   JOptionPane.showMessageDialog(null,"Incorrect Card Number or Password");
               }
           }catch(Exception e){
               System.out.println(e);
           }
       }
    }
    public static void main(String[] args){
        new Login().setVisible(true);
        
    }

    
}
