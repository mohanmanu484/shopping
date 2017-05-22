package com.project.shopping.shoppingapp.viewcallbacks;

import com.project.shopping.shoppingapp.model.Address;

/**
 * Created by mohan on 21/05/17.
 */

public interface CartViewCallback {

    public interface View extends BaseViewCallback{

        void setAddress(Address address);
        void showAddressActivity(Address address);
    }

}
