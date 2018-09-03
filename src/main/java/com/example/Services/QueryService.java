package com.example.Services;

import com.example.Entities.Query;
import com.example.Repositories.QueryRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryService implements QueryServiceInterface {

    @Autowired
    private QueryRepositoryInterface queryRepository;

    @Override
    public List<Query> getAllQuery() {
        return queryRepository.getAllQuery();
    }

    @Override
    public Query getQueryById(int shop_id, int product_id) {
        return queryRepository.getQueryById(shop_id, product_id);
    }

    @Override
    public boolean addQuery(Query query) {
        queryRepository.addQuery(query);
        return true;
    }
}
