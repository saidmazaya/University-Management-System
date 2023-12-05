package university.management.system;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class UpdateLecturer extends JFrame implements ActionListener {

    JTextField tfnama, tfemail, tfalamat, tfnohp, tahunMasuk;
    JLabel lbnip;
    JDateChooser dcdob;
    JComboBox cbfakultas, cbprodi, cbstatus, cbcategory, cbgender;
    JButton submit, cancel;
    Choice nip;

    UpdateLecturer() {

        setSize(900, 650);
        setLocationRelativeTo(null);

        setLayout(null);

        getContentPane().setBackground(new Color(173, 216, 230)); // Set background color

        JLabel heading = new JLabel("Update Lecturer Details");
        heading.setBounds(50, 10, 500, 50);
        heading.setFont(new Font("Tahoma", Font.ITALIC, 35));
        add(heading);

        JLabel lblselnip = new JLabel("Select NIP");
        lblselnip.setBounds(50, 100, 200, 20);
        lblselnip.setFont(new Font("serif", Font.PLAIN, 20));
        add(lblselnip);

        nip = new Choice();
        nip.setBounds(250, 100, 200, 20);
        add(nip);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from dosen");
            while (rs.next()) {
                nip.add(rs.getString("nip"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel lblname = new JLabel("Nama");
        lblname.setBounds(50, 150, 100, 30);
        lblname.setFont(new Font("serif", Font.BOLD, 20));
        add(lblname);

        tfnama = new JTextField();
        tfnama.setBounds(200, 150, 150, 30);
        add(tfnama);

        JLabel lblGender = new JLabel("Gender");
        lblGender.setBounds(400, 150, 200, 30);
        lblGender.setFont(new Font("serif", Font.BOLD, 20));
        add(lblGender);

        String[] genders = {"Laki-laki", "Perempuan"};
        cbgender = new JComboBox(genders);
        cbgender.setBounds(600, 150, 150, 30);
        cbgender.setBackground(Color.WHITE);
        add(cbgender);

        JLabel lblnip = new JLabel("NIP");
        lblnip.setBounds(50, 200, 200, 30);
        lblnip.setFont(new Font("serif", Font.BOLD, 20));
        add(lblnip);

        lbnip = new JLabel();  // Remove the initial text
        lbnip.setBounds(200, 200, 200, 30);
        lbnip.setFont(new Font("serif", Font.BOLD, 20));
        add(lbnip);

        JLabel lbldob = new JLabel("Tanggal Lahir");
        lbldob.setBounds(400, 200, 200, 30);
        lbldob.setFont(new Font("serif", Font.BOLD, 20));
        add(lbldob);

        dcdob = new JDateChooser();
        dcdob.setBounds(600, 200, 150, 30);
        add(dcdob);

        JLabel lbladdress = new JLabel("Alamat");
        lbladdress.setBounds(50, 250, 200, 30);
        lbladdress.setFont(new Font("serif", Font.BOLD, 20));
        add(lbladdress);

        tfalamat = new JTextField();
        tfalamat.setBounds(200, 250, 150, 30);
        add(tfalamat);

        JLabel lblphone = new JLabel("No Hp");
        lblphone.setBounds(400, 250, 200, 30);
        lblphone.setFont(new Font("serif", Font.BOLD, 20));
        add(lblphone);

        tfnohp = new JTextField();
        tfnohp.setBounds(600, 250, 150, 30);
        add(tfnohp);

        JLabel lblemail = new JLabel("Email");
        lblemail.setBounds(50, 300, 200, 30);
        lblemail.setFont(new Font("serif", Font.BOLD, 20));
        add(lblemail);

        tfemail = new JTextField();
        tfemail.setBounds(200, 300, 150, 30);
        add(tfemail);

        JLabel lblsts = new JLabel("Status");
        lblsts.setBounds(400, 300, 200, 30);
        lblsts.setFont(new Font("serif", Font.BOLD, 20));
        add(lblsts);

        String status[] = {"Junior Lecturer", "Lecturer", "Senior Lecturer", "Head of Department", "Dean", "Rector"};
        cbstatus = new JComboBox(status);
        cbstatus.setBounds(600, 300, 150, 30);
        cbstatus.setFont(new Font("serif", Font.BOLD, 15));
        cbstatus.setBackground(Color.WHITE);
        add(cbstatus);

        JLabel lblcategory = new JLabel("Kategori");
        lblcategory.setBounds(50, 350, 200, 30);
        lblcategory.setFont(new Font("serif", Font.BOLD, 20));
        add(lblcategory);

        String category[] = {"Non Disabilities", "Disabilities"};
        cbcategory = new JComboBox(category);
        cbcategory.setBounds(200, 350, 150, 30);
        cbcategory.setFont(new Font("serif", Font.BOLD, 15));
        cbcategory.setBackground(Color.WHITE);
        add(cbcategory);

        JLabel lblenyear = new JLabel("Tahun Masuk");
        lblenyear.setBounds(400, 350, 200, 30);
        lblenyear.setFont(new Font("serif", Font.BOLD, 20));
        add(lblenyear);

        tahunMasuk = new JTextField();
        tahunMasuk.setBounds(600, 350, 150, 30);
        add(tahunMasuk);

        JLabel lblfakultas = new JLabel("Fakultas");
        lblfakultas.setBounds(50, 400, 200, 30);
        lblfakultas.setFont(new Font("serif", Font.BOLD, 20));
        add(lblfakultas);

        cbfakultas = new JComboBox();
        selectFakultasComboBox();
        cbfakultas.setBounds(200, 400, 150, 30);
        cbfakultas.setBackground(Color.WHITE);

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

        JLabel lblprodi = new JLabel("Prodi");
        lblprodi.setBounds(400, 400, 200, 30);
        lblprodi.setFont(new Font("serif", Font.BOLD, 20));
        add(lblprodi);

        cbprodi = new JComboBox();
        selectProdiComboBox(cbfakultas.getSelectedItem().toString());
        cbprodi.setBounds(600, 400, 150, 30);
        cbprodi.setBackground(Color.WHITE);
        add(cbprodi);

        try {
            Conn con = new Conn();
            Connection connection = con.c;
            nip.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent ie) {
                    try {
                        String selectedNip = nip.getSelectedItem();
                        String query = "select * from dosen where nip=?";
                        PreparedStatement pstmt = connection.prepareStatement(query);
                        pstmt.setString(1, selectedNip);
                        ResultSet rs = pstmt.executeQuery();

                        while (rs.next()) {
                            tfnama.setText(rs.getString("nama"));
                            Date dateFromResultSet = rs.getDate("tgl_lahir");
                            dcdob.setDate(dateFromResultSet);
                            cbgender.setSelectedItem(rs.getString("gender"));
                            tfalamat.setText(rs.getString("alamat"));
                            tfnohp.setText(rs.getString("no_hp"));
                            tfemail.setText(rs.getString("email"));
                            tahunMasuk.setText(rs.getString("tahun_masuk"));
                            cbfakultas.setSelectedItem(rs.getString("fakultas"));
                            cbprodi.setSelectedItem(rs.getString("prodi"));
                            cbstatus.setSelectedItem(rs.getString("status"));
                            cbcategory.setSelectedItem(rs.getString("kategori"));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (ie.getSource() == nip) {
                        try {
                            String selectedNIP = nip.getSelectedItem();

                            // Assuming you have a query to get the corresponding NIP from the database
                            Conn c = new Conn();
                            ResultSet rs = c.s.executeQuery("select * from dosen where nip='" + selectedNIP + "'");

                            if (rs.next()) {
                                String nipValue = rs.getString("nip");
                                lbnip.setText(nipValue);
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
            String nipValue = nip.getSelectedItem(); // Assuming nip is your NIP Choice
            String nama = tfnama.getText();
            String gender = (String) cbgender.getSelectedItem();
            java.sql.Date selectedDate = new java.sql.Date(dcdob.getDate().getTime());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String tgl_lahir = dateFormat.format(selectedDate);
            String alamat = tfalamat.getText();
            String no_hp = tfnohp.getText();
            String email = tfemail.getText();
            String prodi = cbprodi.getSelectedItem().toString();
            String fakultas = cbfakultas.getSelectedItem().toString();
            String status = cbstatus.getSelectedItem().toString();
            String kategori = cbcategory.getSelectedItem().toString();
            String tahun_masuk = tahunMasuk.getText();

            try {
                String query = "UPDATE dosen SET nama=?, gender=?, tgl_lahir=?, alamat=?, no_hp=?, email=?, prodi=?, fakultas=?, status=?, kategori=?, tahun_masuk=? WHERE nip=?";
                Conn con = new Conn();
                Connection connection = con.c;
                PreparedStatement pstmt = connection.prepareStatement(query);

                pstmt.setString(1, nama);
                pstmt.setString(2, gender);
                pstmt.setDate(3, selectedDate);
                pstmt.setString(4, alamat);
                pstmt.setString(5, no_hp);
                pstmt.setString(6, email);
                pstmt.setString(7, prodi);
                pstmt.setString(8, fakultas);
                pstmt.setString(9, status);
                pstmt.setString(10, kategori);
                pstmt.setString(11, tahun_masuk);
                pstmt.setString(12, nipValue);

                pstmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "Lecturer Details Updated Successfully");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new UpdateLecturer();
    }
}
