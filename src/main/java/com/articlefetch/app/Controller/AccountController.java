package com.articlefetch.app.Controller;

import com.articlefetch.app.Busniess.Service.AccountService;
import com.articlefetch.app.Controller.JacksonModels.Account;
import com.articlefetch.app.Controller.JacksonModels.AccountStatus;
import com.articlefetch.app.Controller.ResponseEntities.SuccessResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API Routes related to accounts
 */

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired AccountService accountService;

    @GetMapping("/allAccounts")
    public ResponseEntity<List<Account>> getAllAccounts() {
        return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK);
    }

    @GetMapping("/getAccount")
    public ResponseEntity<Account> getAccount( @RequestParam(value = "id", required = true) Integer id){
        return new ResponseEntity<>(accountService.getAccount(id), HttpStatus.OK);
    }

    @PutMapping("/create")
    public ResponseEntity<Map<String, String>> createAccount(@RequestBody Account account) {
        accountService.createAccount(account);
        return SuccessResponseEntity.createdResponseEntity();
    }

    @PutMapping("/reactivate")
    public ResponseEntity<Map<String, String>> reactivateAccount(@RequestParam(value = "id", required = true) Integer id) {
        accountService.reactivateAccount(id);
        return SuccessResponseEntity.updatedResposeEntity();
    }

    @PutMapping("/deactivate")
    public  ResponseEntity<Map<String, String>> deactivateAccount(@RequestParam(value = "id", required = true) Integer id) {
        accountService.deactivateAccount(id);
        return SuccessResponseEntity.updatedResposeEntity();
    }

}
