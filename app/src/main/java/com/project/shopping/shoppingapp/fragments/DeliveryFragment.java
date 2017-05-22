package com.project.shopping.shoppingapp.fragments;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.project.shopping.shoppingapp.BR;
import com.project.shopping.shoppingapp.R;
import com.project.shopping.shoppingapp.ViewModel.DeliveryViewModel;
import com.project.shopping.shoppingapp.viewcallbacks.DeliveryViewCallback;
import com.project.shopping.shoppingapp.viewcallbacks.MainViewModel;

/**
 * Created by mohan on 22/05/17.
 */

public class DeliveryFragment extends BaseFragment implements DeliveryViewCallback.View {



    DeliveryViewModel deliveryViewModel=new DeliveryViewModel();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        ViewDataBinding viewDataBinding= DataBindingUtil.inflate(inflater, R.layout.fragment_delivery,container,false);
        viewDataBinding.setVariable(BR.model,deliveryViewModel);
        deliveryViewModel.showAddress();
        return viewDataBinding.getRoot();
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
        return deliveryViewModel;
    }
}
