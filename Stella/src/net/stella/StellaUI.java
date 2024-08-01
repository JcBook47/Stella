package net.stella;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class StellaUI  extends JFrame {
	private JobDAO jobDAO;
	
	public StellaUI() {
		jobDAO = new JobDAO();
		
		setTitle("Stella");
		setSize(400,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		
		JButton addButton = new JButton("Add Job");
		JButton updateButton = new JButton("Update Job");
		JButton deleteButton = new JButton("Delete Job");
		
		add(addButton);
		add(updateButton);
		add(deleteButton);
		
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AddJobForm(jobDAO).setVisible(true);
			}
		});
		
		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new UpdateJobForm(jobDAO).setVisible(true);
			}
		});
		
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new DeleteJobForm(jobDAO).setVisible(true);
			}
		});
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			StellaUI ui = new StellaUI();
			ui.setVisible(true);
		});
	}

}
