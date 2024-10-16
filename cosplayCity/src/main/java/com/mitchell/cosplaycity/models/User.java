package com.mitchell.cosplaycity.models;

import java.util.Date;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {
	
	//Class attributes
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "")
	@Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters.")
	private String userName;
	
	@NotBlank(message = "Email is required.")
	@Email(message = "Please enter a valid email!")
	private String email;
	
	@NotBlank(message = "")
	@Size(min = 8, max = 128, message = "Password must be between 8 and 128 characters")
	private String password;
	
	@Transient
	@NotBlank(message = "")
	@Size(min = 8, max = 128, message = "Password confirmation must be between 8 and 128 characters")
	private String confirmPassword;
	
	@OneToOne(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Profile profile;
	
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY)
	private List<Cluster> clusters;
	
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY)
	private List<Upcoming> upcomings;
	
	@Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    
    //Generators
    
    public User() {}
    
	public User(
			String userName,
			String email,
			String password,
			String confirmPassword) {
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}
	
	//Getters and Setters
	    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public List<Cluster> getClusters() {
		return clusters;
	}

	public void setClusters(List<Cluster> clusters) {
		this.clusters = clusters;
	}
	
	public List<Upcoming> getUpcomings() {
		return upcomings;
	}

	public void setUpcomings(List<Upcoming> upcomings) {
		this.upcomings = upcomings;
	}

	public Profile getProfile() {
		return profile;
	}
	
	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	//createdAt and updatedAt logic

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	 
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

}
