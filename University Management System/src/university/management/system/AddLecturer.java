// Import library yang diperlukan
package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.*;

import com.toedter.calendar.JDateChooser;

import java.awt.event.*;

// Deklarasi kelas AddLecturer yang meng-extend JFrame dan meng-implement ActionListener
public class AddLecturer extends JFrame implements ActionListener {

    // Deklarasi variabel-variabel yang akan digunakan
    JTextField tfnama, tfemail, tfalamat, tfnohp, tahunMasuk;

    JLabel lbnip;
    JDateChooser dcdob;
    JComboBox cbfakultas, cbprodi, cbstatus, cbcategory;
    JComboBox<String> genderComboBox;
    JButton submit, cancel;

    // Membuat objek Random untuk meng-generate NIM secara acak
    Random ran = new Random();
    long first4 = Math.abs((ran.nextLong() % 9000000L) + 1000000L);

    // Konstruktor kelas AddLecturer
    AddLecturer() {

        // Mengatur ukuran dan lokasi frame
        setSize(900, 600);
        setLocationRelativeTo(null);

        // Mengatur layout menjadi null
        setLayout(null);
        // Mengatur warna latar belakang
        getContentPane().setBackground(new Color(173, 216, 230));
        JLabel heading = new JLabel("Tambah Dosen Baru ");
        heading.setBounds(260, 30, 500, 50);
        heading.setFont(new Font("serif", Font.BOLD, 30));
        add(heading);

        // Menambahkan label dan field untuk Nama
        JLabel lblname = new JLabel("Nama");
        lblname.setBounds(50, 150, 100, 30);
        lblname.setFont(new Font("serif", Font.BOLD, 20));
        add(lblname);
        tfnama = new JTextField();
        tfnama.setBounds(200, 150, 150, 30);
        add(tfnama);

        // Menambahkan label dan combo box untuk Gender
        JLabel lblGender = new JLabel("Gender");
        lblGender.setBounds(400, 150, 200, 30);
        lblGender.setFont(new Font("serif", Font.BOLD, 20));
        add(lblGender);
        String[] genders = {"Laki-laki", "Perempuan"};
        genderComboBox = new JComboBox<>(genders);
        genderComboBox.setBounds(600, 150, 150, 30);
        genderComboBox.setBackground(Color.WHITE);
        add(genderComboBox);

        // Menambahkan label dan nilai NIM
        JLabel lblnip = new JLabel("NIP");
        lblnip.setBounds(50, 200, 200, 30);
        lblnip.setFont(new Font("serif", Font.BOLD, 20));
        add(lblnip);
        lbnip = new JLabel("20" + first4);
        lbnip.setBounds(200, 200, 200, 30);
        lbnip.setFont(new Font("serif", Font.BOLD, 20));
        add(lbnip);

        // Menambahkan label dan date chooser untuk Tanggal Lahir
        JLabel lbldob = new JLabel("Tanggal Lahir");
        lbldob.setBounds(400, 200, 200, 30);
        lbldob.setFont(new Font("serif", Font.BOLD, 20));
        add(lbldob);
        dcdob = new JDateChooser();
        dcdob.setBounds(600, 200, 150, 30);
        add(dcdob);

        // Menambahkan label dan field untuk Alamat
        JLabel lbladdress = new JLabel("Alamat");
        lbladdress.setBounds(50, 250, 200, 30);
        lbladdress.setFont(new Font("serif", Font.BOLD, 20));
        add(lbladdress);
        tfalamat = new JTextField();
        tfalamat.setBounds(200, 250, 150, 30);
        add(tfalamat);

        // Menambahkan label dan field untuk No Hp
        JLabel lblphone = new JLabel("No Hp");
        lblphone.setBounds(400, 250, 200, 30);
        lblphone.setFont(new Font("serif", Font.BOLD, 20));
        add(lblphone);
        tfnohp = new JTextField();
        tfnohp.setBounds(600, 250, 150, 30);
        add(tfnohp);

        // Menambahkan label dan field untuk Email
        JLabel lblemail = new JLabel("Email");
        lblemail.setBounds(50, 300, 200, 30);
        lblemail.setFont(new Font("serif", Font.BOLD, 20));
        add(lblemail);
        tfemail = new JTextField();
        tfemail.setBounds(200, 300, 150, 30);
        add(tfemail);

        // Menambahkan label dan combo box untuk Status
        JLabel lblstatus = new JLabel("Status");
        lblstatus.setBounds(400, 300, 200, 30);
        lblstatus.setFont(new Font("serif", Font.BOLD, 20));
        add(lblstatus);
        String status[] = {"Junior Lecturer", "Lecturer", "Senior Lecturer", "Head of Department", "Dean", "Rector"};
        cbstatus = new JComboBox(status);
        cbstatus.setBounds(600, 300, 150, 30);
        cbstatus.setFont(new Font("serif", Font.BOLD, 15));
        cbstatus.setBackground(Color.WHITE);
        add(cbstatus);

        // Menambahkan label dan combo box untuk Kategori
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

        // Menambahkan label dan field untuk Tahun Masuk
        JLabel lblenYear = new JLabel("Tahun Masuk");
        lblenYear.setBounds(400, 350, 200, 30);
        lblenYear.setFont(new Font("serif", Font.BOLD, 20));
        add(lblenYear);
        tahunMasuk = new JTextField();
        tahunMasuk.setBounds(600, 350, 150, 30);
        add(tahunMasuk);

        // Menambahkan label dan combo box untuk Fakultas
        JLabel lblFakultas = new JLabel("Fakultas");
        lblFakultas.setBounds(50, 400, 200, 30);
        lblFakultas.setFont(new Font("serif", Font.BOLD, 20));
        add(lblFakultas);
        cbfakultas = new JComboBox();
        selectFakultasComboBox();
        cbfakultas.setBounds(200, 400, 150, 30);
        cbfakultas.setBackground(Color.WHITE);

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
        lblProd.setBounds(400, 400, 200, 30);
        lblProd.setFont(new Font("serif", Font.BOLD, 20));
        add(lblProd);
        cbprodi = new JComboBox();
        selectProdiComboBox(cbfakultas.getSelectedItem().toString());
        cbprodi.setBounds(600, 400, 150, 30);
        cbprodi.setBackground(Color.WHITE);
        add(cbprodi);

        // Menambahkan tombol Submit
        submit = new JButton("Submit");
        submit.setBounds(250, 500, 120, 30);
        submit.setBackground(new Color(0, 128, 0));
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(submit);

        // Menambahkan tombol Cancel
        cancel = new JButton("Cancel");
        cancel.setBounds(450, 500, 120, 30);
        cancel.setBackground(new Color(128, 0, 0));
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

        // Menampilkan frame
        setVisible(true);
    }

    // Metode selectProdiComboBox untuk mengatur model untuk cbprodi
    // Mengambil parameter dari fakultas yang dipilih untuk mendapatkan prodi yang sesuai
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

    // Metode selectFakultasComboBox untuk mengatur model untuk cbfakultas
    private void selectFakultasComboBox() {
        try {
            // Query untuk mengambil nilai "Prodi" dari database
            String query = "SELECT DISTINCT nama_fakultas FROM fakultas";

            Conn con = new Conn();
            java.sql.PreparedStatement pst = con.c.prepareStatement(query);

            // Menjalankan query
            ResultSet rs = pst.executeQuery();

            // Mengisi JComboBox "Prodi" dengan nilai yang diambil
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            while (rs.next()) {
                model.addElement(rs.getString("nama_fakultas"));
            }

            cbfakultas.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Metode actionPerformed untuk menangani aksi tombol
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            // Mengambil nilai dari field input
            String nama = tfnama.getText();
            String gender = (String) genderComboBox.getSelectedItem();
            String nip = lbnip.getText();
            Date selectedDate = dcdob.getDate();
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
                // Query SQL untuk menyimpan data mahasiswa ke database
                String query = "insert into dosen values('" + nip + "', '" + nama + "', '" + gender + "', '" + tgl_lahir + "', '" + alamat + "', '" + no_hp + "', '" + email + "', '" + prodi + "', '" + fakultas + "', '" + status + "', '" + kategori + "', '" + tahun_masuk + "')";

                Conn con = new Conn();
                con.s.executeUpdate(query);

                // Menampilkan pesan sukses dan menutup frame
                JOptionPane.showMessageDialog(null, "Lecturer Details Inserted Successfully");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // Menutup frame jika tombol Cancel ditekan
            setVisible(false);
        }
    }

    // Metode main untuk menjalankan program
    public static void main(String[] args) {
        new AddLecturer();
    }
}