package com.example.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.repository.AccountRepository;
import com.example.entity.Account;
import com.example.exception.UsernameExists;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public boolean existsById(Integer id){
        return accountRepository.existsById(id);
    }

    public boolean existsByUsername(String username){
        return accountRepository.existsByUsername(username);
    }

    public Account saveAccount(Account account){

        if(account.getUsername() == null || account.getUsername().isBlank()){
            throw new IllegalArgumentException("Username is blank");
        }

        if (account.getPassword().length() <= 4){
            throw new IllegalArgumentException("Password less than 4 characters");
        }

        if (existsByUsername(account.getUsername())){
            throw new UsernameExists("User " + account.getUsername() + " already exists");
        }

        return accountRepository.save(account);
    }


}
