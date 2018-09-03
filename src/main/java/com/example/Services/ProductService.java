package com.example.Services;

import com.example.Entities.Product;
import com.example.Repositories.ProductRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements ProductServiceInterface {

    @Autowired
    private ProductRepositoryInterface productRepository;

    @Override
    public List<Product> getAllProduct() {
        return productRepository.getAllProduct();
    }

    @Override
    public Product getProductById(int product_id) {
        return productRepository.getProductById(product_id);
    }

    @Override
    public Product findProductByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public boolean addProduct(Product product) {
        productRepository.addProduct(product);
        return true;
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.updateProduct(product);
    }

    @Override
    public void deleteProduct(int product_id) {
        productRepository.deleteProduct(product_id);
    }

    @Override
    public void updateProducts(List<Product> products) {
        productRepository.updateProducts(products);
    }
}
