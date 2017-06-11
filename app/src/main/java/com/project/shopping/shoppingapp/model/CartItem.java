package com.project.shopping.shoppingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mohan on 22/05/17.
 */

public class CartItem implements Parcelable {

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

    protected CartItem(Parcel in) {
        id = in.readString();
        product = in.readParcelable(Product.class.getClassLoader());
        quantity = in.readInt();
        totalPrice = in.readInt();
    }

    public static final Creator<CartItem> CREATOR = new Creator<CartItem>() {
        @Override
        public CartItem createFromParcel(Parcel in) {
            return new CartItem(in);
        }

        @Override
        public CartItem[] newArray(int size) {
            return new CartItem[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeParcelable(product, flags);
        dest.writeInt(quantity);
        dest.writeInt(totalPrice);
    }
}
