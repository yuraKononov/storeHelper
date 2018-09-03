package com.example.Services;

import com.example.Bitap;
import com.example.Entities.ProductList;
import com.example.Repositories.ProductListRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
public class ProductListService implements ProductListServiceInterface {

    @Autowired
    private ProductListRepositoryInterface productListRepository;

    @Override
    public List<ProductList> findProductListByShop(int shop_id) {
        return productListRepository.findByShop(shop_id);
    }

    @Override
    public List<ProductList> findProductListByProduct(int product_id) {
        return productListRepository.findByProduct(product_id);
    }

    @Override
    public List<ProductList> getAllProductList() {
        return productListRepository.getAllProductList();
    }

    @Override
    public ProductList getProductListById(int shop_id, int product_id) {
        return productListRepository.getProductListById(shop_id, product_id);
    }

    @Override
    public boolean addProductList(ProductList productList) {
        productListRepository.addProductList(productList);
        return true;
    }

    @Override
    public void updateProductList(ProductList productList) {
        productListRepository.updateProductList(productList);
    }

    @Override
    public void updateProductLists(List<ProductList> productLists) {
        productListRepository.updateProductLists(productLists);
    }

    @Override
    public void clearProductList() {
        productListRepository.clearProductList();
    }

    @Override
    public List<ProductList> findSimilarProducts(ProductList product, List<ProductList>products){

        List<ProductList> res = new ArrayList<>();
        String [] split = product.getProduct().getProduct_name().split(" ");
        Map<ProductList, Integer> resultMap = new HashMap<>();
        for(ProductList productNameItem : products){
            if(productNameItem == product)
                continue;
            int sum = 0;
            int min = 0;

            for(int i = 0; i < split.length; ++i){
                String [] splitItem = productNameItem.getProduct().getProduct_name().split(" ");
                for(int k = 0; k < splitItem.length; ++k) {
                    int tmp = editdist(splitItem[k], split[i]);
                    if(min > tmp || k == 0)
                        min = tmp;
                    //sum += editdist(splitItem[k], split[i]);
                }
                sum += min;
                System.out.println(sum + "  " + productNameItem.getProduct().getProduct_name() + "  " + split[i]);

            }
            //resultMap.put(productNameItem, sum / (productNameItem.getProduct().getProduct_name().split(" ").length));
            resultMap.put(productNameItem, sum);
        }

        resultMap = sortByValue(resultMap);

        for(int k = 0; k < 3; k++){
            res.add((ProductList) resultMap.keySet().toArray()[k]);
        }

        return res;
    }

    private  <K, V extends Comparable<? super V>> Map<K, V>
    sortByValue( Map<K, V> map )
    {
        Map<K,V> result = new LinkedHashMap<>();
        Stream<Map.Entry<K,V>> st = map.entrySet().stream();

        st.sorted(Comparator.comparing(e -> e.getValue()))
                .forEach(e ->result.put(e.getKey(),e.getValue()));

        return result;
    }

    private int editdist(String s1, String s2){
        int m = s1.length(), n = s2.length();
        int[] D1;
        int[] D2 = new int[n + 1];

        for(int i = 0; i <= n; i ++)
            D2[i] = i;

        for(int i = 1; i <= m; i ++) {
            D1 = D2;
            D2 = new int[n + 1];
            for(int j = 0; j <= n; j ++) {
                if(j == 0) D2[j] = i;
                else {
                    int cost = (s1.charAt(i - 1) != s2.charAt(j - 1)) ? 1 : 0;
                    if(D2[j - 1] < D1[j] && D2[j - 1] < D1[j - 1] + cost)
                        D2[j] = D2[j - 1] + 1;
                    else if(D1[j] < D1[j - 1] + cost)
                        D2[j] = D1[j] + 1;
                    else
                        D2[j] = D1[j - 1] + cost;
                }
            }
        }
        return D2[n];
    }
}
