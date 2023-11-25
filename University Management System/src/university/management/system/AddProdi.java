package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;

public class AddProdi extends JFrame implements ActionListener{

    JTextField tfnama, tffakultas, tfalamat, tfmahasiswa, tfemail, tfdosen, tfstaff;
    JLabel labelprodId;
    JDateChooser dcdob;
    JComboBox cbjenjang;
    JButton submit, cancel;

    Random ran = new Random();
    long first4 = Math.abs((ran.nextLong() % 9000L) + 1000L);

    AddProdi() {

        setSize(900, 700);
        setLocation(350, 50);

        setLayout(null);

        getContentPane().setBackground(new Color(173, 216, 230)); // Set background color


        JLabel heading = new JLabel("Tambah Program Studi");
        heading.setBounds(310, 30, 500, 50);
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

        tffakultas = new JTextField();
        tffakultas.setBounds(600, 150, 150, 30);
        add(tffakultas);

        JLabel lblprodId = new JLabel("Id Prodi");
        lblprodId.setBounds(50, 200, 200, 30);
        lblprodId.setFont(new Font("serif", Font.BOLD, 20));
        add(lblprodId);

        labelprodId = new JLabel("101"+first4);
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

        JLabel lblx = new JLabel("Jumlah Dosen");
        lblx.setBounds(400, 300, 200, 30);
        lblx.setFont(new Font("serif", Font.BOLD, 20));
        add(lblx);

        tfdosen = new JTextField();
        tfdosen.setBounds(600, 300, 150, 30);
        add(tfdosen);

        JLabel lblxii = new JLabel("Jumlah Staff");
        lblxii.setBounds(50, 350, 200, 30);
        lblxii.setFont(new Font("serif", Font.BOLD, 20));
        add(lblxii);

        tfstaff = new JTextField();
        tfstaff.setBounds(200, 350, 150, 30);
        add(tfstaff);

        JLabel lblcourse = new JLabel("Jenjang");
        lblcourse.setBounds(400, 350, 200, 30);
        lblcourse.setFont(new Font("serif", Font.BOLD, 20));
        add(lblcourse);

        String course[] = {"D-3", "D-4", "S-1", "S-2", "S-3"};
        cbjenjang = new JComboBox(course);
        cbjenjang.setBounds(600, 350, 150, 30);
        cbjenjang.setBackground(Color.WHITE);
        add(cbjenjang);


        submit = new JButton("Submit");
        submit.setBounds(250, 550, 120, 30);
        submit.setBackground(new Color(0, 128, 0)); // Set button background color
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(450, 550, 120, 30);
        cancel.setBackground(new Color(128, 0, 0)); // Set button background color
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String nama_prodi = tfnama.getText();
            String nama_fakultas = tffakultas.getText();
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
                String query = "insert into prodi values('"+nama_prodi+"', '"+nama_fakultas+"', '"+email+"', '"+id_prodi+"', '"+tanggal_berdiri+"', '"+alamat+"', '"+mahasiswa_aktif+"', '"+jumlah_dosen+"', '"+jumlah_staff+"','"+jenjang+"')";

                Conn con = new Conn();
                con.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Prodi Baru Berhasil Di tambahkan");
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