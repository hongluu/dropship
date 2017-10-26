package com.shimoon.dropship.web.controller;

import com.shimoon.dropship.business.entities.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryController extends BaseController{

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category/list")
    public String getALlCategory(Model model){
        model.addAttribute("categories",categoryService.getAllCategory());
        return "/pages/category/category_list";
    }

    @GetMapping("/category/amazon/update")
    public String updateCateAmazon(){
        categoryService.getAmazonCatOnlineAndUpdate();
        return "redirect:/category/list";
    }
}
