package com.hv.userMgmt.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hv.userMgmt.dto.UserDTO;
import com.hv.userMgmt.dto.UserProfileDTO;
import com.hv.userMgmt.endpoint.api.UserEndPoint;

/**
 * 
 * @author harshul.varshney
 *
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
	
	@Autowired
    private UserEndPoint userService;
    
	@Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String ssoId)
            throws UsernameNotFoundException {
        UserDTO user = userService.findByUsername(ssoId);
        
        if(user==null){
            throw new UsernameNotFoundException("Username not found");
        }
        logger.info("user found :"+user.getUserName());
        return new User(user.getUserName(), user.getPassword(), 
                 user.getStatus().equals("Active"), true, true, true, getGrantedAuthorities(user));
    }
 
     
    private List<GrantedAuthority> getGrantedAuthorities(UserDTO user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
         
        for(UserProfileDTO userProfile : user.getUserProfiles()){
            logger.info("user profiles :"+user.getUserProfiles());
            authorities.add(new SimpleGrantedAuthority(userProfile.getType()));
        }
        logger.info("authorities :"+authorities);
        return authorities;
    }
     
}
