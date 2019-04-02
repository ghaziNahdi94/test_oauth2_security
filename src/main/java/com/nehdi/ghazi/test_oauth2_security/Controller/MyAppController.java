package com.nehdi.ghazi.test_oauth2_security.Controller;

import com.nehdi.ghazi.test_oauth2_security.Service.AuthenticationService;
import com.nehdi.ghazi.test_oauth2_security.Service.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyAppController {

  private final UserService userService;
  private final AuthenticationService authenticationService;

  @Autowired
  public MyAppController(UserService userService,
      AuthenticationService authenticationService) {
    this.userService = userService;
    this.authenticationService = authenticationService;
  }

  @GetMapping(value = "/public", produces = MediaType.APPLICATION_JSON_VALUE)
  public Object sayHello() {
    return new Object() {
      @Getter
      private String hello = "hello";
    };
  }

  @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
  public Object getUsers() {
    return userService.getAll();
  }

  @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
  public Object getAuthenticatedUser() {
    return new Object() {
      @Getter
      private String username = authenticationService.getCurrentUsername();
    };
  }

}
