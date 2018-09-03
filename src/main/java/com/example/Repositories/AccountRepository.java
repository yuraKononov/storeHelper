package com.example.Repositories;

import com.example.Entities.Account;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class AccountRepository implements AccountRepositoryInterface {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Account findById(int user_id) {
        return this.entityManager.find(Account.class, user_id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Account> getAllAccount() {
        String hql = "FROM Account";
        return (List<Account>) entityManager.createQuery(hql).getResultList();
    }

    @Override
    public Account getAccountById(int user_id) {
        return this.entityManager.find(Account.class, user_id);
    }

    @Override
    public void addAccount(Account account) {
        entityManager.persist(account);
    }

    @Override
    public void updateAccount(Account account) {
        Account acnt = getAccountById(account.getUser_id());
        acnt.setActive(account.isActive());
        entityManager.flush();
    }

    @Override
    public void deleteAccount(int user_id) {
        entityManager.remove(getAccountById(user_id));
    }

}
