package com.example.Services;

import com.example.Entities.Shop;
import com.example.Repositories.ShopRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService implements ShopServiceInterface {

    @Autowired
    private ShopRepositoryInterface shopRepository;

    @Override
    public List<Shop> getAllShop() {
        return shopRepository.getAllShops();
    }

    @Override
    public Shop getShopById(int shop_id) {
        return shopRepository.getShopById(shop_id);
    }

    @Override
    public boolean addsShop(Shop shop) {
        shopRepository.addShop(shop);
        return true;
    }

    @Override
    public void updateShop(Shop shop) {
        shopRepository.updateShop(shop);
    }

    @Override
    public void deleteShop(int shop_id) {
        shopRepository.deleteShop(shop_id);
    }
}
