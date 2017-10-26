package com.shimoon.dropship.persitent.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name="category")
public class Category {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "forum_config_id")
    @Getter(AccessLevel.PUBLIC) @Setter
    private int id;

    @Getter(AccessLevel.PUBLIC) @Setter
    private int category_code;

    @Getter(AccessLevel.PUBLIC) @Setter
    private String url_origin;

    @Getter(AccessLevel.PUBLIC) @Setter
    private int parent_id;


}
