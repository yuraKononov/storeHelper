package com.example.Repositories;

import com.example.Entities.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class QueryRepository implements QueryRepositoryInterface {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Query findByAccountId(int user_id) {
        javax.persistence.Query query = entityManager.createQuery("FROM Query WHERE user_id=:userId");
        query.setParameter("userId", user_id);
        return (Query) query.getResultList().get(0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Query> getAllQuery() {
        String hql = "FROM Query";
        return (List<Query>) entityManager.createQuery(hql).getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Query getQueryById(int shop_id, int product_id) {
        javax.persistence.Query query = entityManager.createQuery("FROM Query WHERE select_shop_id=:shopId " +
                "AND select_product_id=:productId");
        query.setParameter("shopId", shop_id);
        query.setParameter("productId", product_id);
        return (Query) query.getResultList().get(0);
    }

    @Override
    public void addQuery(Query query) {
        this.entityManager.persist(query);
    }

    @Override
    public void updateQuery(Query query) {
        /*Query qr = getQueryByItemId(.getProduct_id());
        prdct.setImage(product.getImage());
        prdct.setProduct_name(product.getProduct_name());
        entityManager.flush();*/
    }
}
