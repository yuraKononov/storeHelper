package com.example.Services;

import com.example.Entities.ProductList;

import java.util.List;

public interface ProductListServiceInterface {
    List<ProductList> findProductListByShop(int shop_id);
    List<ProductList> findProductListByProduct(int product_id);
    List<ProductList> getAllProductList();
    ProductList getProductListById(int shop_id, int product_id);
    boolean addProductList(ProductList productList);
    void updateProductList(ProductList productList);
    void updateProductLists(List<ProductList> productLists);
    void clearProductList();
    List<ProductList> findSimilarProducts(ProductList product, List<ProductList>products);
}
