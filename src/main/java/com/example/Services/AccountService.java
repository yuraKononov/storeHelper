package com.example.Services;

import com.example.Entities.Account;
import com.example.Repositories.AccountRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements AccountServiceInterface {

    @Autowired
    private AccountRepositoryInterface accountRepositoryInterface;

    @Override
    public List<Account> getAllAccount() {
        return accountRepositoryInterface.getAllAccount();
    }

    @Override
    public Account getAccountById(int id) {
        return accountRepositoryInterface.getAccountById(id);
    }

    @Override
    public boolean addAccount(Account account) {
        accountRepositoryInterface.addAccount(account);
        return true;
    }

    @Override
    public void updateAccount(Account account) {
        accountRepositoryInterface.updateAccount(account);
    }

    @Override
    public void deleteAccount(int id) {
        accountRepositoryInterface.deleteAccount(id);
    }
}
