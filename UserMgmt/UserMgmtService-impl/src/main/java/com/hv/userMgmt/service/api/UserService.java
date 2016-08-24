package com.hv.userMgmt.service.api;

import java.util.List;

import com.hv.userMgmt.domain.User;
import com.hv.userMgmt.domain.UserProfile;

/**
 * 
 * @author harshul.varshney
 *
 */
public interface UserService {

	User findById(int id);
    
	User findByUsername(String username);

	void createUser(User mapUser);
	
	List<UserProfile> findAllUserProfiles();
     
}
