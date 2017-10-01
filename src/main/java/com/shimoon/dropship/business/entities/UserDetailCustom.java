package com.shimoon.dropship.business.entities;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by HongLM on 6/7/17.
 */
public interface UserDetailCustom extends UserDetails {
   String getFullName();
}
