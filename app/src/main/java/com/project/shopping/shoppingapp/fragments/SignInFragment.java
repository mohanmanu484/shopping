package com.project.shopping.shoppingapp.fragments;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.shopping.shoppingapp.PreLoginActivity;
import com.project.shopping.shoppingapp.R;
import com.project.shopping.shoppingapp.ViewModel.SigninModel;
import com.project.shopping.shoppingapp.databinding.FragmentSigninBinding;
import com.project.shopping.shoppingapp.viewcallbacks.MainViewModel;
import com.project.shopping.shoppingapp.viewcallbacks.SignInViewCallback;

/**
 * Created by mohan on 21/05/17.
 */

public class SignInFragment extends BaseFragment implements SignInViewCallback.View {


    SigninModel signinModel=new SigninModel();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        FragmentSigninBinding fragmentSigninBinding=DataBindingUtil.inflate(inflater, R.layout.fragment_signin,container,false);
        fragmentSigninBinding.setModel(signinModel);
        return fragmentSigninBinding.getRoot();
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
    public void showRegisterFragment() {
        PreLoginActivity preLoginActivity= (PreLoginActivity) getActivity();
        preLoginActivity.showRegisterFragment();
    }

    @Override
    public void finish() {
        getActivity().finish();
    }

    @Override
    public MainViewModel getViewModel() {
        return signinModel;
    }
}
