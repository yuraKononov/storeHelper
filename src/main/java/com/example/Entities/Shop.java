package com.example.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shop")
public class Shop implements Serializable{
    private static final long serialVersionUID = 1L;

    public Shop(){}

    public Shop(String shop_name, String shop_address, String shop_image){
        this.shop_name = shop_name;
        this.shop_address = shop_address;
        this.shop_image = shop_image;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "shop_id", length = 11, nullable = false)
    private int shop_id;

    @Column(name = "shop_name", length = 255, nullable = false)
    private String shop_name;

    @Column(name = "shop_address", length = 45, nullable = true)
    private String shop_address;

    @Column(name = "shop_image", length = 255, nullable = true)
    private String shop_image;

    /*@OneToMany(mappedBy = "shop")
    private Set<ProductList> productList;*/

    public int getShop_id() {
        return shop_id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public String getShop_address() {
        return shop_address;
    }

    public String getShop_image() {
        return shop_image;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public void setShop_address(String shop_address) {
        this.shop_address  = shop_address;
    }

    public void setShop_image(String shop_image) {
        this.shop_image = shop_image;
    }

    /*
    public Set<ProductList> getProductList() {
        return productList;
    }

    public void setProductList(Set<ProductList> productList) {
        this.productList = productList;
    }*/
}
