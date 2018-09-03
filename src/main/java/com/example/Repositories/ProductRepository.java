package com.example.Repositories;

import com.example.Entities.Product;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ProductRepository implements ProductRepositoryInterface {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Product findByName(String product_name) {
        Query query = entityManager.createQuery("FROM Product WHERE product_name=:productName");
        query.setParameter("productName", product_name);
            return (Product) query.getResultList().get(0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Product> getAllProduct() {
        String hql = "FROM Product";
        return (List<Product>) entityManager.createQuery(hql).getResultList();
    }

    @Override
    public Product getProductById(int product_id) {
        return this.entityManager.find(Product.class, product_id);
    }

    @Transactional
    @Override
    public void addProduct(Product product) {
        entityManager.persist(product);
    }

    @Override
    public void updateProduct(Product product) {
        Product prdct = getProductById(product.getProduct_id());
        prdct.setImage(product.getImage());
        prdct.setProduct_name(product.getProduct_name());
        entityManager.flush();
    }

    @Override
    public void deleteProduct(int id_product) {
        entityManager.remove(getProductById(id_product));
    }

    @Transactional
    @Override
    public void updateProducts(List<Product> products) {
        for(Product product : products) {
            Query query = entityManager.createQuery("UPDATE Product SET image=:imageTmp WHERE product_name=:productName");
            query.setParameter("productName", product.getProduct_name());
            query.setParameter("imageTmp", product.getImage());

            int res = query.executeUpdate();
            if(res == 0){
                addProduct(product);
            }
        }
    }
}
