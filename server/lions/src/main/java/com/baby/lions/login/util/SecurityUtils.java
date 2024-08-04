package com.baby.lions.login.util;

import com.baby.lions.login.config.PrincipalDetails;
import com.baby.lions.login.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils{
    public static Long getCurrentUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!= null && authentication.getPrincipal() instanceof PrincipalDetails){
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            User user = principalDetails.getUser();
            return user.getId();
        }
        return null;
    }
}