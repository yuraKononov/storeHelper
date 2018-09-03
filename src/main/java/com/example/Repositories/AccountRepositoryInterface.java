package com.example.Repositories;

import com.example.Entities.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AccountRepositoryInterface {
    //add yours method
    Account findById(int user_id);
    List<Account> getAllAccount();
    Account getAccountById(int user_id);
    void addAccount(Account account);
    void updateAccount(Account account);
    void deleteAccount(int user_id);
}
