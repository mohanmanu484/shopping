package com.project.shopping.shoppingapp.Utility;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.project.shopping.shoppingapp.PreLoginActivity;
import com.project.shopping.shoppingapp.activity.AddresssActivity;
import com.project.shopping.shoppingapp.activity.CheckoutActivity;
import com.project.shopping.shoppingapp.activity.HomeActivity;
import com.project.shopping.shoppingapp.fragments.AddressFragment;
import com.project.shopping.shoppingapp.model.Address;

/**
 * Created by mohan on 21/05/17.
 */

public class ModuleMaster {

    public static void navigateToHomeScreen(Context context){
        Intent intent=new Intent(context, HomeActivity.class);
        context.startActivity(intent);
    }


    public static void navigateToPreloginActivity(Context context) {
        Intent intent=new Intent(context, PreLoginActivity.class);
        context.startActivity(intent);

    }

    public static void navigateToCart(Context context) {

        Intent intent=new Intent(context, CheckoutActivity.class);
        context.startActivity(intent);
    }

    public static void navigateToAddressActivity(Activity activity, Address address) {

        Intent intent=new Intent(activity, AddresssActivity.class);
        intent.putExtra(AddressFragment.EXTRA_ADDRESS,address);
        activity.startActivityForResult(intent,100);

    }
}
