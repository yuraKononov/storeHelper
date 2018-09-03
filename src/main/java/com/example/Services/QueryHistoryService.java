package com.example.Services;

import com.example.Entities.QueryHistory;
import com.example.Repositories.QueryHistoryRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryHistoryService implements QueryHistoryServiceInterface {

    @Autowired
    private QueryHistoryRepositoryInterface queryHistoryRepository;

    @Override
    public List<QueryHistory> getAllQueryHistory() {
        return queryHistoryRepository.getAllQueryHistory();
    }

    @Override
    public QueryHistory getQueryHistoryById(int shop_id, int product_id) {
        return queryHistoryRepository.getQueryHistoryById(shop_id, product_id);
    }

    @Override
    public boolean addQueryHistory(QueryHistory queryHistory) {
        queryHistoryRepository.addQueryHistory(queryHistory);
        return true;
    }

    @Override
    public void updateQueryHistory(QueryHistory queryHistory) {
        queryHistoryRepository.updateQueryHistory(queryHistory);
    }
}
