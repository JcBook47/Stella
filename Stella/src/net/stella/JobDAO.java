package net.stella;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobDAO {

	public void addJob(Job job) {

		String sql = "INSERT INTO jobs (jobid, clientname, jobdescription, duedate, location, contactinfo, status, estimator) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection connection = DatabaseManager.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, job.getJobId());
			stmt.setString(2, job.getClientName());
			stmt.setString(3, job.getJobDescription());
			stmt.setDate(4, java.sql.Date.valueOf(job.getDueDate()));
			stmt.setString(5, job.getLocation());
			stmt.setString(6, job.getContactInfo());
			stmt.setString(7, job.getStatus().name());
			stmt.setString(8, job.getEstimator());

			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateJob(Job job) {
		String sql = "UPDATE jobs SET clientName = ?, jobDescription = ?, dueDate = ?, location = ?, contactInfo = ?, status = ?, estimator = ? WHERE jobId = ?";
		try (Connection connection = DatabaseManager.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, job.getClientName());
			stmt.setString(2, job.getJobDescription());
			stmt.setDate(3, java.sql.Date.valueOf(job.getDueDate()));
			stmt.setString(4, job.getLocation());
			stmt.setString(5, job.getContactInfo());
			stmt.setString(6, job.getStatus().name());
			stmt.setString(7, job.getEstimator());
			stmt.setString(8, job.getJobId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteJob(String jobId) {
		String sql = "DELETE FROM jobs WHERE jobId = ?";
		try (Connection connection = DatabaseManager.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, jobId);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Job> getJobsByEstimator(String estimator) {
		List<Job> jobs = new ArrayList<>();
		String sql = "SELECT * FROM jobs WHERE estimator = ?";
		try (Connection connection = DatabaseManager.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, estimator);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Job job = new Job(
							rs.getString("jobId"),
							rs.getString("clientName"),
							rs.getString("jobDescription"),
							rs.getDate("dueDate").toLocalDate(),
							rs.getString("location"),
							rs.getString("contactInfo"),
							JobStatus.valueOf(rs.getString("status")),
							rs.getString("estimator")
							);
					jobs.add(job);
				
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return jobs;
	}
	
	public List<Job> getUnassignedJobs(String estimator) {
		List<Job> jobs = new ArrayList<>();
		String sql = "SELECT * FROM jobs WHERE estimator IS NULL";
		try (Connection connection = DatabaseManager.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql)) {
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Job job = new Job(
							rs.getString("jobId"),
							rs.getString("clientName"),
							rs.getString("jobDescription"),
							rs.getDate("dueDate").toLocalDate(),
							rs.getString("locatoin"),
							rs.getString("contactInfo"),
							JobStatus.valueOf(rs.getString("status")),
							rs.getString("estimator")
							);
					jobs.add(job);
				
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return jobs;
	}

	public Estimator getEstimator(String username, String password) {
		String sql = "SELECT * FROM estimators WHERE username = ? AND password = ?";
		try(Connection connection = DatabaseManager.getConnection();
			PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, username);
			stmt.setString(2, password);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return new Estimator(rs.getString("username"), rs.getString("password"));
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	// **** Old code that might not get used but maybe

	public Job getJob(String jobId) {
		String sql = "SELECT * FROM jobs WHERE jobId = ?";
		try (Connection connection = DatabaseManager.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, jobId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Job(rs.getString("jobId"), rs.getString("clientName"), rs.getString("jobDescription"),
						rs.getDate("dueDate").toLocalDate(), rs.getString("location"), rs.getString("contactInfo"),
						JobStatus.valueOf(rs.getString("status")), rs.getString("estimator"));
			}
		} catch (SQLException e) {
			e.getStackTrace();
		}
		return null;
	}

//	public List<Job> getAllJobs() {
//		List<Job> jobs = new ArrayList<>();
//		String sql = "SELECT * FROM jobs";
//		try (Connection connection = DatabaseManager.getConnection();
//				PreparedStatement stmt = connection.prepareStatement(sql);
//				ResultSet rs = stmt.executeQuery()) {
//			while (rs.next()) {
//				Job job = new Job(rs.getString("jobId"), rs.getString("clientName"), rs.getString("jobDescription"),
//						rs.getDate("dueDate").toLocalDate(), rs.getString("location"), rs.getString("contactInfo"),
//						JobStatus.valueOf(rs.getString("status")), rs.getString("estimator"));
//				jobs.add(job);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return jobs;
//	}
}
