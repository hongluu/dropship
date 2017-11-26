package com.shimoon.dropship.web.entities;

import lombok.Getter;
import lombok.Setter;

import javax.ws.rs.GET;

public class Product {
    @Setter @Getter
    private String [] images;

}
