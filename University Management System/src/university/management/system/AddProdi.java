package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.*;

import com.toedter.calendar.JDateChooser;

import java.awt.event.*;

public class AddProdi extends JFrame implements ActionListener {

    JTextField tfnama, tfalamat, tfmahasiswa, tfemail, tfdosen, tfstaff;
    JLabel labelprodId;
    JDateChooser dcdob;
    JComboBox cbjenjang, cbfakultas;
    JButton submit, cancel;

    Random ran = new Random();
    long first4 = Math.abs((ran.nextLong() % 9000L) + 1000L);

    AddProdi() {

        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(null);

        getContentPane().setBackground(new Color(173, 216, 230)); // Set background color


        JLabel heading = new JLabel("Tambah Program Studi");
        heading.setBounds(270, 30, 500, 50);
        heading.setFont(new Font("serif", Font.BOLD, 30));
        add(heading);

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

        JLabel lblprodId = new JLabel("Id Prodi");
        lblprodId.setBounds(50, 200, 200, 30);
        lblprodId.setFont(new Font("serif", Font.BOLD, 20));
        add(lblprodId);

        labelprodId = new JLabel("101" + first4);
        labelprodId.setBounds(200, 200, 200, 30);
        labelprodId.setFont(new Font("serif", Font.BOLD, 20));
        add(labelprodId);

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

        JLabel lbljenjang = new JLabel("Jenjang");
        lbljenjang.setBounds(400, 350, 200, 30);
        lbljenjang.setFont(new Font("serif", Font.BOLD, 20));
        add(lbljenjang);

        String course[] = {"D-3", "D-4", "S-1", "S-2", "S-3"};
        cbjenjang = new JComboBox(course);
        cbjenjang.setBounds(600, 350, 150, 30);
        cbjenjang.setBackground(Color.WHITE);
        add(cbjenjang);


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

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String nama_prodi = tfnama.getText();
            String nama_fakultas = cbfakultas.getSelectedItem().toString();
            String email = tfemail.getText();
            String id_prodi = labelprodId.getText();
            Date selectedDate = dcdob.getDate();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String tanggal_berdiri = dateFormat.format(selectedDate);
            String alamat = tfalamat.getText();
            String mahasiswa_aktif = tfmahasiswa.getText();
            String jumlah_dosen = tfdosen.getText();
            String jumlah_staff = tfstaff.getText();
            String jenjang = (String) cbjenjang.getSelectedItem();

            try {
                // Construct and execute the insertion query
                String query = "insert into prodi values('" + nama_prodi + "', '" + nama_fakultas + "', '"
                        + email + "', '" + id_prodi + "', '" + tanggal_berdiri + "', '" + alamat + "', '"
                        + mahasiswa_aktif + "', '" + jumlah_dosen + "', '" + jumlah_staff + "','" + jenjang + "')";

                Conn con = new Conn();
                con.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Prodi Baru Berhasil Ditambahkan");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new AddProdi();
    }
}