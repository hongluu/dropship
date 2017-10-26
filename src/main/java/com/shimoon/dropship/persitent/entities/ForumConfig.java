package com.shimoon.dropship.persitent.entities;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


/**
 * The persistent class for the user_login database table.
 */
@Entity( name = "forum_config")
public class ForumConfig extends  BaseEntity{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "forum_config_id")
    @Getter(AccessLevel.PUBLIC) @Setter
    private int id;

    @Getter(AccessLevel.PUBLIC) @Setter
    private int num_thread;

    @Getter(AccessLevel.PUBLIC)
    @Setter
    private String prefix_url_has_data;

    @Getter(AccessLevel.PUBLIC) @Setter
    private String forum_name;

    @Lob
    @Getter(AccessLevel.PUBLIC) @Setter
    private String origin_link;

    @Lob
    @Getter(AccessLevel.PUBLIC) @Setter
    private String should_visit_prefix_links;

    @Lob
    @Getter(AccessLevel.PUBLIC) @Setter
    private String ignore_links;

    @Lob
    @Getter(AccessLevel.PUBLIC) @Setter
    private String ignore_keywords;

    @Getter(AccessLevel.PUBLIC) @Setter
    private int total_post;

    @Getter(AccessLevel.PUBLIC) @Setter
    private String title_selector;

    @Getter(AccessLevel.PUBLIC) @Setter
    private int status;

    @Getter(AccessLevel.PUBLIC) @Setter
    private String post_selector;

    @Getter(AccessLevel.PUBLIC) @Setter
    private String postedat_selector;

    @Getter(AccessLevel.PUBLIC) @Setter
    private String comment_selector;

    @Getter(AccessLevel.PUBLIC) @Setter
    private String username_selector;

    @Getter(AccessLevel.PUBLIC) @Setter
    private String usertype_selector;

    @Getter(AccessLevel.PUBLIC) @Setter
    private String source;

}