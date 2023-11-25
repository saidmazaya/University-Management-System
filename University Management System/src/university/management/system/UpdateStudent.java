package university.management.system;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class UpdateStudent extends JFrame implements ActionListener {

    JTextField tfnama, tfemail, tfalamat, tfnohp, tfgender, tftgllahir, tffakultas, tahunMasuk;
    JLabel lbnim;
    JDateChooser dcdob;
    JComboBox cbcourse, cbprodi, cbstatus, cbcategory, cbgender;
    JButton submit, cancel;
    Choice nim;

    UpdateStudent() {

        setSize(900, 650);
        setLocation(350, 50);

        setLayout(null);

        JLabel heading = new JLabel("Update Student Details");
        heading.setBounds(50, 10, 500, 50);
        heading.setFont(new Font("Tahoma", Font.ITALIC, 35));
        add(heading);

        JLabel lblrollnumber = new JLabel("Select Roll Number");
        lblrollnumber.setBounds(50, 100, 200, 20);
        lblrollnumber.setFont(new Font("serif", Font.PLAIN, 20));
        add(lblrollnumber);

        nim = new Choice();
        nim.setBounds(250, 100, 200, 20);
        add(nim);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from student");
            while (rs.next()) {
                nim.add(rs.getString("nim"));
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
        add(cbgender);

        JLabel lblnim = new JLabel("NIM");
        lblnim.setBounds(50, 200, 200, 30);
        lblnim.setFont(new Font("serif", Font.BOLD, 20));
        add(lblnim);

        lbnim = new JLabel();  // Remove the initial text
        lbnim.setBounds(200, 200, 200, 30);
        lbnim.setFont(new Font("serif", Font.BOLD, 20));
        add(lbnim);

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

        JLabel lblx = new JLabel("Status");
        lblx.setBounds(400, 300, 200, 30);
        lblx.setFont(new Font("serif", Font.BOLD, 20));
        add(lblx);

        String status[] = {"New Student", "Exchanged Student", "Reback Student", "Drop Out", "Graduated"};
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

        JLabel lblaadhar = new JLabel("Tahun Masuk");
        lblaadhar.setBounds(400, 350, 200, 30);
        lblaadhar.setFont(new Font("serif", Font.BOLD, 20));
        add(lblaadhar);

        tahunMasuk = new JTextField();
        tahunMasuk.setBounds(600, 350, 150, 30);
        add(tahunMasuk);

        JLabel lblcourse = new JLabel("Fakultas");
        lblcourse.setBounds(50, 400, 200, 30);
        lblcourse.setFont(new Font("serif", Font.BOLD, 20));
        add(lblcourse);

        String course[] = {"FK", "FH", "FASILKOM-TI", "FHUT", "FISIP"};
        cbcourse = new JComboBox(course);
        cbcourse.setBounds(200, 400, 150, 30);
        cbcourse.setBackground(Color.WHITE);
        add(cbcourse);

        JLabel lblbranch = new JLabel("Prodi");
        lblbranch.setBounds(400, 400, 200, 30);
        lblbranch.setFont(new Font("serif", Font.BOLD, 20));
        add(lblbranch);

        String tprodi[] = {"Computer Science", "Electronics", "Mechanical", "Civil", "IT"};
        cbprodi = new JComboBox(tprodi);
        cbprodi.setBounds(600, 400, 150, 30);
        cbprodi.setBackground(Color.WHITE);
        add(cbprodi);

        try {
            Conn con = new Conn();
            Connection connection = con.c;
            nim.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent ie) {
                    try {
                        String selectedNim = nim.getSelectedItem();
                        String query = "select * from student where nim=?";
                        PreparedStatement pstmt = connection.prepareStatement(query);
                        pstmt.setString(1, selectedNim);
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
                            cbcourse.setSelectedItem(rs.getString("fakultas"));
                            cbprodi.setSelectedItem(rs.getString("prodi"));
                            cbstatus.setSelectedItem(rs.getString("status"));
                            cbcategory.setSelectedItem(rs.getString("kategori"));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (ie.getSource() == nim) {
                        try {
                            String selectedNIM = nim.getSelectedItem();

                            // Assuming you have a query to get the corresponding NIM from the database
                            Conn c = new Conn();
                            ResultSet rs = c.s.executeQuery("select * from student where nim='" + selectedNIM + "'");

                            if (rs.next()) {
                                String nimValue = rs.getString("nim");
                                lbnim.setText(nimValue);
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
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(450, 500, 120, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String nimValue = nim.getSelectedItem(); // Assuming nim is your NIM Choice
            String nama = tfnama.getText();
            String gender = (String) cbgender.getSelectedItem();
            java.sql.Date selectedDate = new java.sql.Date(dcdob.getDate().getTime());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String tgl_lahir = dateFormat.format(selectedDate);
            String alamat = tfalamat.getText();
            String no_hp = tfnohp.getText();
            String email = tfemail.getText();
            String prodi = cbprodi.getSelectedItem().toString();
            String fakultas = cbcourse.getSelectedItem().toString();
            String status = cbstatus.getSelectedItem().toString();
            String kategori = cbcategory.getSelectedItem().toString();
            String tahun_masuk = tahunMasuk.getText();

            try {
                String query = "UPDATE student SET nama=?, gender=?, tgl_lahir=?, alamat=?, no_hp=?, email=?, prodi=?, fakultas=?, status=?, kategori=?, tahun_masuk=? WHERE nim=?";
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
                pstmt.setString(12, nimValue);

                pstmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "Student Details Updated Successfully");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new UpdateStudent();
    }
}
