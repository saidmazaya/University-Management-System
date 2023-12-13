package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.util.*;

import java.awt.event.*;

public class AddSubject extends JFrame implements ActionListener {

    JTextField tfnama;
    JLabel labelmatkulId;
    JComboBox cbfakultas, cbprodi, cbsemester;
    JButton submit, cancel;

    Random ran = new Random();
    long first4 = Math.abs((ran.nextLong() % 9000L) + 1000L);

    AddSubject() {

        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(null);

        getContentPane().setBackground(new Color(173, 216, 230)); // Set background color


        JLabel heading = new JLabel("Tambah Mata Kuliah");
        heading.setBounds(270, 30, 500, 50);
        heading.setFont(new Font("serif", Font.BOLD, 30));
        add(heading);

        JLabel lblname = new JLabel("Nama Matkul");
        lblname.setBounds(50, 150, 140, 30);
        lblname.setFont(new Font("serif", Font.BOLD, 20));
        add(lblname);

        tfnama = new JTextField();
        tfnama.setBounds(200, 150, 150, 30);
        add(tfnama);

        JLabel lblfname = new JLabel("Nama Fakultas ");
        lblfname.setBounds(400, 150, 200, 30);
        lblfname.setFont(new Font("serif", Font.BOLD, 20));
        add(lblfname);

        cbfakultas = new JComboBox();
        selectFakultasComboBox();
        cbfakultas.setBounds(600, 150, 150, 30);
        cbfakultas.setBackground(Color.WHITE);
        add(cbfakultas);

        JLabel lblsemester = new JLabel("Semester");
        lblsemester.setBounds(50, 250, 200, 30);
        lblsemester.setFont(new Font("serif", Font.BOLD, 20));
        add(lblsemester);

        String semester[] = {"1", "2", "3", "4", "5", "6", "7", "8"};
        cbsemester = new JComboBox(semester);
        cbsemester.setBounds(200, 250, 150, 30);
        cbsemester.setFont(new Font("serif", Font.BOLD, 15));
        cbsemester.setBackground(Color.WHITE);
        add(cbsemester);

        // Menambahkan ActionListener ke cbfakultas untuk memperbarui cbprodi saat fakultas dipilih
        cbfakultas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mendapatkan fakultas yang dipilih
                String selectedFakultas = cbfakultas.getSelectedItem().toString();

                // Memperbarui JComboBox "Prodi" berdasarkan "Fakultas" yang dipilih
                selectProdiComboBox(selectedFakultas);
            }
        });

        add(cbfakultas);

        // Menambahkan label dan combo box untuk Prodi
        JLabel lblProd = new JLabel("Prodi");
        lblProd.setBounds(400, 200, 200, 30);
        lblProd.setFont(new Font("serif", Font.BOLD, 20));
        add(lblProd);
        cbprodi = new JComboBox();
        selectProdiComboBox(cbfakultas.getSelectedItem().toString());
        cbprodi.setBounds(600, 200, 150, 30);
        cbprodi.setBackground(Color.WHITE);
        add(cbprodi);

        JLabel lblmatkulId = new JLabel("Id Mata Kuliah");
        lblmatkulId.setBounds(50, 200, 200, 30);
        lblmatkulId.setFont(new Font("serif", Font.BOLD, 20));
        add(lblmatkulId);

        labelmatkulId = new JLabel("101" + first4);
        labelmatkulId.setBounds(200, 200, 200, 30);
        labelmatkulId.setFont(new Font("serif", Font.BOLD, 20));
        add(labelmatkulId);

        submit = new JButton("Submit");
        submit.setBounds(250, 500, 120, 30);
        submit.setBackground(new Color(0, 128, 0)); // Set button background color
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(450, 500, 120, 30);
        cancel.setBackground(new Color(128, 0, 0)); // Set button background color
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);

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
        backgroundPanel.setBounds(0, 0, 900, 610);

        // Add the backgroundPanel to the content pane
        add(backgroundPanel);

        setVisible(true);
    }

    private void selectFakultasComboBox() {
        try {
            // Query to retrieve "Prodi" values from the database
            String query = "SELECT DISTINCT nama_fakultas FROM fakultas";

            Conn con = new Conn();
            java.sql.PreparedStatement pst = con.c.prepareStatement(query);

            // Execute query
            ResultSet rs = pst.executeQuery();

            // Populate the "Prodi" JComboBox with the retrieved values
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            while (rs.next()) {
                model.addElement(rs.getString("nama_fakultas"));
            }

            cbfakultas.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void selectProdiComboBox(String selectedFakultas) {
        try {
            // Query untuk mengambil nilai "Prodi" dari database berdasarkan "Fakultas" yang dipilih
            String query = "SELECT DISTINCT nama_prodi FROM prodi WHERE nama_fakultas = ?";

            Conn con = new Conn();
            java.sql.PreparedStatement pst = con.c.prepareStatement(query);

            // Mengatur fakultas yang dipilih sebagai parameter dalam query
            pst.setString(1, selectedFakultas);

            // Menjalankan query
            ResultSet rs = pst.executeQuery();

            // Mengisi JComboBox "Prodi" dengan nilai yang diambil
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            while (rs.next()) {
                model.addElement(rs.getString("nama_prodi"));
            }

            cbprodi.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String nama_matkul = tfnama.getText();
            String nama_prodi = cbprodi.getSelectedItem().toString();
            String id_matkul = labelmatkulId.getText();
            String semester = cbsemester.getSelectedItem().toString();

            try {
                // Construct and execute the insertion query
                String query = "insert into subject values('" + id_matkul + "', '" + nama_matkul + "', '" + semester + "', '" + nama_prodi + "')";

                Conn con = new Conn();
                con.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Matkul Baru Berhasil Ditambahkan");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new AddSubject();
    }
}
