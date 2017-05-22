package com.project.shopping.shoppingapp.fragments;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.shopping.shoppingapp.R;
import com.project.shopping.shoppingapp.ViewModel.AddressViewModel;
import com.project.shopping.shoppingapp.activity.AddresssActivity;
import com.project.shopping.shoppingapp.databinding.FragmentAddressBinding;
import com.project.shopping.shoppingapp.model.Address;
import com.project.shopping.shoppingapp.viewcallbacks.AddressCallback;
import com.project.shopping.shoppingapp.viewcallbacks.MainViewModel;

/**
 * Created by mohan on 22/05/17.
 */

public class AddressFragment extends BaseFragment implements AddressCallback.View {

    AddressViewModel addressViewModel=new AddressViewModel();
    public static final String EXTRA_ADDRESS="address";
    private Address address;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       Bundle bundle= getActivity().getIntent().getExtras();

        if(bundle!=null){
           address= bundle.getParcelable(EXTRA_ADDRESS);
        }


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        FragmentAddressBinding fragmentAddressBinding=DataBindingUtil.inflate(inflater, R.layout.fragment_address,container,false);
        fragmentAddressBinding.setAddress(address);
        fragmentAddressBinding.setModel(addressViewModel);

        AddresssActivity addresssActivity= (AddresssActivity) getActivity();
        addresssActivity.setSupportActionBar(fragmentAddressBinding.toolbar);
        addresssActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        return fragmentAddressBinding.getRoot();
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
        return addressViewModel;
    }

    @Override
    public void showAddress(Address address) {

        AddresssActivity  addresssActivity= (AddresssActivity) getActivity();
        addresssActivity.finishActivityWithAddress(address);


    }
}
