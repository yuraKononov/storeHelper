package com.example.Repositories;

import com.example.Entities.ProductList;
import org.hibernate.TransientObjectException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ProductListRepository implements ProductListRepositoryInterface {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<ProductList> findByShop(int shop_id) {
        Query query = entityManager.createQuery("FROM ProductList WHERE shop_id=:shopId");
        query.setParameter("shopId", shop_id);
        return (List<ProductList>) query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ProductList> findByProduct(int product_id) {
        Query query = entityManager.createQuery("FROM ProductList WHERE product_id=:productId");
        query.setParameter("productId", product_id);
        return (List<ProductList>) query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ProductList> getAllProductList() {
        return (List<ProductList>) entityManager.createQuery("FROM ProductList").getResultList();
    }

    @Override
    public ProductList getProductListById(int shop_id, int product_id) {
        Query query = entityManager.createQuery("FROM ProductList WHERE product_id=:productId AND shop_id=:shopId");
        query.setParameter("productId", product_id);
        query.setParameter("shopId", shop_id);
        return (ProductList) query.getResultList().get(0);
    }

    @Override
    public void addProductList(ProductList productList) {
        entityManager.persist(productList);
    }

    @Override
    public void updateProductList(ProductList productList) {

    }

    @Transactional
    @Override
    public void updateProductLists(List<ProductList> productLists) {
        for(ProductList productList : productLists) {
            Query query = entityManager.createQuery("UPDATE ProductList SET price=:priceTmp, discont=:discontTmp, " +
                    "discont_begin=:discontBegin, discont_end=:discontEnd WHERE product_id=:productId AND shop_id=:shopId");
            query.setParameter("priceTmp", productList.getPrice());
            query.setParameter("discontTmp", productList.getDiscont());
            query.setParameter("discontBegin", productList.getDiscont_begin());
            query.setParameter("discontEnd", productList.getDiscont_end());
            query.setParameter("productId", productList.getProduct().getProduct_id());
            query.setParameter("shopId", productList.getShop().getShop_id());

                int res = query.executeUpdate();
                if(res == 0){
                    addProductList(productList);
                }

        }
    }

    @Transactional
    @Override
    public void clearProductList() {
        Query query = entityManager.createQuery("DELETE FROM ProductList");
        query.executeUpdate();
    }
}
