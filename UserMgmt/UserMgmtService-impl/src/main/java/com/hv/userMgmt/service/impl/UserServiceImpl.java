package com.hv.userMgmt.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hv.userMgmt.domain.User;
import com.hv.userMgmt.domain.UserProfile;
import com.hv.userMgmt.dto.Status;
import com.hv.userMgmt.repository.UserProfileRepository;
import com.hv.userMgmt.repository.UserRepository;
import com.hv.userMgmt.service.api.UserService;

/**
 * 
 * @author harshul.varshney
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private UserProfileRepository userProfileRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
 
    public User findById(int id) {
        return userRepository.findById(id);
    }
 
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    @Override
    public void createUser(User user) {
    	user.setPassword(passwordEncoder.encode(user.getPassword()));
    	user.setUserIdentifier(UUID.randomUUID().toString());
    	user.setCreatedBy("");
    	user.setCreatedDate(new Date());
    	user.setState(Status.ACTIVE.getName());
    	
    	loadUserProfiles(user);
    	
    	userRepository.persist(user);
    }
    
    /**
     * Need to load user profile entity in session else Hibernate will
     * throw detached entity exception.
     * @param user
     */
    private void loadUserProfiles(User user) {
    	Set<UserProfile> userProfileSet = new HashSet<>();
    	for(UserProfile up : user.getUserProfiles()) {
    		UserProfile userP = userProfileRepository.findByType(up.getType());
    		userProfileSet.add(userP);
    	}
    	
    	user.setUserProfiles(null);
    	user.setUserProfiles(userProfileSet);
    }

	@Override
	public List<UserProfile> findAllUserProfiles() {
		
		return userProfileRepository.findAll();
	}
}
