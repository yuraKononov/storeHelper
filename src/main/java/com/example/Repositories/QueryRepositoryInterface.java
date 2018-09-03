package com.example.Repositories;

import com.example.Entities.Query;

import java.util.List;

public interface QueryRepositoryInterface {

    Query findByAccountId(int user_id);
    List<Query> getAllQuery();
    Query getQueryById(int shop_id, int product_id);
    void addQuery(Query query);
    void updateQuery(Query query);
    //void deleteQuery(int product_id);
}
