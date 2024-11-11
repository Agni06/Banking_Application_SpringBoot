package com.banking_application.MyBanking.Controller;

import com.banking_application.MyBanking.Entity.Account;
import com.banking_application.MyBanking.Service.AccountSetvice;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("banking")
public class AccountController {

    @Autowired
    AccountSetvice accountService;

    @GetMapping("allAccounts")
    public ResponseEntity<List<Account>> getAllAccounts(){
        return accountService.getALLAccounts();
    }

    @PostMapping("addAccount")
    public ResponseEntity<Account> addAccount(@RequestBody Account account){
        return accountService.addAccount(account);
    }

    @PutMapping("withdraw/{id}")
    public ResponseEntity<String> withdraw(@PathVariable("id") int id, @RequestBody Long amount) throws Exception {
        return accountService.withdraw(id,amount);
    }

    @GetMapping("accountById/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable("id") int id) throws Exception {
        return accountService.getAccountById(id);
    }

    @PutMapping("deposit/{id}")
    public ResponseEntity<String> deposit(@PathVariable("id") int id, @RequestBody Long amount) throws Exception {
        return accountService.deposit(id,amount);
    }

    @PutMapping("updateAccountDetails/{id}")
    public ResponseEntity<String> updateAccountDetails(@PathVariable("id") int id, @RequestBody Account account) throws Exception {
        return accountService.updateAccountDetails(id,account);
    }
}
