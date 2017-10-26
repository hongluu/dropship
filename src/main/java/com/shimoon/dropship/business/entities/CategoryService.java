package com.shimoon.dropship.business.entities;

import com.shimoon.dropship.persitent.entities.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategory();

    void getAmazonCatOnlineAndUpdate();
}
