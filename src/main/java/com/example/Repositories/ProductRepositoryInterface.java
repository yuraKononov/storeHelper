package com.example.Repositories;

import com.example.Entities.Product;

import java.util.List;

public interface ProductRepositoryInterface {
    //add yours method
    Product findByName(String product_name);
    List<Product> getAllProduct();
    Product getProductById(int product_id);
    void addProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(int product_id);
    void updateProducts(List<Product> products);
}
