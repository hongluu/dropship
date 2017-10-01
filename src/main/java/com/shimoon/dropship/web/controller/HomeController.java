package com.shimoon.dropship.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Locale;

@Controller
@SessionScope
public class HomeController extends BaseController {

    @GetMapping("/login")
    public String home(Locale locale, Model model){
        return "login";
    }
    @GetMapping("/dashboard")
    public String dashboard(Locale locale, Model model){
        return "/pages/search_product";
    }

}
