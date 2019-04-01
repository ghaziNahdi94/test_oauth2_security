package com.nehdi.ghazi.test_oauth2_security.Config;

import com.nehdi.ghazi.test_oauth2_security.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountDetailsService implements UserDetailsService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public AccountDetailsService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username)
        .map(user -> new User(user.getUsername(), passwordEncoder.encode(user.getPassword()),
            user.isActivated(), true,
            true, true,
            AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER")))
        .orElseThrow(
            () -> new UsernameNotFoundException("couldn't find user with username : " + username));
  }
}
