package net.stella;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class UpdateJobForm extends JFrame {
    private JobDAO jobDAO;
    private StellaUI mainUI;

    public UpdateJobForm(JobDAO jobDAO, StellaUI mainUI) {
        this.jobDAO = jobDAO;
        this.mainUI = mainUI;

        setTitle("Update Job");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(8, 2));

        JTextField jobIdField = new JTextField();
        JTextField clientNameField = new JTextField();
        JTextField jobDescriptionField = new JTextField();
        JTextField dueDateField = new JTextField(); // Format: YYYY-MM-DD
        JTextField locationField = new JTextField();
        JTextField contactInfoField = new JTextField();
        JComboBox<JobStatus> statusComboBox = new JComboBox<>(JobStatus.values());
        JTextField estimatorField = new JTextField();

        add(new JLabel("Job ID:"));
        add(jobIdField);
        add(new JLabel("Client Name:"));
        add(clientNameField);
        add(new JLabel("Job Description:"));
        add(jobDescriptionField);
        add(new JLabel("Due Date (YYYY-MM-DD):"));
        add(dueDateField);
        add(new JLabel("Location:"));
        add(locationField);
        add(new JLabel("Contact Info:"));
        add(contactInfoField);
        add(new JLabel("Status:"));
        add(statusComboBox);
        add(new JLabel("Estimator:"));
        add(estimatorField);

        JButton updateButton = new JButton("Update Job");
        add(new JLabel()); // Empty cell
        add(updateButton);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String jobId = jobIdField.getText();
                Job existingJob = jobDAO.getJob(jobId);
                if (existingJob != null) {
                    existingJob.setClientName(clientNameField.getText());
                    existingJob.setJobDescription(jobDescriptionField.getText());
                    existingJob.setDueDate(LocalDate.parse(dueDateField.getText()));
                    existingJob.setLocation(locationField.getText());
                    existingJob.setContactInfo(contactInfoField.getText());
                    existingJob.setStatus((JobStatus) statusComboBox.getSelectedItem());
                    existingJob.setEstimator(estimatorField.getText());

                    jobDAO.updateJob(existingJob);
                    mainUI.updateJobTable();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Job not found!");
                }
            }
        });
    }
}
