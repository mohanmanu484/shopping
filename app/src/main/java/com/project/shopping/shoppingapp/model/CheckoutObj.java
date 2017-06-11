package com.project.shopping.shoppingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mohan on 22/05/17.
 */

public class CheckoutObj implements Parcelable{

    private int amount;
    private int itemCount;
    private User user;

    public CheckoutObj(int amount, int itemCount) {
        this.amount = amount;
        this.itemCount = itemCount;
    }

    public CheckoutObj() {
    }

    protected CheckoutObj(Parcel in) {
        amount = in.readInt();
        itemCount = in.readInt();
    }

    public static final Creator<CheckoutObj> CREATOR = new Creator<CheckoutObj>() {
        @Override
        public CheckoutObj createFromParcel(Parcel in) {
            return new CheckoutObj(in);
        }

        @Override
        public CheckoutObj[] newArray(int size) {
            return new CheckoutObj[size];
        }
    };

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(amount);
        dest.writeInt(itemCount);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
