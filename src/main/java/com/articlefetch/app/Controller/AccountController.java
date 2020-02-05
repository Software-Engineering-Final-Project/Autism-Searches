package com.articlefetch.app.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    // This is a test endpoint
    @GetMapping("/test")
    public String dummy() {
        return "Hello World";
    }




}
