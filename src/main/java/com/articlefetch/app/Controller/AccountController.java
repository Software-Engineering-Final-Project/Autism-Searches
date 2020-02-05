package com.articlefetch.app.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    // This is a test endpoint
    @GetMapping("/test")
    public Test dummy() {
        return new Test();
    }


    private static class Test {
        public final String msg = "Hello from spring! :-)";
    }

}
