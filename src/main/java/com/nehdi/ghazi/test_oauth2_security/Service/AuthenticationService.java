package com.nehdi.ghazi.test_oauth2_security.Service;

import java.util.Optional;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

  public String getCurrentUsername() {
    SecurityContext securityContext = SecurityContextHolder.getContext();
    UserDetails springSecurityUser = (UserDetails) securityContext.getAuthentication().getPrincipal();
    return Optional.ofNullable(springSecurityUser.getUsername()).orElse(null);
  }

}
