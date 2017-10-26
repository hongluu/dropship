package com.shimoon.dropship.web.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class AmazonCate {

    @Setter
    @Getter private String url;

    @Setter
    @Getter private String code;

    @Setter
    @Getter private String name;

    @Setter
    @Getter private List<AmazonCate> sub_categories;



}
