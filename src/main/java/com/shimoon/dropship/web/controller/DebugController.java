package com.shimoon.dropship.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.annotation.SessionScope;

@Controller
@SessionScope
public class DebugController extends BaseController {
    @GetMapping("/debug/search")
    public String search(){
        return "/pages/search_product";
    }


}
