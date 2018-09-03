package com.example.Entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "query_history")
public class QueryHistory implements Serializable {
    private static final long serialVersionUID = 1L;

    public QueryHistory(){}

    public QueryHistory(Account account, ProductList productList) {
        this.account = account;
        this.productList = productList;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "history_user_id")
    private Account account;

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "history_shop_id", referencedColumnName = "shop_id"),
            @JoinColumn(name = "history_product_id", referencedColumnName = "product_id")
    })
    private ProductList productList;

    public Account getAccount() {
        return account;
    }

    public ProductList getProductList() {
        return productList;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setProductList(ProductList productList) {
        this.productList = productList;
    }
}
