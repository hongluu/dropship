package com.shimoon.dropship.business.entities;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Created by HongLM on 6/7/17.
 */
@Service
public interface UserDetailCustom extends UserDetails {
   String getFullName();
}
