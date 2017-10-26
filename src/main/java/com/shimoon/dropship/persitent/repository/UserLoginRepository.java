package com.shimoon.dropship.persitent.repository;

import java.io.Serializable;
import java.util.List;

import com.shimoon.dropship.persitent.entities.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, Serializable> {

  UserLogin findByUserNameAndPassword(String string, String string2);

  UserLogin findByUserName(String username);

  List<UserLogin> findAll();

  UserLogin findByEmail(String email);

  UserLogin findByUserNameOrEmail(String username, String email);

  UserLogin findByUserId(Integer user_id);
}
