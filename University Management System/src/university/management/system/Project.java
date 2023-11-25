package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Project extends JFrame implements ActionListener {

    Project() {
        setSize(1000, 550);
        setLocationRelativeTo(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/1.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1000, 550, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image);

        JMenuBar mb = new JMenuBar();

        // New Information
        JMenu newInformation = new JMenu("Information");
        newInformation.setForeground(Color.BLUE);
        mb.add(newInformation);

        JMenuItem facultyInfo = new JMenuItem("Add New Faculty");
        facultyInfo.setBackground(Color.WHITE);
        facultyInfo.addActionListener(this);
        newInformation.add(facultyInfo);

        JMenuItem prodiInfo = new JMenuItem("Add New Prodi");
        prodiInfo.setBackground(Color.WHITE);
        prodiInfo.addActionListener(this);
        newInformation.add(prodiInfo);

        JMenuItem studentInfo = new JMenuItem("Add New Student");
        studentInfo.setBackground(Color.WHITE);
        studentInfo.addActionListener(this);
        newInformation.add(studentInfo);

        // Details
        JMenu details = new JMenu("View Details");
        details.setForeground(Color.RED);
        mb.add(details);

        JMenuItem facultydetails = new JMenuItem("View Faculty Details");
        facultydetails.setBackground(Color.WHITE);
        facultydetails.addActionListener(this);
        details.add(facultydetails);

        JMenuItem prodidetails = new JMenuItem("View Prodi Details");
        prodidetails.setBackground(Color.WHITE);
        prodidetails.addActionListener(this);
        details.add(prodidetails);

        JMenuItem studentdetails = new JMenuItem("View Student Details");
        studentdetails.setBackground(Color.WHITE);
        studentdetails.addActionListener(this);
        details.add(studentdetails);

        // UpdateInfo
        JMenu updateInfo = new JMenu("Update Details");
        updateInfo.setForeground(Color.RED);
        mb.add(updateInfo);

        JMenuItem updatefacultyinfo = new JMenuItem("Update Faculty Details");
        updatefacultyinfo.setBackground(Color.WHITE);
        updatefacultyinfo.addActionListener(this);
        updateInfo.add(updatefacultyinfo);

        JMenuItem updateprodiinfo = new JMenuItem("Update Prodi Details");
        updateprodiinfo.setBackground(Color.WHITE);
        updateprodiinfo.addActionListener(this);
        updateInfo.add(updateprodiinfo);

        JMenuItem updatestudentinfo = new JMenuItem("Update Student Details");
        updatestudentinfo.setBackground(Color.WHITE);
        updatestudentinfo.addActionListener(this);
        updateInfo.add(updatestudentinfo);

        // UpdateInfo
        JMenu aboutInfo = new JMenu("About");
        aboutInfo.setForeground(Color.BLUE);
        mb.add(aboutInfo);

        JMenuItem about = new JMenuItem("About");
        about.setBackground(Color.WHITE);
        about.addActionListener(this);
        aboutInfo.add(about);

        // exit
        JMenu exit = new JMenu("Exit");
        exit.setForeground(Color.RED);
        mb.add(exit);

        JMenuItem ex = new JMenuItem("Exit");
        ex.setBackground(Color.WHITE);
        ex.addActionListener(this);
        exit.add(ex);

        setJMenuBar(mb);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String msg = ae.getActionCommand();

        if (msg.equals("Exit")) {
            setVisible(false);
        } else if (msg.equals("Add New Faculty")) {
            new AddFakultas();
        } else if (msg.equals("Add New Prodi")) {
            new AddProdi();
        } else if (msg.equals("Add New Student")) {
            new AddStudent();
        } else if (msg.equals("View Faculty Details")) {
            new FakultasDetails();
        } else if (msg.equals("View Prodi Details")) {
            new ProdiDetails();
        } else if (msg.equals("View Student Details")) {
            new StudentDetails();
        } else if (msg.equals("Update Faculty Details")) {
            new UpdateFakultas();
        } else if (msg.equals("Update Prodi Details")) {
            new UpdateProdi();
        } else if (msg.equals("Update Student Details")) {
            new UpdateStudent();
        } else if (msg.equals("About")) {
            new About();
        }
    }

    public static void main(String[] args) {
        new Project();
    }
}
