package com.BOA.AccountService.Service.Interfaces;

import com.BOA.AccountService.Models.Account;

import java.util.List;

public interface AccountService {
    Account createAccount(Account account);
    List<Account> getAllAccounts();
    Account getAccountById(Long accountId);
    boolean deleteAccount(Long accountId);
}
