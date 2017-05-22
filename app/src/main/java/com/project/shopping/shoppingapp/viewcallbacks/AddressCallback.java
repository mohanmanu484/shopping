package com.project.shopping.shoppingapp.viewcallbacks;

import com.project.shopping.shoppingapp.model.Address;

/**
 * Created by mohan on 21/05/17.
 */

public interface AddressCallback {

    public interface View extends BaseViewCallback{


        void showAddress(Address address);
    }

}
