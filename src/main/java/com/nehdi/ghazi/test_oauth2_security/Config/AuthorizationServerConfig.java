package com.nehdi.ghazi.test_oauth2_security.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

  private final AuthenticationManager authenticationManager;
  private final PasswordEncoder passwordEncoder;
  private final AccountDetailsService accountDetailsService;

  @Autowired
  public AuthorizationServerConfig(
      AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder,
      AccountDetailsService accountDetailsService) {
    this.authenticationManager = authenticationManager;
    this.passwordEncoder = passwordEncoder;
    this.accountDetailsService = accountDetailsService;
  }

  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    security.checkTokenAccess("isAuthenticated()");
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.inMemory().withClient("ghazi")
        .authorizedGrantTypes("password", "refresh_token")
        .scopes("read", "write")
        .resourceIds("oauth2-resource")
        .accessTokenValiditySeconds(20)
        .refreshTokenValiditySeconds(10000)
        .secret(passwordEncoder.encode("nehdi"));
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints.authenticationManager(authenticationManager);
    endpoints.userDetailsService(accountDetailsService);
  }
}
