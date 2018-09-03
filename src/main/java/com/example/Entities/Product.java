package com.example.Entities;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "product")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    public Product(){}

    public Product(String image, String product_name){
        this.image = image;
        this.product_name = product_name;
       // productList = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id", length = 11, nullable = false)
    private int product_id;

    @Lob
    @Column(name = "image", length = 255, nullable = true)
    private String image;

    @Column(name = "product_name", length = 255, nullable = false)
    private String product_name;

    /*@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductList> productList;*/

    public int getProduct_id() {
        return product_id;
    }

    public String getImage() {
        return image;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    /*public Set<ProductList> getProductList() {
        return productList;
    }

    public void setProductList(Set<ProductList> productList) {
        this.productList = productList;
    }*/

    @Override
    public String toString() {
        return "Product[id_product=" + product_id + ", product_name=" + product_name;
    }
}