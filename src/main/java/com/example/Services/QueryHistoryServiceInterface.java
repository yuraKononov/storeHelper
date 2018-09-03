package com.example.Services;

import com.example.Entities.QueryHistory;

import java.util.List;

public interface QueryHistoryServiceInterface {
    List<QueryHistory> getAllQueryHistory();
    QueryHistory getQueryHistoryById(int shop_id, int product_id);
    boolean addQueryHistory(QueryHistory queryHistory);
    void updateQueryHistory(QueryHistory queryHistory);
}
