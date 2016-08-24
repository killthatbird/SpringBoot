package com.hv.userMgmt.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.hv.userMgmt.dto.UserProfileDTO;

@Component
public class RoleToUserProfileConverter implements Converter<Object, UserProfileDTO> {

	@Override
	public UserProfileDTO convert(Object source) {
		UserProfileDTO userProfile = new UserProfileDTO();
		userProfile.setType((String)source);
		return userProfile;
	}

}
