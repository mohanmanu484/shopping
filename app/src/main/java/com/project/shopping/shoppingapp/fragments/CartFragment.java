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
import com.project.shopping.shoppingapp.Utility.ModuleMaster;
import com.project.shopping.shoppingapp.ViewModel.CartViewModel;
import com.project.shopping.shoppingapp.databinding.FragmentCheckoutBinding;
import com.project.shopping.shoppingapp.model.Address;
import com.project.shopping.shoppingapp.viewcallbacks.CartViewCallback;
import com.project.shopping.shoppingapp.viewcallbacks.MainViewModel;

import static android.app.Activity.RESULT_OK;

/**
 * Created by mohan on 22/05/17.
 */

public class CartFragment extends BaseFragment implements CartViewCallback.View {

    CartViewModel cartViewModel=new CartViewModel();

    FragmentCheckoutBinding fragmentCheckoutBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        fragmentCheckoutBinding=DataBindingUtil.inflate(inflater, R.layout.fragment_checkout,container,false);
        fragmentCheckoutBinding.setModel(cartViewModel);
        fragmentCheckoutBinding.setAddress(new Address());
        return fragmentCheckoutBinding.getRoot();
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

        ModuleMaster.navigateToAddressActivity(getActivity(),address);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==100  && requestCode==RESULT_OK){
            cartViewModel.fetchAddressInfo();
            setAddress((Address) data.getExtras().get(AddressFragment.EXTRA_ADDRESS));
        }


    }
}
