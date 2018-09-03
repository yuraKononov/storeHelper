package com.example.Repositories;

import com.example.Entities.ProductList;

import java.util.List;

public interface ProductListRepositoryInterface {
    List<ProductList> findByShop(int shop_id);
    List<ProductList> findByProduct(int product_id);
    List<ProductList> getAllProductList();
    ProductList getProductListById(int shop_id, int product_id);
    void addProductList(ProductList productList);
    void updateProductList(ProductList productList);
    void updateProductLists(List<ProductList> productLists);
    void clearProductList();
}
