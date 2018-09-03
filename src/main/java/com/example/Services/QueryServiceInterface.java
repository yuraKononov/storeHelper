package com.example.Services;

import com.example.Entities.Query;

import java.util.List;

public interface QueryServiceInterface {
    List<Query> getAllQuery();
    Query getQueryById(int shop_id, int product_id);
    boolean addQuery(Query query);
    /*void updateShop(Shop shop);
    void deleteShop(int shop_id);*/
}
