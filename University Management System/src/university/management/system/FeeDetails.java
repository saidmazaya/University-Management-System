package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

import net.proteanit.sql.DbUtils;

import java.awt.event.*;

public class FeeDetails extends JFrame implements ActionListener {

    Choice lblnim;
    JTable table;
    JButton search, print, add, cancel, preview;

    FeeDetails() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Search by NIM");
        heading.setBounds(20, 20, 150, 20);
        add(heading);

        lblnim = new Choice();
        lblnim.setBounds(180, 20, 150, 20);
        lblnim.insert("Semua Data", 0);
        add(lblnim);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from fee");
            while (rs.next()) {
                lblnim.add(rs.getString("nim"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        table = new JTable();

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from fee");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 100, 900, 600);
        add(jsp);

        search = new JButton("Search");
        search.setBounds(20, 70, 80, 20);
        search.addActionListener(this);
        add(search);

        print = new JButton("Print");
        print.setBounds(120, 70, 80, 20);
        print.addActionListener(this);
        add(print);

        preview = new JButton("Preview");
        preview.setBounds(420, 70, 90, 20);
        preview.addActionListener(this);
        add(preview);

        add = new JButton("Add");
        add.setBounds(220, 70, 80, 20);
        add.addActionListener(this);
        add(add);

        cancel = new JButton("Cancel");
        cancel.setBounds(320, 70, 80, 20);
        cancel.addActionListener(this);
        add(cancel);

        setSize(914, 600);
        setLocation(300, 100);
        setVisible(true);
        setLocationRelativeTo(null);

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load the background image
                ImageIcon backgroundImage = new ImageIcon(ClassLoader.getSystemResource("images/background.jpg"));
                Image img = backgroundImage.getImage();
                // Draw the background image
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };

        // Set the layout manager for the backgroundPanel to null
        backgroundPanel.setLayout(null);

        // Set the bounds of the backgroundPanel
        backgroundPanel.setBounds(0, 0, 900, 650);

        // Add the backgroundPanel to the content pane
        add(backgroundPanel);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) {
            String selectedNIM = lblnim.getSelectedItem();

            if ("Semua Data".equals(selectedNIM)) {
                // Show all data without considering nim
                try {
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("select * from fee");
                    table.setModel(DbUtils.resultSetToTableModel(rs));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                // Search by the selected nim
                String query = "select * from fee where nim = '" + selectedNIM + "'";
                try {
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery(query);
                    table.setModel(DbUtils.resultSetToTableModel(rs));

                    // Show Add, Update, Print buttons when a specific nim is selected
                    add.setVisible(true);
                    print.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (ae.getSource() == print) {
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == add) {
            setVisible(false);
            new StudentFeeForm();
        } else if (ae.getSource() == preview) {
            // Display a preview window or dialog
            new PreviewDialog(this, table.getModel());
        } else {
            setVisible(false);
        }
    }


    public static void main(String[] args) {
        new FeeDetails();
    }
}
