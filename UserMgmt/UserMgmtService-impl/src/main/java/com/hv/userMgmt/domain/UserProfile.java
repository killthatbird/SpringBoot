package com.hv.userMgmt.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.hv.common.domain.BaseEntity;
import com.hv.userMgmt.dto.UserProfileType;

@Entity
@Table(name="USER_PROFILE")
public class UserProfile extends BaseEntity {
 
    @Column(name="TYPE", unique=true, nullable=false)
    private String type = UserProfileType.USER.getUserProfileType();
    
    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(name = "APP_USER_USER_PROFILE", 
             joinColumns = { @JoinColumn(name = "USER_PROFILE_ID", referencedColumnName="ID") }, 
             inverseJoinColumns = { @JoinColumn(name = "USER_ID", referencedColumnName="ID") })
    private List<User> userList;
 
    public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public String getType() {
        return type;
    }
 
    public void setType(String type) {
        this.type = type;
    }
 
    @Override
    public String toString() {
        return "UserProfile [type=" + type  + "]";
    }
}
