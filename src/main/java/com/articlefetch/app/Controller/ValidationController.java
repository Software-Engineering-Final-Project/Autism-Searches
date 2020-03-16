package com.articlefetch.app.Controller;

import com.articlefetch.app.Busniess.Service.AccountService;
import com.articlefetch.app.Busniess.Service.LoginService;
import com.articlefetch.app.Busniess.Service.LoginServiceImpl;
import com.articlefetch.app.Controller.JacksonModels.Account;
import com.articlefetch.app.Controller.JacksonModels.LoginValidation;
import com.articlefetch.app.Controller.ResponseEntities.SuccessResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * API Routes related to validation
 */

@RestController
@RequestMapping("/validate")
public class ValidationController {

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody LoginValidation validation) {
        Account account = loginService.validateAccount(validation.getUsername(), validation.getPassword());

        return new ResponseEntity<>(account, HttpStatus.OK);
    }
}
