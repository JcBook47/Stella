package net.stella;

import java.time.LocalDate;

public class Job {
	
	private String jobId;
	private String clientName;
	private String jobDescription;
	private LocalDate dueDate;
	private String location;
	private String contactInfo;
	private JobStatus status;
	
	// Constructors, getters, and setters
	
	public Job(String string, String string2, String string3, LocalDate of, String string4, String string5,
			JobStatus receivedJobRequest) {
		// TODO Auto-generated constructor stub
		this.jobId = string;
		this.clientName = string2;
		this.jobDescription = string3;
		this.dueDate = of;
		this.location = string4;
		this.contactInfo = string5;
		this.status = receivedJobRequest;
	}
	
	// *** Setters ***
	
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	
	public void setClientName(String name) {
		this.clientName = name;
	}
	
	public void setJobDescription(String jobDesc) {
		this.jobDescription = jobDesc;
	}
	
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public void setContactInfo(String conInfo) {
		this.contactInfo = conInfo;
	}
	
	public void setStatus(JobStatus status) {
		this.status = status;
	}
	
	// *** Getters ***

	public String getJobId() {
		return this.jobId;
	}
	
	public String getClientName() {
		return this.clientName;
	}
	
	public String getJobDescription() {
		return this.jobDescription;
	}
	
	public LocalDate getDueDate() {
		return this.dueDate;
	}
	
	public String getLocation() {
		return this.location;
	}
	
	public String getContactInfo() {
		return this.clientName;
	}
	
	public JobStatus getStatus() {
		return this.status;
	}
	
}
