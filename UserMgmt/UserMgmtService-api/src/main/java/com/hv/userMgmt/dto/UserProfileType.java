package com.hv.userMgmt.dto;

public enum UserProfileType {
	USER("USER"),
    DBA("DBA"),
    ADMIN("ADMIN");
     
    String userProfileType;
     
    private UserProfileType(String userProfileType){
        this.userProfileType = userProfileType;
    }
     
    public String getUserProfileType(){
        return userProfileType;
    }
}
