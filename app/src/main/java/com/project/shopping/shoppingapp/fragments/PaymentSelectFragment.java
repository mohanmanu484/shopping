package com.project.shopping.shoppingapp.fragments;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.project.shopping.shoppingapp.R;
import com.project.shopping.shoppingapp.ViewModel.PaymentSelectViewModel;
import com.project.shopping.shoppingapp.activity.CheckoutActivity;
import com.project.shopping.shoppingapp.databinding.FragmentPaymentSelectorBinding;
import com.project.shopping.shoppingapp.model.CheckoutObj;
import com.project.shopping.shoppingapp.viewcallbacks.MainViewModel;
import com.project.shopping.shoppingapp.viewcallbacks.PaymentSelectCallback;
import com.razorpay.Checkout;

/**
 * Created by mohan on 22/05/17.
 */

public class PaymentSelectFragment extends BaseFragment implements PaymentSelectCallback.View {

    PaymentSelectViewModel paymentSelectViewModel=new PaymentSelectViewModel();
    private CheckoutObj checkoutObj;
    public static final String EXTRA_CHECKOUT="checkout";

    FragmentPaymentSelectorBinding fragmentPaymentSelectorBinding;



    public static PaymentSelectFragment getInstance(CheckoutObj checkoutObj){

        PaymentSelectFragment paymentSelectFragment=new PaymentSelectFragment();
        Bundle bundle=new Bundle();
        bundle.putParcelable(EXTRA_CHECKOUT,checkoutObj);
        paymentSelectFragment.setArguments(bundle);
        return paymentSelectFragment;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        checkoutObj=getArguments().getParcelable(EXTRA_CHECKOUT);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        fragmentPaymentSelectorBinding=DataBindingUtil.inflate(inflater, R.layout.fragment_payment_selector,container,false);
        fragmentPaymentSelectorBinding.setModel(paymentSelectViewModel);
        fragmentPaymentSelectorBinding.setData(checkoutObj);
        CheckoutActivity checkoutActivity= (CheckoutActivity) getActivity();
        checkoutActivity.setSupportActionBar(fragmentPaymentSelectorBinding.toolbar);
        checkoutActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        return fragmentPaymentSelectorBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Checkout.preload(getActivity().getApplicationContext());
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
        return paymentSelectViewModel;
    }

    @Override
    public RadioGroup getPaymentGruop() {
        return fragmentPaymentSelectorBinding.radioGroup;
    }
}
