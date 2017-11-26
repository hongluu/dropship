package com.shimoon.dropship.web.entities;

import com.shimoon.dropship.persitent.entities.Category;
import com.shimoon.dropship.persitent.entities.MarketPlace;
import com.shimoon.dropship.persitent.repository.MarketplaceRepo;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class AmazonCate {

    public static final String AMAZON_URL = "amazon.com";
    @Setter
    @Getter private String url;

    @Setter
    @Getter private String code;

    @Setter
    @Getter private String name;

    @Setter
    @Getter private String parentCode;

    @Setter
    @Getter private List<AmazonCate> sub_categories;
    @Setter
    @Getter private MarketPlace marketplace;

    public Category toCategory(){
        Category category = new Category();
        category.setCode(code);
        category.setParent_id(0);
        category.setSource(AMAZON_URL);
        category.setName(name);
        category.setMarketPlace(marketplace);

        return category;
    }



}
