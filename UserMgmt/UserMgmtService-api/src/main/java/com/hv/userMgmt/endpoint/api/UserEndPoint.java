package com.hv.userMgmt.endpoint.api;

import java.util.List;

import com.hv.userMgmt.dto.UserDTO;
import com.hv.userMgmt.dto.UserProfileDTO;

public interface UserEndPoint {

	UserDTO findById(int id);
    
	UserDTO findByUsername(String username);
	
	void createUser(UserDTO userDTO);
	
	List<UserProfileDTO> findAllUserProfiles();
}
