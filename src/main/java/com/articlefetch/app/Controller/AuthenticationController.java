package com.articlefetch.app.Controller;

import com.articlefetch.app.Busniess.Service.LoginService;
import com.articlefetch.app.Controller.JacksonModels.Account;
import com.articlefetch.app.Controller.JacksonModels.AccountCreate;
import com.articlefetch.app.Controller.JacksonModels.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * API Routes related to validation
 */

@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody Authentication validation) throws IOException {
        Account accountCreate = loginService.validateAccount(validation.getUsername(), validation.getPassword());
        return new ResponseEntity<Account>(accountCreate, HttpStatus.OK);
    }
}
