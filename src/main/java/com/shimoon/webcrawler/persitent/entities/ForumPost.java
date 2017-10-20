package com.shimoon.webcrawler.persitent.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * The persistent class for the user_login database table.
 */

@Document(collection = "forum_post")
public class ForumPost implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "forum_id")
    @Getter(AccessLevel.PUBLIC) @Setter private int forum_id;

    @Column(name = "createdAt")
    @Getter(AccessLevel.PUBLIC) @Setter private Timestamp createdAt;

    @Getter(AccessLevel.PUBLIC) @Setter private Timestamp updatedAt;

    @Getter(AccessLevel.PUBLIC) @Setter private String url;

    @Getter(AccessLevel.PUBLIC) @Setter private String source;

    @Getter(AccessLevel.PUBLIC) @Setter private String userName;

    @Getter(AccessLevel.PUBLIC) @Setter private String userType;

    @Getter(AccessLevel.PUBLIC) @Setter  private String title;

    @Getter(AccessLevel.PUBLIC) @Setter private String content;

    @Getter(AccessLevel.PUBLIC) @Setter private String strPostedAt;






}