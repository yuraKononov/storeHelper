package com.example.Controllers;

import com.example.Entities.Query;
import com.example.Services.QueryServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/query")
public class QueryController {

    @Autowired
    private QueryServiceInterface queryService;

    @GetMapping("/get_all")
    public ResponseEntity<List<Query>> getAllProductList() {
        List<Query> list = queryService.getAllQuery();
        return new ResponseEntity<List<Query>>(list, HttpStatus.OK);
    }

    @GetMapping("/s_id={shop_id}&p_id={product_id}")
    public ResponseEntity<Query> getProductListById(@PathVariable("shop_id") Integer shop_id,
                                                    @PathVariable("product_id") Integer product_id) {
        Query query = queryService.getQueryById(shop_id, product_id);
        return new ResponseEntity<Query>(query, HttpStatus.OK);
    }
}
