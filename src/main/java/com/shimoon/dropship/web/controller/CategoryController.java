package com.shimoon.dropship.web.controller;

import com.shimoon.dropship.business.service.CategoryService;
import com.shimoon.dropship.persitent.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CategoryController extends BaseController{


    private final  CategoryService categoryService;
    private final ContextBean contextBean;
    @Autowired
    public CategoryController(CategoryService categoryService, ContextBean contextBean) {
        this.categoryService = categoryService;
        this.contextBean = contextBean;
    }

    @GetMapping("/category/list")
    public String getALlCategory(Model model){
        List<Category> categoryList =categoryService.getAllCategory();
        contextBean.setCategories(categoryList);
        model.addAttribute("allCategories",categoryList);
        return "/pages/category/category_list";
    }

    @GetMapping("/category/amazon/update")
    public String updateCateAmazon(){
        categoryService.getAmazonCatOnlineAndUpdate();
        return "redirect:/category/list";
    }
}
