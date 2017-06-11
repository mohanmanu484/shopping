package com.project.shopping.shoppingapp.viewcallbacks;

import com.project.shopping.shoppingapp.model.Product;

/**
 * Created by mohan on 21/05/17.
 */

public interface HomeViewCallback {

    public interface View extends BaseViewCallback{

        void showDetailsFragment(Product product);

        void finish();
    }

}
