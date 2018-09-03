package com.example.Controllers;

import com.example.Entities.Product;
import com.example.Services.ProductServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductServiceInterface productService;

    @PostMapping("/add")
    public ResponseEntity<Void> addProduct(@RequestBody Product product, UriComponentsBuilder builder){
        boolean flag = productService.addProduct(product);
        if(!flag)
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/product/{id}").buildAndExpand(product.getProduct_id()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        productService.updateProduct(product);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    //@DeleteMapping("/delete/{id}")
    @Transactional
    @GetMapping("delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Integer product_id) {
        productService.deleteProduct(product_id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Integer product_id) {
        Product product = productService.getProductById(product_id);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @GetMapping("/get_all")
    public ResponseEntity<List<Product>> getAllShop() {
        List<Product> list = productService.getAllProduct();
        return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
    }
}
