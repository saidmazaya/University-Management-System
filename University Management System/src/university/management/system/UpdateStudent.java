package university.management.system;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateStudent extends JFrame implements ActionListener{

    JTextField tfnama, tfemail, tfalamat, tfnohp, tfgender, tfcourse, tfbranch, tahunMasuk;
    JLabel labelrollno;
    JDateChooser tfdob;
    JButton submit, cancel;
    Choice crollno;
    
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
        
        crollno = new Choice();
        crollno.setBounds(250, 100, 200, 20);
        add(crollno);
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from student");
            while(rs.next()) {
                crollno.add(rs.getString("rollno"));
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

        tfgender = new JTextField();
        tfgender.setBounds(200, 250, 150, 30);
        add(tfgender);

        JLabel lbldob = new JLabel("Tanggal Lahir");
        lbldob.setBounds(400, 200, 200, 30);
        lbldob.setFont(new Font("serif", Font.BOLD, 20));
        add(lbldob);

        tfdob = new JDateChooser();
        tfdob.setBounds(600, 200, 150, 30);
        add(tfdob);

        JLabel lbladdress = new JLabel("Alamat");
        lbladdress.setBounds(50, 250, 200, 30);
        lbladdress.setFont(new Font("serif", Font.BOLD, 20));
        add(lbladdress);
        
        tfalamat = new JTextField();
        tfalamat.setBounds(200, 250, 150, 30);
        add(tfalamat);
        
        JLabel lblphone = new JLabel("Phone");
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

        tfstatus = new JTextField();
        tfstatus.setBounds(600, 400, 150, 30);
        add(tfstatus);

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

        tfcourse = new JTextField();
        tfcourse.setBounds(600, 400, 150, 30);
        add(tfcourse);

        JLabel lblbranch = new JLabel("Prodi");
        lblbranch.setBounds(400, 400, 200, 30);
        lblbranch.setFont(new Font("serif", Font.BOLD, 20));
        add(lblbranch);

        tfbranch = new JTextField();
        tfbranch.setBounds(600, 400, 150, 30);
        add(tfbranch);
        
        try {
            Conn c = new Conn();
            Date selectedDate = (Date) tfdob.getDate();
            String query = "select * from student where rollno='"+crollno.getSelectedItem()+"'";
            ResultSet rs = c.s.executeQuery(query);
            while(rs.next()) {
                labelname.setText(rs.getString("nama"));
                Date dateFromResultSet = rs.getDate("tgl_lahir");
                tfdob.setDate(dateFromResultSet);
                tfgender.setText(rs.getString("gender"));
                tfalamat.setText(rs.getString("alamat"));
                tfnohp.setText(rs.getString("no_hp"));
                tfemail.setText(rs.getString("email"));
                tahunMasuk.setText(rs.getString("tahun_masuk"));
                tfcourse.setText(rs.getString("fakultas"));
                tfbranch.setText(rs.getString("prodi"));
                tfstatus.setText(rs.getString("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        crollno.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                try {
                    Conn c = new Conn();
                    String query = "select * from student where rollno='"+crollno.getSelectedItem()+"'";
                    ResultSet rs = c.s.executeQuery(query);
                    while(rs.next()) {
                        labelname.setText(rs.getString("nama"));
                        Date dateFromResultSet = rs.getDate("tgl_lahir");
                        tfdob.setDate(dateFromResultSet);;
                        tfgender.setText(rs.getString("gender"));
                        tfalamat.setText(rs.getString("address"));
                        tfemail.setText(rs.getString("email"));
                        tahunMasuk.setText(rs.getString("tahun_masuk"));
                        tfcourse.setText(rs.getString("fakultas"));
                        tfbranch.setText(rs.getString("prodi"));
                        tfstatus.setText(rs.getString("status"));

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
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
            String rollno = labelrollno.getText();
            String address = tfalamat.getText();
            String phone = tfnohp.getText();
            String email = tfemail.getText();
            String course = tfcourse.getText();
            String branch = tfbranch.getText();
            
            try {
                String query = "update student set alamat='"+address+"', no_hp='"+phone+"', email='"+email+"', fakultas='"+course+"', prodi='"+branch+"' where idprodi='"+rollno+" ";
                Conn con = new Conn();
                con.s.executeUpdate(query);
                
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
