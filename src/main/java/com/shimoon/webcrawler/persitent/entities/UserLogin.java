package com.shimoon.webcrawler.persitent.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the user_login database table.
 */
@Entity
@Table(name = "user_login")
@NamedQuery(name = "UserLogin.findAll", query = "SELECT u FROM UserLogin u")
public class UserLogin implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "created_at")
    private Timestamp createdAt;

    private String email;

    private String password;

    @Column(name = "status_flag")
    private short statusFlag;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "full_name")
    private String fullName;


    public UserLogin() {
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public short getStatusFlag() {
        return this.statusFlag;
    }

    public void setStatusFlag(short statusFlag) {
        this.statusFlag = statusFlag;
    }

    public Timestamp getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}