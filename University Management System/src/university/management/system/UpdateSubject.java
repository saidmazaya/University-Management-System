package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateSubject extends JFrame implements ActionListener {

    JTextField tfnama;
    JComboBox cbfakultas, cbprodi, cbsemester;
    JButton submit, cancel;

    JLabel labelmatkulId;

    Choice idmatkul;

    UpdateSubject() {

        setSize(900, 650);
        setLocationRelativeTo(null);

        setLayout(null);

        getContentPane().setBackground(new Color(173, 216, 230)); // Set background color

        JLabel heading = new JLabel("Update LSubject");
        heading.setBounds(50, 10, 500, 50);
        heading.setFont(new Font("Tahoma", Font.ITALIC, 35));
        add(heading);

        JLabel lblselnip = new JLabel("Select Id Mata Kuliah");
        lblselnip.setBounds(50, 100, 200, 20);
        lblselnip.setFont(new Font("serif", Font.PLAIN, 20));
        add(lblselnip);

        idmatkul = new Choice();
        idmatkul.setBounds(250, 100, 200, 20);
        add(idmatkul);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from subject");
            while (rs.next()) {
                idmatkul.add(rs.getString("id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

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

        JLabel lblmatkulId = new JLabel("Id Mata Kuliah");
        lblmatkulId.setBounds(50, 200, 200, 30);
        lblmatkulId.setFont(new Font("serif", Font.BOLD, 20));
        add(lblmatkulId);

        labelmatkulId = new JLabel();
        labelmatkulId.setBounds(200, 200, 200, 30);
        labelmatkulId.setFont(new Font("serif", Font.BOLD, 20));
        add(labelmatkulId);

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

        // Add an ActionListener to cbfakultas to update cbprodi when a faculty is selected
        cbfakultas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected faculty
                String selectedFakultas = cbfakultas.getSelectedItem().toString();

                // Update the "Prodi" JComboBox based on the selected "Fakultas"
                selectProdiComboBox(selectedFakultas);
            }
        });

        add(cbfakultas);

        try {
            Conn con = new Conn();
            Connection connection = con.c;
            idmatkul.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent ie) {
                    try {
                        String selectedNiprodi = idmatkul.getSelectedItem();
                        String query = "select * from subject where id=?";
                        PreparedStatement pstmt = connection.prepareStatement(query);
                        pstmt.setString(1, selectedNiprodi);
                        ResultSet rs = pstmt.executeQuery();

                        while (rs.next()) {
                            tfnama.setText(rs.getString("nama_matkul"));
                            cbprodi.setSelectedItem(rs.getString("nama_prodi"));
                            cbsemester.setSelectedItem(rs.getString("semester"));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (ie.getSource() == idmatkul) {
                        try {
                            String selectedidmatkul = idmatkul.getSelectedItem();

                            // Assuming you have a query to get the corresponding NIP from the database
                            Conn c = new Conn();
                            ResultSet rs = c.s.executeQuery("select * from subject where id='" + selectedidmatkul + "'");

                            if (rs.next()) {
                                String matkulValue = rs.getString("id");
                                labelmatkulId.setText(matkulValue);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


        submit = new JButton("Update");
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

        setVisible(true);
    }

    private void selectProdiComboBox(String selectedFakultas) {
        try {
            // Query to retrieve "Prodi" values from the database based on the selected "Fakultas"
            String query = "SELECT DISTINCT nama_prodi FROM prodi WHERE nama_fakultas = ?";

            Conn con = new Conn();
            java.sql.PreparedStatement pst = con.c.prepareStatement(query);

            // Set the selected fakultas as a parameter in the query
            pst.setString(1, selectedFakultas);

            // Execute query
            ResultSet rs = pst.executeQuery();

            // Populate the "Prodi" JComboBox with the retrieved values
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            while (rs.next()) {
                model.addElement(rs.getString("nama_prodi"));
            }

            cbprodi.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String idMataKuliah = idmatkul.getSelectedItem(); // Assuming id is your subject id
            String namaMatkul = tfnama.getText();
            String semester = cbsemester.getSelectedItem().toString(); // Use cbsemester instead of undeclared variable semester
            String prodi = cbprodi.getSelectedItem().toString();

            try {
                String query = "UPDATE subject SET nama_matkul=?, semester=?, nama_prodi=? WHERE id=?";
                Conn con = new Conn();
                Connection connection = con.c;
                PreparedStatement pstmt = connection.prepareStatement(query);

                pstmt.setString(1, namaMatkul);
                pstmt.setString(2, semester);
                pstmt.setString(3, prodi);
                pstmt.setString(4, idMataKuliah);

                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Subject Details Updated Successfully");
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update subject details");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new UpdateSubject();
    }
}
