package com.hv.userMgmt.dto;

import java.util.Set;


/**
 * 
 * @author harshul.varshney
 *
 */
public class UserDTO {
	
	private String userIdentifier;
	
	private String userName;
     
    private String password;
         
    private String firstName;
 
    private String lastName;
 
    private String email;
 
    private String status;
    
	private String createdBy;
    
	private String createdDate;
    
   	private String updatedBy;
       
   	private String updatedDate;
    
    private String deleted;
    
    private Set<UserProfileDTO> userProfiles;

	public String getUserIdentifier() {
		return userIdentifier;
	}

	public void setUserIdentifier(String userIdentifier) {
		this.userIdentifier = userIdentifier;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public Set<UserProfileDTO> getUserProfiles() {
		return userProfiles;
	}

	public void setUserProfiles(Set<UserProfileDTO> userProfiles) {
		this.userProfiles = userProfiles;
	}

}
