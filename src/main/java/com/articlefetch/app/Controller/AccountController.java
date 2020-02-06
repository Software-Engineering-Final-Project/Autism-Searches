package com.articlefetch.app.Controller;

import com.articlefetch.app.Busniess.Service.AccountService;
import com.articlefetch.app.Controller.JacksonModels.Account;
import com.articlefetch.app.Controller.JacksonModels.AccountStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Account getAccount(Integer key){
        return accountService.getAccount(key);
    }

    @PutMapping("/create")
    public AccountStatus createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @PutMapping("/reactivate")
    public AccountStatus reactivateAccount(String username){
        return accountService.reactivateAccount(username);
    }

    @PutMapping("/deactivate")
    public AccountStatus deactivateAccount(String username) {
        return accountService.deactivateAccount(username);

    }

}
