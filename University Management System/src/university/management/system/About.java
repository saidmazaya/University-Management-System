package university.management.system;

import javax.swing.*;
import java.awt.*;

public class About extends JFrame {

    About() {
        setSize(700, 500);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/pbol1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);

        // Adjust y-coordinate to create space from the top
        JLabel image = new JLabel(i3);
        image.setBounds(350, 20, 300, 200);
        add(image);


        JLabel heading = new JLabel("<html>University<br/>Management System</html>");
        heading.setBounds(70, 20, 300, 130);
        heading.setFont(new Font("Tahoma", Font.BOLD, 30));
        add(heading);

        JLabel name = new JLabel("Developed By: PBOL Kel 5");
        name.setBounds(70, 220, 550, 40);
        name.setFont(new Font("Tahoma", Font.BOLD, 30));
        add(name);

        JLabel nim = new JLabel("Roll number: 221402-005,014,129");
        nim.setBounds(70, 280, 550, 40);
        nim.setFont(new Font("Tahoma", Font.PLAIN, 30));
        add(nim);

        JLabel contact = new JLabel("Contact: @bpintar.combehh");
        contact.setBounds(70, 340, 550, 40);
        contact.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(contact);

        setLayout(null);

        setVisible(true);
    }

    public static void main(String[] args) {
        new About();
    }
}
