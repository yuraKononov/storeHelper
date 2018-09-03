package com.example.Repositories;

import com.example.Entities.Account;
import com.example.Entities.QueryHistory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class QueryHistoryRepository implements QueryHistoryRepositoryInterface {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public QueryHistory findByAccountId(int user_id) {
        return this.entityManager.find(QueryHistory.class, user_id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<QueryHistory> getAllQueryHistory() {
        String hql = "FROM QueryHistory";
        return (List<QueryHistory>) entityManager.createQuery(hql).getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public QueryHistory getQueryHistoryById(int shop_id, int product_id) {
        javax.persistence.Query query = entityManager.createQuery("FROM QueryHistory WHERE history_shop_id=:shopId " +
                "AND history_product_id=:productId");
        query.setParameter("shopId", shop_id);
        query.setParameter("productId", product_id);
        return (QueryHistory) query.getResultList().get(0);
    }

    @Override
    public void addQueryHistory(QueryHistory queryHistory) {
        this.entityManager.persist(queryHistory);
    }

    @Override
    public void updateQueryHistory(QueryHistory queryHistory) {
        QueryHistory qr = getQueryHistoryById(queryHistory.getProductList().getShop().getShop_id(),
                queryHistory.getProductList().getProduct().getProduct_id());
        qr.setAccount(qr.getAccount());
        qr.setProductList(qr.getProductList());
        entityManager.flush();
    }
}
