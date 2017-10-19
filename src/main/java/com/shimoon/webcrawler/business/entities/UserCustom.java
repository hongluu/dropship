package com.shimoon.webcrawler.business.entities;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * Created by HongLM on 6/7/17.
 */
public class UserCustom extends User implements UserDetailCustom {

  public UserCustom(String username, String password,
      Collection<? extends GrantedAuthority> authorities) {
    super(username, password, authorities);
  }
  private String fullName;
  private int userId;
  @Override
  public String getFullName() {
    return this.fullName;
  }


  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }
}
