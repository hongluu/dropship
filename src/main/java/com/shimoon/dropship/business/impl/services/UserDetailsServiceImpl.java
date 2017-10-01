package com.shimoon.dropship.business.impl.services;


import java.util.HashSet;
import java.util.Set;

import com.shimoon.dropship.business.entities.UserCustom;
import com.shimoon.dropship.business.entities.UserDetailCustom;
import com.shimoon.dropship.persitent.entities.UserLogin;
import com.shimoon.dropship.persitent.repository.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserLoginRepository userLoginRepository;

  @Override
  @Transactional(readOnly = true)
  public UserDetailCustom loadUserByUsername(String username) throws UsernameNotFoundException {
    UserLogin userLogin = userLoginRepository.findByUserNameOrEmail(username, username);

    if (userLogin == null) {
      throw new UsernameNotFoundException("User details not found with this username: " + username);
    }
    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        for (UserRole role : user.getUserRoles()){
//            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
//        }
    UserCustom user = new UserCustom(userLogin.getUserName(),
        userLogin.getPassword(), grantedAuthorities);
    user.setFullName(userLogin.getFullName());
    return user;

  }
}