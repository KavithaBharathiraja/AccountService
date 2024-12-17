package com.BOA.AccountService.Repositories;

import com.BOA.AccountService.Models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    // You can define custom query methods here if needed, for example:
    // Optional<Account> findByAccountNumber(String accountNumber);
    // List<Account> findByUserId(Long userId);
}
