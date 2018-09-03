package com.example.Repositories;

import com.example.Entities.Shop;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class ShopRepository implements ShopRepositoryInterface {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Shop findByName(String shop_name) {
        return entityManager.find(Shop.class, shop_name);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Shop> getAllShops() {
        String hql = "FROM Shop";
        return (List<Shop>) entityManager.createQuery(hql).getResultList();
    }

    @Override
    public Shop getShopById(int shop_id) {
        return entityManager.find(Shop.class, shop_id);
    }

    @Override
    public void addShop(Shop shop) {
        entityManager.persist(shop);
    }

    @Override
    public void updateShop(Shop shop) {
        Shop shp = getShopById(shop.getShop_id());
        shp.setShop_name(shop.getShop_name());
        shp.setShop_address(shop.getShop_address());
        shp.setShop_image(shop.getShop_image());
        entityManager.flush();
    }

    @Override
    public void deleteShop(int shop_id) {
        entityManager.remove(getShopById(shop_id));
    }
}
