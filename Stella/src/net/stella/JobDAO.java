package net.stella;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JobDAO {
	
	public void addJob(Job job) {
		
		
		String sql = "INSERT INTO jobs (jobid, clientname, jobdescription, duedate, location, contactinfo, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (Connection connection = DatabaseManager.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, job.getJobId());
			stmt.setString(2, job.getClientName());
			stmt.setString(3, job.getJobDescription());
			stmt.setDate(4, java.sql.Date.valueOf(job.getDueDate()));
			stmt.setString(5, job.getLocation());
			stmt.setString(6, job.getContactInfo());
			stmt.setString(7, job.getStatus().name());
			
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Job getJob(String jobId) {
		String sql = "SELECT * FROM jobs WHERE jobId = ?";
		try(Connection connection = DatabaseManager.getConnection();
				PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, jobId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Job(
						rs.getString("jobId"),
						rs.getString("clientName"),
						rs.getString("jobDescription"),
						rs.getDate("dueDate").toLocalDate(),
						rs.getString("location"),
						rs.getString("contactInfo"),
						JobStatus.valueOf(rs.getString("status"))
						);
			}
		} catch (SQLException e) {
			e.getStackTrace();
		}
		return null;
	}
	
	//Implement updateJob and deleteJob similarly
	
	public void updateJob(Job job) {
		String sql = "UPDATE jobs SET clientName = ?, jobDescription = ?, dueDate = ?, location = ?, contactInfo = ?, status = ? WHERE jobId = ?";
	    try (Connection connection = DatabaseManager.getConnection();
	         PreparedStatement stmt = connection.prepareStatement(sql)) {
	        stmt.setString(1, job.getClientName());
	        stmt.setString(2, job.getJobDescription());
	        stmt.setDate(3, java.sql.Date.valueOf(job.getDueDate()));
	        stmt.setString(4, job.getLocation());
	        stmt.setString(5, job.getContactInfo());
	        stmt.setString(6, job.getStatus().name());
	        stmt.setString(7, job.getJobId());
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
}
