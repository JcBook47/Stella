package net.stella;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Stella {
	private JobDAO jobDAO;
	
	public Stella() {
		jobDAO = new JobDAO();
	}
	
	public void addJob(Job job) {
		jobDAO.addJob(job);
	}
	
	public void updateJobStatus(String jobId, JobStatus status) {
		Job job = jobDAO.getJob(jobId);
		
		if (job != null) {
			job.setStatus(status);
			if (status == JobStatus.MATERIALS_CALCULATED && job.getDueDate() != null) {
				LocalDate dueDate = job.getDueDate();
				LocalDate materialDeadline = dueDate.minusDays(2);
				if (LocalDate.now().isAfter(materialDeadline)) {
					System.out.println("Warning: Material cost should have been obtained by " + materialDeadline + ".");
				}
			}
			
			// Assuming you have an update method in JobDAO
			//jobDAO.updateJob(job);
		}
	}
	
	public static void main(String[] args) {
		Stella stella = new Stella();
		
		Job job = new Job("J001", "John Doe", "Roof replacement", LocalDate.of(2024, 8, 15), "123 Main St", "555-1234", JobStatus.RECEIVED_JOB_REQUEST);
		stella.addJob(job);
	}
}