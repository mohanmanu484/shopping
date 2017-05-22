package com.project.shopping.shoppingapp.model;

/**
 * Created by mohan on 22/05/17.
 */

public class CartItem {

    private String  id;
    private Product product;
    private int quantity;
    private int totalPrice;

    public CartItem() {
    }

    public CartItem(Product product, int quantity, int totalPrice) {
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
