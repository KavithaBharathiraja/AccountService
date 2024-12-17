package com.BOA.AccountService.Controller;

import com.BOA.AccountService.Models.Account;
import com.BOA.AccountService.Service.AccountServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountServiceImpl accountServiceImpl;

    private static final Logger logger = LogManager.getLogger(AccountController.class);

    public AccountController(AccountServiceImpl accountServiceImpl) {
        this.accountServiceImpl = accountServiceImpl;
    }

    // Create new account
    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account createdAccount = accountServiceImpl.createAccount(account);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    // Get all accounts
    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountServiceImpl.getAllAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    // Get a specific account by ID
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        Account account = accountServiceImpl.getAccountById(id);
        if (account != null) {
            return new ResponseEntity<>(account, HttpStatus.OK);
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }

    // Update an existing account
    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody Account account) {
        account.setAccountId(id);
        Account updatedAccount = accountServiceImpl.updateAccount(account);
        return ResponseEntity.ok(updatedAccount);
    }

    // Delete an account by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        boolean isDeleted = accountServiceImpl.deleteAccount(id);
        if (isDeleted) {
            return ResponseEntity.ok("Account deleted successfully.");
        } else {
            return ResponseEntity.status(404).body("Account not found.");
        }
    }
}
