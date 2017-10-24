package com.shimoon.webcrawler.config.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsService userDetailsService;

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.sessionManagement().maximumSessions(1).expiredUrl("/error");
    http // ignore our stomp endpoints since they are protected using
        // Stomp headers
        .headers()
        // allow same origin to frame our site to support iframe SockJS
        .frameOptions().sameOrigin().and().authorizeRequests()
        .antMatchers("/resources/**","/theme/**", "/css/**", "/js/**", "/img/**", "/security-policy", "/role-use", "/error","/rest/**","/encode-password","/debug/**").permitAll().anyRequest()

        .authenticated().and().authorizeRequests().antMatchers("/api/**").authenticated()
        .and().formLogin().loginPage("/login").defaultSuccessUrl("/forum/list", true).permitAll().and()
        .logout().permitAll();
  }


}
