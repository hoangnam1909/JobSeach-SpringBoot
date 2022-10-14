package com.nhn.controllers;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;

public class AdviceController {

    @ModelAttribute("something")
    public String getAccessToken(@RequestHeader(value="Authorization") String accessToken) {
        return accessToken;
    }

}
