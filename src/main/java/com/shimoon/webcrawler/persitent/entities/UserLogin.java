package com.shimoon.webcrawler.persitent.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the user_login database table.
 */
@Document(collection = "user_login")
public class UserLogin implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    @Getter(AccessLevel.PUBLIC) @Setter  private int userId;

    @Column(name = "created_at")
    @Getter(AccessLevel.PUBLIC) @Setter private Timestamp createdAt;

    @Getter(AccessLevel.PUBLIC) @Setter private String email;

    @Getter(AccessLevel.PUBLIC) @Setter private String password;

    @Column(name = "status_flag")
    @Getter(AccessLevel.PUBLIC) @Setter private short statusFlag;

    @Column(name = "updated_at")
    @Getter(AccessLevel.PUBLIC) @Setter private Timestamp updatedAt;

    @Column(name = "user_name")
    @Getter(AccessLevel.PUBLIC) @Setter private String userName;

    @Column(name = "full_name")
    @Getter(AccessLevel.PUBLIC) @Setter private String fullName;
}