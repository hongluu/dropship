package com.shimoon.dropship.web.controller;

import com.shimoon.dropship.business.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.SessionScope;

@Controller
@SessionScope
public class SearchController extends BaseController{




    private final CategoryService categoryService;
    private final  ContextBean contextBean;
    private String currentTypeSearch;

    @Autowired
    public SearchController(CategoryService categoryService, ContextBean contextBean) {
        this.categoryService = categoryService;
        this.contextBean = contextBean;


    }
    @GetMapping("/search")
    public String goPageSearch(Model model, @RequestParam("search_type") String searchType){
        this.currentTypeSearch =searchType;
        model.addAttribute("search_type",searchType);
        return "/pages/search/search_product";
    }

//    @GetMapping("/search/amazon")
//    public String goSearchOnAmazon(Model model){
//        return null;
//    }
//    @PostMapping(name = "/search/amazon")
//    public String searchOnAmazon(Model model){
//        return null;
//    }
}
