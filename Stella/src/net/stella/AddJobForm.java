package net.stella;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddJobForm  extends JFrame {
	private JobDAO jobDAO;
	private StellaUI mainUI;
	
	public AddJobForm(JobDAO jobDAO, StellaUI mainUI) {
		this.jobDAO = jobDAO;
		this.mainUI = mainUI;
		
		setTitle("Add Job");
		setSize(300,400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new GridLayout(8,2));
		
		JTextField jobIdField = new JTextField();
		JTextField clientNameField = new JTextField();
		JTextField jobDescriptionField = new JTextField();
		JTextField dueDateField = new JTextField();
		JTextField locationField = new JTextField();
		JTextField contactInfoField = new JTextField();
		JComboBox<JobStatus> statusComboBox = new JComboBox<>(JobStatus.values());
		JTextField estimatorField = new JTextField();
		
		add(new JLabel("Job ID:"));
		add(jobIdField);
		add(new JLabel("Client Name:"));
		add(clientNameField);
		add(new JLabel("Description of Job:"));
		add(jobDescriptionField);
		add(new JLabel("Due Date (YYYY-MM-DD)"));
		add(dueDateField);
		add(new JLabel("Location"));
		add(locationField);
		add(new JLabel("Contact Info:"));
		add(contactInfoField);
		add(new JLabel("Status:"));
		add(statusComboBox);
		add(new JLabel("Estimator:"));
		add(estimatorField);
		
		JButton addButton = new JButton("Add Job");
		add(new JLabel()); //Empty Cell
		add(addButton);
		
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String jobId = jobIdField.getText();
				String clientName = clientNameField.getText();
				String jobDescription = jobDescriptionField.getText();
				LocalDate dueDate = LocalDate.parse(dueDateField.getText());
				String location = locationField.getText();
				String contactInfo = contactInfoField.getText();
				JobStatus status = (JobStatus) statusComboBox.getSelectedItem();
				String estimator = estimatorField.getText();
				
				Job job = new Job(jobId, clientName, jobDescription, dueDate, location, contactInfo, status, estimator);
				jobDAO.addJob(job);
				mainUI.updateJobTable();
				dispose();
			}

		});
	}

}
