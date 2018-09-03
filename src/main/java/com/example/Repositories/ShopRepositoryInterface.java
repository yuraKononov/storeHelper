package com.example.Repositories;

import com.example.Entities.Shop;

import java.util.List;

public interface ShopRepositoryInterface {
    //add yours method
    Shop findByName(String name);
    List<Shop> getAllShops();
    Shop getShopById(int shop_id);
    void addShop(Shop shop);
    void updateShop(Shop shop);
    void deleteShop(int shop_id);
}
