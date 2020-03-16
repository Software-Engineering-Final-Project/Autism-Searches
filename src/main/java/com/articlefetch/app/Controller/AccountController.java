package com.articlefetch.app.Controller;

import com.articlefetch.app.Busniess.Service.AccountService;
import com.articlefetch.app.Controller.JacksonModels.Account;
import com.articlefetch.app.Controller.JacksonModels.AccountStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * API Routes related to accounts
 */

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired AccountService accountService;

    @GetMapping("/allAccounts")
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/getAccount")
    public Account getAccount( @RequestParam(value = "id", required = true) Integer id){
        return accountService.getAccount(id);
    }

    @PutMapping("/create")
    public ResponseEntity<String> createAccount(@RequestBody Account account) {
        accountService.createAccount(account);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    @PutMapping("/reactivate")
    public ResponseEntity<String> reactivateAccount(@RequestParam(value = "id", required = true) Integer id) {
        accountService.reactivateAccount(id);
        return new ResponseEntity<>("Success", HttpStatus.ACCEPTED);
    }

    @PutMapping("/deactivate")
    public  ResponseEntity<String> deactivateAccount(@RequestParam(value = "id", required = true) Integer id) {
        accountService.deactivateAccount(id);
        return new ResponseEntity<>("Success", HttpStatus.ACCEPTED);
    }

}
