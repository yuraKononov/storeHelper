package com.example.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "product_list")
public class ProductList implements Serializable {
    private static final long serialVersionUID = 1L;


    public ProductList(){}

    public ProductList(Product product, Shop shop, double price, double discont, Date discont_begin, Date discont_end) {
        this.product = product;
        this.shop = shop;
        this.price = price;
        this.discont = discont;
        this.discont_begin = discont_begin;
        this.discont_end = discont_end;
    }

    @Id
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "product_id")
    private Product product;

    @Id
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "discont")
    private double discont;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "discont_begin", nullable = true)
    private Date discont_begin;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "discont_end", nullable = true)
    private Date discont_end;

    public Product getProduct() {
        return product;
    }

    public Shop getShop() {
        return shop;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscont() {
        return discont;
    }

    public Date getDiscont_begin() {
        return discont_begin;
    }

    public Date getDiscont_end() {
        return discont_end;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDiscont(double discont) {
        this.discont = discont;
    }

    public void setDiscont_begin(Date discont_begin) {
        this.discont_begin = discont_begin;
    }

    public void setDiscont_end(Date discont_end) {
        this.discont_end = discont_end;
    }
}
