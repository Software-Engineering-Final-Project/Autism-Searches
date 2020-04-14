package com.articlefetch.app.Controller;

import com.articlefetch.app.Busniess.Service.AccountService;
import com.articlefetch.app.Controller.JacksonModels.Account;
import com.articlefetch.app.Controller.JacksonModels.AccountCreate;
import com.articlefetch.app.Controller.JacksonModels.Category;
import com.articlefetch.app.Controller.ResponseEntities.SuccessResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
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
    public ResponseEntity<Account> getAccount(@RequestParam(value = "id", required = true) Integer id) throws IOException {
        return new ResponseEntity<>(accountService.getAccount(id), HttpStatus.OK);
    }

    @GetMapping("/getCategories")
    public ResponseEntity<List<Category>> getCategories(@RequestParam(value = "id", required = true) Integer id) {
        return new ResponseEntity<>(accountService.getStarredCategories(id), HttpStatus.OK);
    }

    @PostMapping("/updateAccount")
    public ResponseEntity<Account> updateAccount(@RequestBody Account account,
                                                 @RequestParam(value = "id", required = true) Integer id) throws IOException {
        return new ResponseEntity<>(accountService.updateAccount(id, account), HttpStatus.OK);
    }

    @PostMapping("/addCategories")
    public ResponseEntity<List<Category>> addCategories(
            @RequestBody List<Category> categoryList,
            @RequestParam(value = "id", required = true) Integer id,
            HttpServletRequest httpServletRequest)
    {
        List greetings = (List) httpServletRequest.getSession().getAttribute("GREETING_MESSAGES");
        if(greetings == null) {
            System.out.println("NO AVAILABLE HEADER");
        }
        return new ResponseEntity<>(accountService.addStarredCategories(categoryList, id), HttpStatus.OK);
    }

    @PostMapping("/removeCategories")
    public ResponseEntity<List<Category>> removeCategories(
            @RequestBody List<Category> categoryList,
            @RequestParam(value = "id", required = true) Integer id
    ) {
        return new ResponseEntity<>(accountService.removeStarredCategories(categoryList, id), HttpStatus.OK);
    }

    @PutMapping("/create")
    public ResponseEntity<Map<String, String>> createAccount(@RequestBody AccountCreate accountCreate)
            throws InvalidKeySpecException, NoSuchAlgorithmException {
        Integer id = accountService.createAccount(accountCreate);
        return SuccessResponseEntity.createdResponseEntity(id);
    }

    @PutMapping("/reactivate")
    public ResponseEntity<Map<String, String>> reactivateAccount(@RequestParam(value = "id", required = true) Integer id) {
        accountService.reactivateAccount(id);
        return SuccessResponseEntity.updatedResponseEntity();
    }

    @PutMapping("/deactivate")
    public  ResponseEntity<Map<String, String>> deactivateAccount(@RequestParam(value = "id", required = true) Integer id) {
        accountService.deactivateAccount(id);
        return SuccessResponseEntity.updatedResponseEntity();
    }
}
