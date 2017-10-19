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


/**
 * The persistent class for the user_login database table.
 */
@Entity
@Table(name = "forum")
@NamedQuery(name = "Forum.findAll", query = "SELECT u FROM Forum u")
public class ForumPost implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "forum_id")
    @Setter @Getter(AccessLevel.PUBLIC)
    private int forum_id;

    @Setter @Getter(AccessLevel.PUBLIC)
    private Timestamp createdAt;

    @Setter @Getter(AccessLevel.PUBLIC)
    private Timestamp updatedAt;

    @Setter @Getter(AccessLevel.PUBLIC)
    private String url;

    @Setter @Getter(AccessLevel.PUBLIC)
    private String source;

    @Setter @Getter(AccessLevel.PUBLIC)
    private String userName;

    @Setter @Getter(AccessLevel.PUBLIC)
    private String userType;

    @Setter @Getter(AccessLevel.PUBLIC)
    private String title;

    @Setter @Getter(AccessLevel.PUBLIC)
    private String content;

    @Setter @Getter(AccessLevel.PUBLIC)
    private String strPostedAt;


}