package com.project.shopping.shoppingapp.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.shopping.shoppingapp.R;
import com.project.shopping.shoppingapp.ViewModel.CartViewModel;
import com.project.shopping.shoppingapp.activity.AddresssActivity;
import com.project.shopping.shoppingapp.activity.CheckoutActivity;
import com.project.shopping.shoppingapp.databinding.FragmentCheckoutBinding;
import com.project.shopping.shoppingapp.model.Address;
import com.project.shopping.shoppingapp.model.CartItem;
import com.project.shopping.shoppingapp.model.CheckoutObj;
import com.project.shopping.shoppingapp.viewcallbacks.CartViewCallback;
import com.project.shopping.shoppingapp.viewcallbacks.MainViewModel;

/**
 * Created by mohan on 22/05/17.
 */

public class CartFragment extends BaseFragment implements CartViewCallback.View  {

    CartViewModel cartViewModel=new CartViewModel();

    FragmentCheckoutBinding fragmentCheckoutBinding;

    CartItem cartItem=null;

    public static final String CART_ITEM="cart_item";

    public static CartFragment getInstance(CartItem cartItem){

        Bundle bundle=new Bundle();
        bundle.putParcelable(CART_ITEM,cartItem);
        CartFragment cartFragment=new CartFragment();
        cartFragment.setArguments(bundle);
        return cartFragment;


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            cartItem=getArguments().getParcelable(CART_ITEM);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        fragmentCheckoutBinding=DataBindingUtil.inflate(inflater, R.layout.fragment_checkout,container,false);
        fragmentCheckoutBinding.setModel(cartViewModel);
        fragmentCheckoutBinding.setAddress(new Address());
        CheckoutActivity checkoutActivity= (CheckoutActivity) getActivity();
        fragmentCheckoutBinding.toolbar.setTitle(cartItem!=null?"Checkout":"My cart");
        checkoutActivity.setSupportActionBar(fragmentCheckoutBinding.toolbar);
        checkoutActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        return fragmentCheckoutBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        cartViewModel.fetchAddressInfo();
    }

    @Override
    public Context getActivityContext() {
        return getActivity();
    }

    @Override
    public Activity getActivityInstance() {
        return getActivity();
    }

    @Override
    public MainViewModel getViewModel() {
        return cartViewModel;
    }

    @Override
    public void setAddress(Address address) {
        fragmentCheckoutBinding.setAddress(address);
    }

    @Override
    public void showAddressActivity(Address address) {

        //ModuleMaster.navigateToAddressActivity(getActivity(),address);
        Intent intent=new Intent(getActivity(), AddresssActivity.class);
        intent.putExtra(AddressFragment.EXTRA_ADDRESS,address);
        //activity.startActivityForResult(intent,100);
        startActivityForResult(intent,100);


    }

    @Override
    public void showPaymentSelectFragment(CheckoutObj checkoutObj) {
        CheckoutActivity checkoutActivity= (CheckoutActivity) getActivity();
        checkoutActivity.showPaymentSelectFragment(checkoutObj);
    }

    @Override
    public CartItem getSingleProduct() {
        return cartItem;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //if(requestCode==100  && requestCode==RESULT_OK){

           // setAddress((Address) data.getExtras().get(AddressFragment.EXTRA_ADDRESS));
            cartViewModel.fetchAddressInfo();
            cartViewModel.fetchCartItems();
       // }


    }
}
