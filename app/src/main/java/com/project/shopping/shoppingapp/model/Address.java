package com.project.shopping.shoppingapp.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mohan on 22/05/17.
 */

public class Address extends BaseObservable implements Parcelable{

    private String name="";
    private String phone="";

    private String city="";
    private String area="";
    private String doorNo="";
    private String pincode="";
    private String state="";

    public Address() {
    }

    protected Address(Parcel in) {
        name = in.readString();
        phone = in.readString();
        city = in.readString();
        area = in.readString();
        doorNo = in.readString();
        pincode = in.readString();
        state = in.readString();
    }

    public static final Creator<Address> CREATOR = new Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };

    @Bindable
    public String getName() {
        return name;
    }

    @Bindable
    public void setName(String name) {
        this.name = name;
    }

    @Bindable
    public String getPhone() {
        return phone;
    }

    @Bindable
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Bindable
    public String getCity() {
        return city;
    }

    @Bindable
    public void setCity(String city) {
        this.city = city;
    }

    @Bindable
    public String getArea() {
        return area;
    }

    @Bindable
    public void setArea(String area) {
        this.area = area;
    }

    @Bindable
    public String getDoorNo() {
        return doorNo;
    }

    @Bindable
    public void setDoorNo(String doorNo) {
        this.doorNo = doorNo;
    }

    @Bindable
    public String getPincode() {
        return pincode;
    }

    @Bindable
    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    @Bindable
    public String getState() {
        return state;
    }

    @Bindable
    public void setState(String state) {
        this.state = state;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(phone);
        dest.writeString(city);
        dest.writeString(area);
        dest.writeString(doorNo);
        dest.writeString(pincode);
        dest.writeString(state);
    }
}
