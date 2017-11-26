package com.shimoon.dropship.business.service;

import com.shimoon.dropship.business.entities.ServiceResult;
import com.shimoon.dropship.persitent.entities.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategory();

    void getAmazonCatOnlineAndUpdate();

    List<Category> getCategoryBy(String store);
}
