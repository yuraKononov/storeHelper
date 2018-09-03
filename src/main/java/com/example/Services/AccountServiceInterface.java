package com.example.Services;

import com.example.Entities.Account;

import java.util.List;

public interface AccountServiceInterface {
    List<Account> getAllAccount();
    Account getAccountById(int user_id);
    boolean addAccount(Account account);
    void updateAccount(Account account);
    void deleteAccount(int user_id);
}
