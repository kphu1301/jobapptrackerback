package com.example.jobapptracker.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="job_app")
public class JobApp {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="Company is required")
	private String company;
	
	@NotBlank(message="Position is required")
	private String position;
	
	private String jobPostingLink;
	
	
	private String recruiterName;
	
	@Email(message="Enter valid email address")
	private String recruiterEmail;
	
	@NotBlank(message="Status is required")
	private String status;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	public JobApp() {
		
	}
	
	public JobApp(String company, String position, String jobPostingLink
			, String recruiterName, String recruiterEmail, String status) {
		this.company = company;
		this.position = position;
		this.jobPostingLink = jobPostingLink;
		this.recruiterName = recruiterName;
		this.recruiterEmail = recruiterEmail;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getJobPostingLink() {
		return jobPostingLink;
	}

	public void setJobPostingLink(String jobPostingLink) {
		this.jobPostingLink = jobPostingLink;
	}
	

	public String getRecruiterName() {
		return recruiterName;
	}

	public void setRecruiterName(String recruiterName) {
		this.recruiterName = recruiterName;
	}

	public String getRecruiterEmail() {
		return recruiterEmail;
	}

	public void setRecruiterEmail(String recruiterEmail) {
		this.recruiterEmail = recruiterEmail;
	}
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Job [id=" + id + ", company=" + company + ", position=" + position + ", jobPostingLink=" + jobPostingLink
				+ "]";
	} 
	
}
