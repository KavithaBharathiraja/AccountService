package com.BOA.AccountService.Repositories;

import com.BOA.AccountService.Models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    // You can define custom query methods here if needed, for example:
    // Optional<Account> findByAccountNumber(String accountNumber);
    Account findByAccountNumber(String accountNumber);

}
