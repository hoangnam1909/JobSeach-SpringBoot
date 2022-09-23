package com.nhn.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Render {

    @RequestMapping("/admin-test")
    public String admin(){
        return "admin";
    }

}
