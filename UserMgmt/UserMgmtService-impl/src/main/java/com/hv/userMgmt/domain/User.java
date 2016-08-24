package com.hv.userMgmt.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.hv.common.domain.AbstractTrackedEntity;

/**
 * 
 * @author harshul.varshney
 *
 */
@Entity
@Table(name="APP_USER")
public class User extends AbstractTrackedEntity {
	
	@Column(name="USER_IDENTIFIER", nullable=false)
	private String userIdentifier;
	
	@Column(name="USER_NAME", nullable=false)
	private String userName;
     
    @Column(name="PASSWORD", nullable=false)
    private String password;
         
    @Column(name="FIRST_NAME", nullable=false)
    private String firstName;
 
    @Column(name="LAST_NAME", nullable=false)
    private String lastName;
 
    @Column(name="EMAIL", nullable=false)
    private String email;
 
    @Column(name="STATUS", nullable=false)
    private String status;
 
    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(name = "APP_USER_USER_PROFILE", 
             joinColumns = { @JoinColumn(name = "USER_ID", referencedColumnName="ID") }, 
             inverseJoinColumns = { @JoinColumn(name = "USER_PROFILE_ID", referencedColumnName="ID") })
    private Set<UserProfile> userProfiles = new HashSet<UserProfile>();
 
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
 
    public String getState() {
        return status;
    }
 
    public void setState(String state) {
        this.status = state;
    }
 
    public Set<UserProfile> getUserProfiles() {
        return userProfiles;
    }
 
    public void setUserProfiles(Set<UserProfile> userProfiles) {
        this.userProfiles = userProfiles;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof User))
            return false;
        User other = (User) obj;
        if (userIdentifier != other.userIdentifier)
            return false;
        return true;
    }
 
    @Override
    public String toString() {
        return "User [userIdentifier=" + userIdentifier
                + ", firstName=" + firstName + ", lastName=" + lastName
                + ", email=" + email + ", status=" + status + ", userProfiles=" + userProfiles +"]";
    }
}
