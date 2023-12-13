package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class StudentFeeForm extends JFrame implements ActionListener {

    Choice cnim, jlhtagihan;
    JComboBox cbsemester, cbstatus;
    JLabel lbltagihan, labelprodi, lblfname, lblPaymentStatus;
    JButton pay, back;

    JTextField tfjlhpembayaran;

    StudentFeeForm() {
        setSize(900, 500);
        setLocation(300, 100);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/usu.png"));
        Image i2 = i1.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 50, 500, 300);
        add(image);

        JLabel lblrollnumber = new JLabel("Select NIM");
        lblrollnumber.setBounds(40, 60, 150, 20);
        lblrollnumber.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(lblrollnumber);

        cnim = new Choice();
        cnim.setBounds(200, 60, 150, 20);
        add(cnim);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from student");
            while (rs.next()) {
                cnim.add(rs.getString("nim"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Menambahkan label dan combo box untuk Prodi
        JLabel lblProdi = new JLabel("Prodi");
        lblProdi.setBounds(40, 80, 200, 30);
        lblProdi.setFont(new Font("serif", Font.BOLD, 20));
        add(lblProdi);

        labelprodi = new JLabel();
        labelprodi.setBounds(200, 80, 150, 30);
        labelprodi.setBackground(Color.WHITE);
        add(labelprodi);

        lblfname = new JLabel("Nama");
        lblfname.setBounds(40, 120, 150, 20);
        lblfname.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(lblfname);

        JLabel labelfname = new JLabel();
        labelfname.setBounds(250, 120, 150, 20);
        labelfname.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(labelfname);

        JLabel lbljumlah = new JLabel("Jumlah Pembayaran");
        lbljumlah.setBounds(40, 150, 200, 30);
        lbljumlah.setFont(new Font("serif", Font.BOLD, 20));
        add(lbljumlah);

        tfjlhpembayaran = new JTextField();
        tfjlhpembayaran.setBounds(250, 150, 150, 30);
        add(tfjlhpembayaran);

        cnim.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                try {
                    Conn c = new Conn();
                    String query = "select * from student where nim='" + cnim.getSelectedItem() + "'";
                    ResultSet rs = c.s.executeQuery(query);
                    while (rs.next()) {
                        labelfname.setText(rs.getString("nama"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        try {
            Conn c = new Conn();
            String query = "select * from student where nim='" + cnim.getSelectedItem() + "'";
            ResultSet rs = c.s.executeQuery(query);
            while (rs.next()) {
                labelprodi.setText(rs.getString("prodi"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        cnim.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                try {
                    Conn c = new Conn();
                    String query = "select * from student where nim='" + cnim.getSelectedItem() + "'";
                    ResultSet rs = c.s.executeQuery(query);
                    while (rs.next()) {
                        labelprodi.setText(rs.getString("prodi"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        lbltagihan = new JLabel("Jumlah Tagihan");
        lbltagihan.setBounds(40, 200, 500, 20);
        lbltagihan.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(lbltagihan);

        cnim.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                try {
                    Conn c = new Conn();
                    String query = "select * from student where nim='" + cnim.getSelectedItem() + "'";
                    ResultSet rs = c.s.executeQuery(query);
                    if (rs.next()) {
                        labelprodi.setText(rs.getString("prodi"));

                        // Fetch the corresponding major fee based on the selected prodi
                        String majorFeeQuery = "SELECT ukt FROM major_fee WHERE nama_prodi='" + rs.getString("prodi") + "'";

                        try {
                            ResultSet majorFeeResultSet = c.s.executeQuery(majorFeeQuery);

                            if (majorFeeResultSet.next()) {
                                // Set the fee amount directly to the label
                                lbltagihan.setText("Jumlah Tagihan: " + majorFeeResultSet.getString("ukt"));
                            } else {
                                // Handle the case when no fee amount is found for the selected prodi
                                lbltagihan.setText("Jumlah Tagihan: Tidak Ditemukan");
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No student found for the selected NIM");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        JLabel lblsemester = new JLabel("Semester");
        lblsemester.setBounds(40, 220, 200, 30);
        lblsemester.setFont(new Font("serif", Font.BOLD, 20));
        add(lblsemester);

        String semester[] = {"1", "2", "3", "4", "5", "6", "7", "8"};
        cbsemester = new JComboBox(semester);
        cbsemester.setBounds(250, 220, 150, 30);
        cbsemester.setFont(new Font("serif", Font.BOLD, 15));
        cbsemester.setBackground(Color.WHITE);
        add(cbsemester);

        lblPaymentStatus = new JLabel("Status: Belum Bayar");
        lblPaymentStatus.setBounds(40, 300, 400, 20);
        lblPaymentStatus.setFont(new Font("serif", Font.BOLD, 20));
        add(lblPaymentStatus);

        pay = new JButton("Pay Fee");
        pay.setBounds(150, 380, 100, 25);
        pay.setBackground(Color.BLACK);
        pay.setForeground(Color.WHITE);
        pay.addActionListener(this);
        pay.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(pay);

        back = new JButton("Back");
        back.setBounds(270, 380, 100, 25);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        back.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(back);

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
        backgroundPanel.setBounds(0, 0, 900, 500);

        // Add the backgroundPanel to the content pane
        add(backgroundPanel);


        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == pay) {
            String nim = cnim.getSelectedItem();
            String prodi = labelprodi.getText();
            String semester = (String) cbsemester.getSelectedItem();
            String jumlah_pembayaran = tfjlhpembayaran.getText();

            // Determine the payment status based on the conditions
            String status;
            if (jumlah_pembayaran.isEmpty()) {
                status = "Belum Bayar";
                JOptionPane.showMessageDialog(null, "Jumlah pembayaran belum diisi!");
            } else {
                // Fetch the total fee from the database
                int totalFee = getTotalFeeFromDatabase(prodi);
                int pembayaran = Integer.parseInt(jumlah_pembayaran);

                if (pembayaran == totalFee) {
                    status = "Lunas";
                } else if (pembayaran < totalFee) {
                    status = "Belum Lunas";
                    JOptionPane.showMessageDialog(null, "Jumlah pembayaran belum mencukupi!");
                } else {
                    status = "Status Tidak Diketahui"; // Handle other cases if needed
                    JOptionPane.showMessageDialog(null, "Jumlah pembayaran berlebih!");
                    return; // Do not proceed with the database update
                }
            }

            lblPaymentStatus.setText("Status: " + status);

            try {
                Conn con = new Conn();
                Connection connection = con.c;

                // Check if a record with the same nim and semester already exists
                String selectQuery = "SELECT * FROM fee WHERE nim = ? AND semester = ?";
                try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
                    selectStatement.setString(1, nim);
                    selectStatement.setString(2, semester);
                    ResultSet resultSet = selectStatement.executeQuery();

                    if (resultSet.next()) {
                        // If a record already exists, perform an update
                        String updateQuery = "UPDATE fee SET jumlah_pembayaran = ?, status = ? WHERE nim = ? AND semester = ?";
                        try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                            updateStatement.setString(1, jumlah_pembayaran);
                            updateStatement.setString(2, status);
                            updateStatement.setString(3, nim);
                            updateStatement.setString(4, semester);

                            updateStatement.executeUpdate();
                        }
                    } else if ("Lunas".equals(status)) {
                        // If no record exists and payment status is Lunas, perform an insert
                        String insertQuery = "INSERT INTO fee (nim, jumlah_pembayaran, status, semester) VALUES (?, ?, ?, ?)";
                        try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                            insertStatement.setString(1, nim);
                            insertStatement.setString(2, jumlah_pembayaran);
                            insertStatement.setString(3, status);
                            insertStatement.setString(4, semester);

                            insertStatement.executeUpdate();
                        }
                        JOptionPane.showMessageDialog(null, "College fee submitted successfully");
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }


    private int getTotalFeeFromDatabase(String prodi) {
        // Implement the logic to fetch the total fee from the database
        // You may need to modify this part based on your database structure
        int totalFee = 0;
        try {
            Conn c = new Conn();
            String query = "SELECT ukt FROM major_fee WHERE nama_prodi='" + prodi + "'";
            ResultSet rs = c.s.executeQuery(query);

            if (rs.next()) {
                totalFee = rs.getInt("ukt");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalFee;
    }

    public static void main(String[] args) {
        new StudentFeeForm();
    }
}