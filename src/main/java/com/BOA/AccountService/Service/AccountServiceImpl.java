package com.BOA.AccountService.Service;


import com.BOA.AccountService.Models.Account;
import com.BOA.AccountService.Models.User;
import com.BOA.AccountService.Repositories.AccountRepository;
import com.BOA.AccountService.Service.Interfaces.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final RestTemplate restTemplate;

    private static final String USER_SERVICE_URL = "http://localhost:8081/users/";

    public AccountServiceImpl(AccountRepository accountRepository, RestTemplate restTemplate) {
        this.accountRepository = accountRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public Account createAccount(Account account) {
        // Fetch user from the external service
        User user = restTemplate.getForObject(USER_SERVICE_URL + account.getUser().getUserId(), User.class);

        // Check if user exists, else throw exception
        if (user == null) {
            throw new RuntimeException("User with ID " + account.getUser().getUserId() + " not found.");
        }

        // Set the user to the account
        account.setUser(user);

        // Save account to the repository
        return accountRepository.save(account);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccountById(Long accountId) {
        return accountRepository.findById(accountId).orElse(null);
    }

    @Override
    public boolean deleteAccount(Long accountId) {
        if (accountRepository.existsById(accountId)) {
            accountRepository.deleteById(accountId);
            return true;
        }
        return false;
    }
}
