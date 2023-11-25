package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;

public class AddStudent extends JFrame implements ActionListener{
    
    JTextField tfnama, tfemail, tfalamat, tfnohp, tfgender, tftgllahir, tffakultas, tahunMasuk;

    JLabel lbnim;
    JDateChooser dcdob;
    JComboBox cbcourse, cbprodi, cbstatus, cbcategory;
    JComboBox<String> genderComboBox;
    JButton submit, cancel;
    
    Random ran = new Random();
    long first4 = Math.abs((ran.nextLong() % 9000L) + 1000L);
    
    AddStudent() {
        
        setSize(900, 700);
        setLocation(350, 50);
        
        setLayout(null);
        
        JLabel heading = new JLabel("Add New Student ");
        heading.setBounds(310, 30, 500, 50);
        heading.setFont(new Font("serif", Font.BOLD, 30));
        add(heading);
        
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
        genderComboBox = new JComboBox<>(genders);
        genderComboBox.setBounds(600, 150, 150, 30);
        add(genderComboBox);
        
        JLabel lblnim = new JLabel("NIM");
        lblnim.setBounds(50, 200, 200, 30);
        lblnim.setFont(new Font("serif", Font.BOLD, 20));
        add(lblnim);
        
        lbnim = new JLabel("20"+first4);
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

        String status[] = {"New Student", "Exchanged Student", "Reback Student"};
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
        
        submit = new JButton("Submit");
        submit.setBounds(250, 550, 120, 30);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(submit);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(450, 550, 120, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
        String nama = tfnama.getText();
        String gender = (String) genderComboBox.getSelectedItem();
        String nim = lbnim.getText();
        Date selectedDate = dcdob.getDate();
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
                    // Query SQL
                    String query = "insert into student values('"+nama+"', '"+gender+"', '"+nim+"', '"+tgl_lahir+"', '"+alamat+"', '"+no_hp+"', '"+email+"', '"+prodi+"', '"+fakultas+"', '"+status+"', '"+kategori+"', '"+tahun_masuk+"')";

                    Conn con = new Conn();
                    con.s.executeUpdate(query);

                    JOptionPane.showMessageDialog(null, "Student Details Inserted Successfully");
                    setVisible(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        } else {
            setVisible(false);
        }
    }
    
    public static void main(String[] args) {
        new AddStudent();
    }
}
