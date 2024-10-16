package com.mitchell.cosplaycity.models;

import java.util.Date;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="profiles")
public class Profile {
	
	//Class attributes
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(max = 150, message = "Bio cannot exceed 150 characters")
	private String bio;

	private String profilePhoto;
	private String pronouns;
	private List<String> upcoming;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    
    //Generators
        
    public Profile() {}
    
    public Profile(User user) {
    	this.user = user;
    }
    
	//Getters and Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	public String getPronouns() {
		return pronouns;
	}

	public void setPronouns(String pronouns) {
		this.pronouns = pronouns;
	}

	public List<String> getUpcoming() {
		return upcoming;
	}

	public void setUpcoming(List<String> upcoming) {
		this.upcoming = upcoming;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
