package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

import net.proteanit.sql.DbUtils;

import java.awt.event.*;

public class LecturerDetails extends JFrame implements ActionListener {

    Choice lbnip;
    JTable table;
    JButton search, print, update, add, cancel, preview;

    LecturerDetails() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Search by NIP");
        heading.setBounds(20, 20, 150, 20);
        add(heading);

        lbnip = new Choice();
        lbnip.setBounds(180, 20, 150, 20);
        lbnip.insert("Semua Data", 0);
        add(lbnip);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from dosen");
            while (rs.next()) {
                lbnip.add(rs.getString("nip"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        table = new JTable();

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from dosen");
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
        preview.setBounds(520, 70, 90, 20);
        preview.addActionListener(this);
        add(preview);

        add = new JButton("Add");
        add.setBounds(220, 70, 80, 20);
        add.addActionListener(this);
        add(add);

        update = new JButton("Update");
        update.setBounds(320, 70, 80, 20);
        update.addActionListener(this);
        add(update);

        cancel = new JButton("Cancel");
        cancel.setBounds(420, 70, 80, 20);
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
            String selectedNip = lbnip.getSelectedItem();

            if ("Semua Data".equals(selectedNip)) {
                // Show all data without considering nip
                try {
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("select * from dosen");
                    table.setModel(DbUtils.resultSetToTableModel(rs));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                // Search by the selected nip
                String query = "select * from dosen where nip = '" + selectedNip + "'";
                try {
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery(query);
                    table.setModel(DbUtils.resultSetToTableModel(rs));

                    // Show Add, Update, Print buttons when a specific nip is selected
                    add.setVisible(true);
                    update.setVisible(true);
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
            new AddLecturer();
        } else if (ae.getSource() == preview) {
            // Display a preview window or dialog
            new PreviewDialog(this, table.getModel());
        }  else if (ae.getSource() == update) {
            setVisible(false);
            new UpdateLecturer();
        } else {
            setVisible(false);
        }
    }


    public static void main(String[] args) {
        new LecturerDetails();
    }
}