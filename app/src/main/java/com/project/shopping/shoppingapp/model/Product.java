package com.project.shopping.shoppingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mohan on 21/05/17.
 */

public class Product implements Parcelable{

    private String name;
    private String image;
    private int price;
    private String desc;

    public Product() {
    }

    protected Product(Parcel in) {
        name = in.readString();
        image = in.readString();
        price = in.readInt();
        desc = in.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(image);
        dest.writeInt(price);
        dest.writeString(desc);
    }
}
