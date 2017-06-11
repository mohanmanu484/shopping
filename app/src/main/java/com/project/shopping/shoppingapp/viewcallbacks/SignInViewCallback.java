package com.project.shopping.shoppingapp.viewcallbacks;

/**
 * Created by mohan on 21/05/17.
 */

public interface SignInViewCallback {

    public interface View extends BaseViewCallback{

        void showRegisterFragment();

        void finish();
    }

}
