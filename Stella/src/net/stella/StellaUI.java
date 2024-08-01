package net.stella;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class StellaUI extends JFrame {
	private JobDAO jobDAO;
	private JTable jobTable;
	private DefaultTableModel tableModel;
	private Estimator estimator;

	public StellaUI(Estimator estimator) {
		jobDAO = new JobDAO();
		this.estimator = estimator;

		setTitle("Stella - " + estimator.getUsername());
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());

		JButton addButton = new JButton("Add Job");
		JButton updateButton = new JButton("Update Job");
		JButton deleteButton = new JButton("Delete Job");

		buttonPanel.add(addButton);
		buttonPanel.add(updateButton);
		buttonPanel.add(deleteButton);

		add(buttonPanel, BorderLayout.SOUTH);

		String[] columnNames = { "Job Id", "Client Name", "Job Description", "Due Date", "Location", "Contact Info",
				"Status", "Estimator" };
		tableModel = new DefaultTableModel(columnNames, 0);
		jobTable = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(jobTable);

		add(scrollPane, BorderLayout.CENTER);

		addButton.addActionListener(e -> {
			new AddJobForm(jobDAO, this).setVisible(true);
		});

		updateButton.addActionListener(e -> {
			new UpdateJobForm(jobDAO, this).setVisible(true);
		});

		deleteButton.addActionListener(e -> {
			new DeleteJobForm(jobDAO, this).setVisible(true);
		});

		updateJobTable();
	}

	public void updateJobTable() {
		List<Job> jobs = jobDAO.getJobsByEstimator(estimator.getUsername());
		tableModel.setRowCount(0);
		for (Job job : jobs) {
			Object[] rowData = { job.getJobId(), job.getClientName(), job.getJobDescription(), job.getDueDate(),
					job.getLocation(), job.getContactInfo(), job.getStatus().name(), job.getEstimator() };
			tableModel.addRow(rowData);
		}
	}
}
