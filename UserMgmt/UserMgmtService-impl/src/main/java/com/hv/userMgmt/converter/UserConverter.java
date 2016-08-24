package com.hv.userMgmt.converter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hv.userMgmt.domain.User;
import com.hv.userMgmt.domain.UserProfile;
import com.hv.userMgmt.dto.UserDTO;
import com.hv.userMgmt.dto.UserProfileDTO;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * 
 * @author harshul.varshney
 *
 */
@Component
public class UserConverter {

	/**
	 * Accepts User domain objects and returns UserDTO object.
	 * @param user
	 * @return
	 */
	public UserDTO mapUser(User user) {
	
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		
		mapperFactory.classMap(User.class, UserDTO.class)
//			.field("userIdentifier", "userIdentifier")
//			.field("userName", "userName")
//			.field("password", "password")
			.byDefault()
			.customize(new CustomMapper<User, UserDTO>() {
				@Override
			    public void mapAtoB(User source, UserDTO target, MappingContext context) {
			      // some mapping logic here...
			    }
			})
			.register();
		
		UserDTO userDTO = mapperFactory.getMapperFacade().map(user, UserDTO.class);
		return userDTO;
	}
	
	public User mapUser(UserDTO userDTO) {
		
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		
		mapperFactory.classMap(UserDTO.class, User.class)
			.byDefault()
			.register();
		
		User user = mapperFactory.getMapperFacade().map(userDTO, User.class);
		return user;
	}
	
	public List<UserProfileDTO> mapUserProfiles(List<UserProfile> userProfiles) {
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(UserProfileDTO.class, UserProfile.class)
			.byDefault()
			.register();
		
		List<UserProfileDTO> up = mapperFactory.getMapperFacade().mapAsList(userProfiles, UserProfileDTO.class);
		
		return up;
	}
}
