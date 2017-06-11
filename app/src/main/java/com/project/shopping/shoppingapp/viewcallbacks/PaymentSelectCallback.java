package com.project.shopping.shoppingapp.viewcallbacks;

import android.widget.RadioGroup;

/**
 * Created by mohan on 21/05/17.
 */

public interface PaymentSelectCallback {

    public interface View extends BaseViewCallback{

        RadioGroup getPaymentGruop();

    }

}
