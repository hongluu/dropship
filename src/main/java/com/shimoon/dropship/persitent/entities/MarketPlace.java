package com.shimoon.dropship.persitent.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name="marketplace")
public class MarketPlace extends BaseEntity{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter(AccessLevel.PUBLIC) @Setter
    private int id;

    @Getter(AccessLevel.PUBLIC) @Setter
    private int status;

    @Getter(AccessLevel.PUBLIC) @Setter
    private String url_to_find_brower_node;

    @Getter(AccessLevel.PUBLIC) @Setter
    private String name;

    @Getter(AccessLevel.PUBLIC) @Setter
    private String source;


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "marketPlace")
    @Getter(AccessLevel.PUBLIC) @Setter
    private List<Category> categories;


}
