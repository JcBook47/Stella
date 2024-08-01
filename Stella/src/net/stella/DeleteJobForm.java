package net.stella;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteJobForm extends JFrame {
    private JobDAO jobDAO;
    private StellaUI mainUI;

    public DeleteJobForm(JobDAO jobDAO, StellaUI mainUI) {
        this.jobDAO = jobDAO;
        this.mainUI = mainUI;

        setTitle("Delete Job");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(2, 2));

        JTextField jobIdField = new JTextField();

        add(new JLabel("Job ID:"));
        add(jobIdField);

        JButton deleteButton = new JButton("Delete Job");
        add(new JLabel()); // Empty cell
        add(deleteButton);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String jobId = jobIdField.getText();
                jobDAO.deleteJob(jobId);
                mainUI.updateJobTable();
                dispose();
            }
        });
    }
}
