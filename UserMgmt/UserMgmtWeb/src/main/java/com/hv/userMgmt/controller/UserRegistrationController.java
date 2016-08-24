package com.hv.userMgmt.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hv.userMgmt.dto.UserDTO;
import com.hv.userMgmt.dto.UserProfileDTO;
import com.hv.userMgmt.endpoint.api.UserEndPoint;

/**
 * 
 * @author harshul.varshney
 *
 */
@Controller
public class UserRegistrationController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserRegistrationController.class);
	
	@Autowired
    private UserEndPoint userService;
	
	@RequestMapping(value = "/newUser", method = RequestMethod.GET)
    public String newRegistration(ModelMap model) {
        UserDTO user = new UserDTO();
        model.addAttribute("user", user);
        return "newuser";
    }
	
	/*
     * This method will be called on form submission, handling POST request It
     * also validates the user input
     */
    @RequestMapping(value = "/newUser", method = RequestMethod.POST)
    public String saveRegistration(@Valid UserDTO user, BindingResult result, ModelMap model) {
 
        if (result.hasErrors()) {
            logger.error("There are errors");
            return "newuser";
        }
        userService.createUser(user);
         
        logger.info("First Name : "+user.getFirstName());
        logger.info("Last Name : "+user.getLastName());
        logger.info("Password : "+user.getPassword());
        logger.info("Email : "+user.getEmail());
        logger.info("Checking UsrProfiles....");
        if(user.getUserProfiles()!=null){
            for(UserProfileDTO profile : user.getUserProfiles()){
                System.out.println("Profile : "+ profile.getType());
            }
        }
         
        model.addAttribute("success", "User " + user.getFirstName() + " has been registered successfully");
        return "registrationsuccess";
    }
    
    @ModelAttribute("roles")
    public List<UserProfileDTO> initializeProfiles() {
        return userService.findAllUserProfiles();
    }

}
