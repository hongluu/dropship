package com.shimoon.dropship.persitent.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name="category")
public class Category extends BaseEntity{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter(AccessLevel.PUBLIC) @Setter
    private int id;

    @Getter(AccessLevel.PUBLIC) @Setter
    private String code;

    @Getter(AccessLevel.PUBLIC) @Setter
    private String source;

    @Getter(AccessLevel.PUBLIC) @Setter
    private int parent_id;

    @Getter(AccessLevel.PUBLIC) @Setter
    private String parent_code;

    @Getter(AccessLevel.PUBLIC) @Setter private String name;


    @Getter(AccessLevel.PUBLIC) @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    private MarketPlace marketPlace;

}
