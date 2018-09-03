package com.example.Controllers;

import com.example.Bitap;
import com.example.Entities.ProductList;
import com.example.Services.ProductListServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Stream;

@RestController
@RequestMapping("/product_list")
public class ProductListController {

    @Autowired
    private ProductListServiceInterface productListServiceInterface;

    @GetMapping("/get_all")
    public ResponseEntity<List<ProductList>> getAllProductList() {
        List<ProductList> list = productListServiceInterface.getAllProductList();
        return new ResponseEntity<List<ProductList>>(list, HttpStatus.OK);
    }

    @GetMapping("/s_id={shop_id}&p_id={product_id}")
    public ResponseEntity<ProductList> getProductListById(@PathVariable("shop_id") Integer shop_id,
                                                          @PathVariable("product_id") Integer product_id) {
        ProductList productList = productListServiceInterface.getProductListById(shop_id, product_id);
        return new ResponseEntity<ProductList>(productList, HttpStatus.OK);
    }

    @GetMapping("/shop_id={shop_id}")
    public ResponseEntity<List<ProductList>> getProductListByShopId(@PathVariable("shop_id") Integer shop_id) {
        List<ProductList> list = productListServiceInterface.findProductListByShop(shop_id);
        return new ResponseEntity<List<ProductList>>(list, HttpStatus.OK);
    }

    @GetMapping("/product_id={product_id}")
    public ResponseEntity<List<ProductList>> getProductListByProductId(@PathVariable("product_id") Integer product_id) {
        List<ProductList> list = productListServiceInterface.findProductListByProduct(product_id);
        return new ResponseEntity<List<ProductList>>(list, HttpStatus.OK);
    }

    @GetMapping("/get_similar/s_id={shop_id}&p_id={product_id}")
    public ResponseEntity<List<ProductList>> getSimilarProducts(@PathVariable("shop_id") Integer shop_id,
                                                                @PathVariable("product_id") Integer product_id) {
        List<ProductList> list = productListServiceInterface.findSimilarProducts(productListServiceInterface.getProductListById(shop_id,
                product_id), productListServiceInterface.getAllProductList());
        return new ResponseEntity<List<ProductList>>(list, HttpStatus.OK);
    }
}
