package com.example.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "query")
public class Query implements Serializable {
    private static final long serialVersionUID = 1L;

    public Query(Account account, ProductList productList, Date insert_date) {
        this.account = account;
        this.productList = productList;
        this.insert_date = insert_date;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "select_user_id")
    private Account account;

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "select_shop_id", referencedColumnName = "shop_id"),
            @JoinColumn(name = "select_product_id", referencedColumnName = "product_id")
    })
    private ProductList productList;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "insert_date", nullable = false)
    private Date insert_date;

    public Account getAccount() {
        return account;
    }

    public ProductList getProductList() {
        return productList;
    }

    public Date getInsert_date() {
        return insert_date;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setProductList(ProductList productList) {
        this.productList = productList;
    }

    public void setInsert_date(Date insert_date) {
        this.insert_date = insert_date;
    }


}
