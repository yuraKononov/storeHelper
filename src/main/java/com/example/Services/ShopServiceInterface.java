package com.example.Services;

import com.example.Entities.Shop;

import java.util.List;

public interface ShopServiceInterface {
    List<Shop> getAllShop();
    Shop getShopById(int shop_id);
    boolean addsShop(Shop shop);
    void updateShop(Shop shop);
    void deleteShop(int shop_id);
}
