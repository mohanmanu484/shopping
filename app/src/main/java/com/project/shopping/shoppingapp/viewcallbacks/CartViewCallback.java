package com.project.shopping.shoppingapp.viewcallbacks;

import com.project.shopping.shoppingapp.model.Address;
import com.project.shopping.shoppingapp.model.CartItem;
import com.project.shopping.shoppingapp.model.CheckoutObj;

/**
 * Created by mohan on 21/05/17.
 */

public interface CartViewCallback {

    public interface View extends BaseViewCallback{

        void setAddress(Address address);
        void showAddressActivity(Address address);

        void showPaymentSelectFragment(CheckoutObj checkoutObj);

        CartItem getSingleProduct();
    }

}
