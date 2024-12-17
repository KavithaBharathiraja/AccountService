package com.BOA.AccountService.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class User {

    @Id
    private Long userId;

    private String username;
    private String email;

    @OneToMany(mappedBy = "user")
    @JsonIgnore  // Prevent accounts from being serialized with User
    private List<Account> accounts;
}
