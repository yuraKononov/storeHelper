package com.example.Controllers;

import com.example.Entities.QueryHistory;
import com.example.Services.QueryHistoryServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/query_history")
public class QueryHistoryController {

    @Autowired
    private QueryHistoryServiceInterface queryHistoryService;

    @GetMapping("/get_all")
    public ResponseEntity<List<QueryHistory>> getAllProductList() {
        List<QueryHistory> list = queryHistoryService.getAllQueryHistory();
        return new ResponseEntity<List<QueryHistory>>(list, HttpStatus.OK);
    }
}
