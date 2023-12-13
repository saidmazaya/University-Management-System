package university.management.system;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;

class PreviewDialog extends JDialog {
    public PreviewDialog(Frame parent, TableModel model) {
        super(parent, "Print Preview", true);
        setLayout(new BorderLayout());

        JTable previewTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(previewTable);
        add(scrollPane, BorderLayout.CENTER);

        JButton printButton = new JButton("Print");
        printButton.addActionListener(e -> {
            try {
                previewTable.print();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(printButton);
        buttonPanel.add(closeButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setSize(1000, 400);
        setLocationRelativeTo(parent);
        setVisible(true);
    }
}
