package com.BOA.AccountService.Service;

import com.BOA.AccountService.Models.Account;
import com.BOA.AccountService.Repositories.AccountRepository;
import com.BOA.AccountService.Service.Interfaces.AccountService;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private static final Logger logger = LogManager.getLogger(AccountServiceImpl.class);

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public Account createAccount(Account account) {
        // Log that an account is being created, using account.getUser().getId() to get the userId
        if (account.getUser() != null && account.getUser().getUserId() != null) {
            logger.info("Creating a new account for user ID: {}", account.getUser().getUserId());
        } else {
            logger.warn("User information is incomplete, cannot log user ID");
        }

        // Save the account and return it
        return accountRepository.save(account);
    }

    @Override
    @Transactional
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    @Transactional
    public Account getAccountById(Long id) {
        // Retrieve the account and log
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Account updateAccount(Account account) {
        // Check if account exists
        if (!accountRepository.existsById(account.getAccountId())) {
            logger.error("Account with ID {} not found for update", account.getAccountId());
            return null;
        }

        // Log the userId before updating the account
        // Accessing userId from User entity within Account entity
        if (account.getUser() != null && account.getUser().getUserId() != null) {
            logger.info("Creating a new account for user ID: {}", account.getUser().getUserId());
        } else {
            logger.warn("User information is incomplete, cannot log user ID");
        }

        // Update the account and return it
        return accountRepository.save(account);
    }

    @Override
    @Transactional
    public boolean deleteAccount(Long id) {
        if (!accountRepository.existsById(id)) {
            logger.error("Account with ID {} not found for deletion", id);
            return false;  // Account doesn't exist
        }
        accountRepository.deleteById(id);
        logger.info("Deleted account with ID: {}", id);
        return true;
    }
}
