package com.hv.userMgmt.endpoint.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hv.userMgmt.converter.UserConverter;
import com.hv.userMgmt.domain.User;
import com.hv.userMgmt.domain.UserProfile;
import com.hv.userMgmt.dto.UserDTO;
import com.hv.userMgmt.dto.UserProfileDTO;
import com.hv.userMgmt.endpoint.api.UserEndPoint;
import com.hv.userMgmt.service.api.UserService;

/**
 * 
 * @author harshul.varshney
 *
 */
@Component
public class UserEndPointImpl implements UserEndPoint{

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserConverter converter;
	
	@Override
	public UserDTO findById(int id) {
		User user = userService.findById(id);
		return converter.mapUser(user);
	}

	@Override
	public UserDTO findByUsername(String username) {
		User user = userService.findByUsername(username);
		
		if(user != null) {
			return converter.mapUser(user);
		}
		
		return null;
	}

	@Override
	public void createUser(UserDTO userDTO) {
		
		userService.createUser(converter.mapUser(userDTO));
	}

	@Override
	public List<UserProfileDTO> findAllUserProfiles() {
		List<UserProfile> userProfiles = userService.findAllUserProfiles();
		List<UserProfileDTO> userProfilesDTO = converter.mapUserProfiles(userProfiles);
		
		return userProfilesDTO;
	}

}
