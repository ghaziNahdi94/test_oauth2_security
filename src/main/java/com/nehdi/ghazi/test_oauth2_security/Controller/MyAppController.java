package com.nehdi.ghazi.test_oauth2_security.Controller;

import com.nehdi.ghazi.test_oauth2_security.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyAppController {

  private final UserService userService;

  @Autowired
  public MyAppController(UserService userService) {
    this.userService = userService;
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

}
