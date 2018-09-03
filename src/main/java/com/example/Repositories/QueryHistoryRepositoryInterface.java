package com.example.Repositories;

import com.example.Entities.QueryHistory;

import java.util.List;

public interface QueryHistoryRepositoryInterface {
    QueryHistory findByAccountId(int user_id);
    List<QueryHistory> getAllQueryHistory();
    QueryHistory getQueryHistoryById(int shop_id, int product_id);
    void addQueryHistory(QueryHistory queryHistory);
    void updateQueryHistory(QueryHistory queryHistory);
}
