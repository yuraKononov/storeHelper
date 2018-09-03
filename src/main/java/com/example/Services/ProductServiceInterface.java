package com.example.Services;

import com.example.Entities.Product;

import java.util.List;

public interface ProductServiceInterface {
    List<Product> getAllProduct();
    Product getProductById(int product_id);
    Product findProductByName(String name);
    boolean addProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(int product_id);
    void updateProducts(List<Product> products);
}
