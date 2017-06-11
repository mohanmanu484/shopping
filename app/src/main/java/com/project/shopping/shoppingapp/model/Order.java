package com.project.shopping.shoppingapp.model;

import java.util.List;

/**
 * Created by mohan on 11/06/17.
 */

public class Order {

    private String orderId;
    private List<Product> productList;
    private int price;

    public Order(String orderId, List<Product> productList, int price) {
        this.orderId = orderId;
        this.productList = productList;
        this.price = price;
    }

    public Order() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
