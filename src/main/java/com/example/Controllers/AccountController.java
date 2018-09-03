package com.example.Controllers;

import com.example.Entities.Account;
import com.example.Services.AccountServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/user")
public class AccountController {

    @Autowired
    AccountServiceInterface accountService;

    @PostMapping("/add")
    public ResponseEntity<Void> addAccount(@RequestBody Account account,  UriComponentsBuilder builder){
        boolean flag = accountService.addAccount(account);
        if(!flag)
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/user/{id}").buildAndExpand(account.getUser_id()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Account> updateAccount(@RequestBody Account account) {
        accountService.updateAccount(account);
        return new ResponseEntity<Account>(account, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable("id") Integer user_id) {
        accountService.deleteAccount(user_id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable("id") Integer user_id) {
        Account account = accountService.getAccountById(user_id);
        return new ResponseEntity<Account>(account, HttpStatus.OK);
    }

    @GetMapping("/get_all")
    public ResponseEntity<List<Account>> getAllAccount() {
        List<Account> list = accountService.getAllAccount();
        return new ResponseEntity<List<Account>>(list, HttpStatus.OK);
    }


    @RequestMapping("/test")
    public void test(){
        Account account = new Account(false);
        account.setActive(true);
        UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080");
        addAccount(account, urlBuilder);
    }
}
