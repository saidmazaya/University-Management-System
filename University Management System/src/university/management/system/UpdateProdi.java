package university.management.system;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class UpdateProdi extends JFrame implements ActionListener {

    JTextField tfnama, tfalamat, tfmahasiswa, tfstaff, tfdosen, tfemail;
    JLabel labelProdId;
    JButton submit, cancel;

    JDateChooser dcdob;
    Choice cProdId;

    JComboBox cbjenjang, cbfakultas;

    UpdateProdi() {

        setSize(900, 600);
        setLocationRelativeTo(null);

        setLayout(null);

        getContentPane().setBackground(new Color(173, 216, 230)); // Set background color

        JLabel heading = new JLabel("Update Prodi Details");
        heading.setBounds(50, 10, 500, 50);
        heading.setFont(new Font("Tahoma", Font.ITALIC, 35));
        add(heading);

        JLabel lblidProd = new JLabel("Pilih Id Prodi");
        lblidProd.setBounds(50, 100, 200, 20);
        lblidProd.setFont(new Font("serif", Font.PLAIN, 20));
        add(lblidProd);

        cProdId = new Choice();
        cProdId.setBounds(250, 100, 200, 20);
        add(cProdId);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from prodi");
            while (rs.next()) {
                cProdId.add(rs.getString("id_prodi"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel lblname = new JLabel("Nama Prodi");
        lblname.setBounds(50, 150, 105, 30);
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

        JLabel lblProdId = new JLabel("Id Prodi");
        lblProdId.setBounds(50, 200, 200, 30);
        lblProdId.setFont(new Font("serif", Font.BOLD, 20));
        add(lblProdId);

        labelProdId = new JLabel();
        labelProdId.setBounds(200, 200, 200, 30);
        labelProdId.setFont(new Font("serif", Font.BOLD, 20));
        add(labelProdId);

        JLabel lbldob = new JLabel("Tanggal Berdiri");
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

        JLabel lbljumlah = new JLabel("Mahasiswa Aktif");
        lbljumlah.setBounds(400, 250, 200, 30);
        lbljumlah.setFont(new Font("serif", Font.BOLD, 20));
        add(lbljumlah);

        tfmahasiswa = new JTextField();
        tfmahasiswa.setBounds(600, 250, 150, 30);
        add(tfmahasiswa);

        JLabel lblemail = new JLabel("Email Prodi");
        lblemail.setBounds(50, 300, 200, 30);
        lblemail.setFont(new Font("serif", Font.BOLD, 20));
        add(lblemail);

        tfemail = new JTextField();
        tfemail.setBounds(200, 300, 150, 30);
        add(tfemail);

        JLabel lbljumDos = new JLabel("Jumlah Dosen");
        lbljumDos.setBounds(400, 300, 200, 30);
        lbljumDos.setFont(new Font("serif", Font.BOLD, 20));
        add(lbljumDos);

        tfdosen = new JTextField();
        tfdosen.setBounds(600, 300, 150, 30);
        add(tfdosen);

        JLabel lbljumStaf = new JLabel("Jumlah Staff");
        lbljumStaf.setBounds(50, 350, 200, 30);
        lbljumStaf.setFont(new Font("serif", Font.BOLD, 20));
        add(lbljumStaf);

        tfstaff = new JTextField();
        tfstaff.setBounds(200, 350, 150, 30);
        add(tfstaff);

        JLabel lblJenj = new JLabel("Jenjang");
        lblJenj.setBounds(400, 350, 200, 30);
        lblJenj.setFont(new Font("serif", Font.BOLD, 20));
        add(lblJenj);

        String course[] = {"D-3", "D-4", "S-1", "S-2", "S-3"};
        cbjenjang = new JComboBox(course);
        cbjenjang.setBounds(600, 350, 150, 30);
        cbjenjang.setBackground(Color.WHITE);
        add(cbjenjang);

        cProdId.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                try {
                    Conn c = new Conn();
                    String query = "select * from prodi where id_prodi='" + cProdId.getSelectedItem() + "'";
                    ResultSet rs = c.s.executeQuery(query);
                    while (rs.next()) {
                        tfnama.setText(rs.getString("nama_prodi"));
                        labelProdId.setText(rs.getString("id_prodi"));
                        Date dateFromResultSet = rs.getDate("tanggal_berdiri");
                        dcdob.setDate(dateFromResultSet);
                        tfmahasiswa.setText(rs.getString("mahasiswa_aktif"));
                        tfalamat.setText(rs.getString("alamat"));
                        tfemail.setText(rs.getString("email"));
                        cbfakultas.setSelectedItem(rs.getString("nama_fakultas"));
                        tfdosen.setText(rs.getString("jumlah_dosen"));
                        tfstaff.setText(rs.getString("jumlah_staff"));
                        cbjenjang.setSelectedItem(rs.getString("jenjang"));

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

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
            String id_prodi = labelProdId.getText();
            String alamat = tfalamat.getText();
            String nama_prodi = tfnama.getText();
            String email = tfemail.getText();
            String nama_fakultas = cbfakultas.getSelectedItem().toString();
            String jumlah_dosen = tfdosen.getText();
            String jumlah_staff = tfstaff.getText();
            String mahasiswa_aktif = tfmahasiswa.getText();
            java.sql.Date selectedDate = new java.sql.Date(dcdob.getDate().getTime());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String tanggal_berdiri = dateFormat.format(selectedDate);
            String jenjang = cbjenjang.getSelectedItem().toString();


            try {
                String query = "update prodi set alamat=?, nama_prodi=?, email=?, "
                        + "nama_fakultas=?, jumlah_dosen=?, jumlah_staff=?, jenjang=?, "
                        + "mahasiswa_aktif=?, tanggal_berdiri=? "
                        + "where id_prodi=?";
                Conn con = new Conn();
                Connection connection = con.c;
                PreparedStatement pstmt = connection.prepareStatement(query);
                pstmt.setString(1, alamat);
                pstmt.setString(2, nama_prodi);
                pstmt.setString(3, email);
                pstmt.setString(4, nama_fakultas);
                pstmt.setString(5, jumlah_dosen);
                pstmt.setString(6, jumlah_staff);
                pstmt.setString(7, jenjang);
                pstmt.setString(8, mahasiswa_aktif);
                pstmt.setString(9, tanggal_berdiri);
                pstmt.setString(10, id_prodi);

                pstmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "Prodi Details Updated Successfully");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }


    public static void main(String[] args) {
        new UpdateProdi();
    }
}
