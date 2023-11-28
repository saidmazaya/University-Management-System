package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;

import com.toedter.calendar.JDateChooser;

import java.awt.event.*;

public class AddFakultas extends JFrame implements ActionListener {

    JTextField tfnama, tfalamat, tfemail;
    JLabel labelfakId;
    JDateChooser dcdob;
    JButton submit, cancel;

    Random ran = new Random();
    long first4 = Math.abs((ran.nextLong() % 9000L) + 1000L);

    AddFakultas() {

        setSize(900, 500);
        setLocationRelativeTo(null);

        setLayout(null);

        getContentPane().setBackground(new Color(173, 216, 230)); // Set background color


        JLabel heading = new JLabel("Tambah Fakultas");
        heading.setBounds(310, 30, 500, 50);
        heading.setFont(new Font("serif", Font.BOLD, 30));
        add(heading);

        JLabel lblname = new JLabel("Nama Fakultas");
        lblname.setBounds(50, 150, 200, 30);
        lblname.setFont(new Font("serif", Font.BOLD, 20));
        add(lblname);

        tfnama = new JTextField();
        tfnama.setBounds(200, 150, 150, 30);
        add(tfnama);

        JLabel lblfname = new JLabel("Email Fakultas");
        lblfname.setBounds(400, 150, 200, 30);
        lblfname.setFont(new Font("serif", Font.BOLD, 20));
        add(lblfname);

        tfemail = new JTextField();
        tfemail.setBounds(600, 150, 150, 30);
        add(tfemail);

        JLabel lblprodId = new JLabel("Id Fakultas");
        lblprodId.setBounds(50, 200, 200, 30);
        lblprodId.setFont(new Font("serif", Font.BOLD, 20));
        add(lblprodId);

        labelfakId = new JLabel("101" + first4);
        labelfakId.setBounds(200, 200, 200, 30);
        labelfakId.setFont(new Font("serif", Font.BOLD, 20));
        add(labelfakId);

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

        submit = new JButton("Submit");
        submit.setBounds(300, 400, 120, 30);
        submit.setBackground(new Color(0, 128, 0)); // Set button background color
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(500, 400, 120, 30);
        cancel.setBackground(new Color(128, 0, 0)); // Set button background color
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String nama_fakultas = tfnama.getText();
            String email = tfemail.getText();
            String id_fakultas = labelfakId.getText();
            String alamat = tfalamat.getText();
            Date selectedDate = dcdob.getDate();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String tanggal_berdiri = dateFormat.format(selectedDate);

            try {
                String query = "insert into fakultas values('" + id_fakultas + "','" + nama_fakultas + "','" + email + "','" + alamat + "','" + tanggal_berdiri + "')";

                Conn con = new Conn();
                con.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Fakultas Baru Berhasil Di tambahkan");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new AddFakultas();
    }
}