package com.nehdi.ghazi.test_oauth2_security;

import com.nehdi.ghazi.test_oauth2_security.Entity.UserEntity;
import com.nehdi.ghazi.test_oauth2_security.Repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<UserEntity> getAll() {
    return userRepository.findAll();
  }
}
