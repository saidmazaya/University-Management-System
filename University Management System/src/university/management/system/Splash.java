package university.management.system;

import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame implements Runnable {

    private JProgressBar progressBar;
    private Thread t;

    Splash() {
        setLayout(new BorderLayout());

        // Create a panel to hold the image and progress bar
        JPanel panel = new JPanel(new BorderLayout());

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/usu.png"));
        Image i2 = i1.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        panel.add(image, BorderLayout.CENTER);

        // Create a progress bar
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        panel.add(progressBar, BorderLayout.SOUTH);

        add(panel, BorderLayout.CENTER);

        t = new Thread(this);
        t.start();

        setVisible(true);

        int x = 1;
        setLocation(250, 50);
        setSize(800, 600);
    }

    public void run() {
        try {
            for (int i = 0; i <= 100; i++) {
                // Update the progress bar value
                progressBar.setValue(i);

                // Sleep for a short time to simulate loading
                Thread.sleep(60);
            }

            // Hide the splash screen
            setVisible(false);

            // Next Frame
            new Login();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Splash();
    }
}