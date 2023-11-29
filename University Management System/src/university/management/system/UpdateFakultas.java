package university.management.system;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class UpdateFakultas extends JFrame implements ActionListener {

    JTextField tfnama, tfalamat, tfemail;
    JLabel labelFakId;
    JDateChooser dcdob;
    JButton submit, cancel;

    Choice cFakId;

    UpdateFakultas() {

        setSize(900, 500);
        setLocationRelativeTo(null);

        setLayout(null);

        getContentPane().setBackground(new Color(173, 216, 230)); // Set background color

        JLabel heading = new JLabel("Update Faculty Details");
        heading.setBounds(50, 10, 500, 50);
        heading.setFont(new Font("Tahoma", Font.ITALIC, 35));
        add(heading);

        JLabel lblidFak = new JLabel("Pilih Id Fakultas");
        lblidFak.setBounds(50, 100, 200, 20);
        lblidFak.setFont(new Font("serif", Font.PLAIN, 20));
        add(lblidFak);

        cFakId = new Choice();
        cFakId.setBounds(250, 100, 200, 20);
        add(cFakId);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from fakultas");
            while (rs.next()) {
                cFakId.add(rs.getString("id_fakultas"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel lblname = new JLabel("Nama Fakultas");
        lblname.setBounds(50, 150, 130, 30);
        lblname.setFont(new Font("serif", Font.BOLD, 20));
        add(lblname);

        tfnama = new JTextField();
        tfnama.setBounds(200, 150, 150, 30);
        add(tfnama);

        JLabel lblFakId = new JLabel("Id Fakultas");
        lblFakId.setBounds(50, 200, 200, 30);
        lblFakId.setFont(new Font("serif", Font.BOLD, 20));
        add(lblFakId);

        labelFakId = new JLabel();
        labelFakId.setBounds(200, 200, 200, 30);
        labelFakId.setFont(new Font("serif", Font.BOLD, 20));
        add(labelFakId);

        JLabel lblfemail = new JLabel("Email Fakultas");
        lblfemail.setBounds(400, 150, 200, 30);
        lblfemail.setFont(new Font("serif", Font.BOLD, 20));
        add(lblfemail);

        tfemail = new JTextField();
        tfemail.setBounds(600, 150, 150, 30);
        add(tfemail);

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

        cFakId.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                try {
                    Conn c = new Conn();
                    String query = "select * from fakultas where id_fakultas='" + cFakId.getSelectedItem() + "'";
                    ResultSet rs = c.s.executeQuery(query);
                    while (rs.next()) {
                        tfnama.setText(rs.getString("nama_fakultas"));
                        labelFakId.setText(rs.getString("id_fakultas"));
                        Date dateFromResultSet = rs.getDate("tanggal_berdiri");
                        dcdob.setDate(dateFromResultSet);
                        tfalamat.setText(rs.getString("alamat"));
                        tfemail.setText(rs.getString("email"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        submit = new JButton("Update");
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
            String id_fakultas = labelFakId.getText();
            String alamat = tfalamat.getText();
            String nama_fakultas = tfnama.getText();
            String email = tfemail.getText();
            java.sql.Date selectedDate = new java.sql.Date(dcdob.getDate().getTime());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String tanggal_berdiri = dateFormat.format(selectedDate);

            try {
                String query = "update fakultas set alamat=?, nama_fakultas=?, email=?, "
                        + "tanggal_berdiri=? "
                        + "where id_fakultas=?";
                Conn con = new Conn();
                Connection connection = con.c;
                PreparedStatement pstmt = connection.prepareStatement(query);
                pstmt.setString(1, alamat);
                pstmt.setString(2, nama_fakultas);
                pstmt.setString(3, email);
                pstmt.setString(4, tanggal_berdiri);
                pstmt.setString(5, id_fakultas);

                pstmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "Faculty Details Updated Successfully");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }


    public static void main(String[] args) {
        new UpdateFakultas();
    }
}
