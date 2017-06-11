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
import com.project.shopping.shoppingapp.ViewModel.SignupModel;
import com.project.shopping.shoppingapp.databinding.FragmentSignupBinding;
import com.project.shopping.shoppingapp.viewcallbacks.MainViewModel;
import com.project.shopping.shoppingapp.viewcallbacks.SignUpViewCallback;

/**
 * Created by mohan on 21/05/17.
 */

public class SignUpFragment extends BaseFragment implements SignUpViewCallback.View {


    SignupModel signupModel=new SignupModel();

    /*@Override
    public void onStart() {
        super.onStart();
        signupModel.onAtttach(this);
    }*/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

       FragmentSignupBinding fragmentSignupBinding= DataBindingUtil.inflate(inflater, R.layout.fragment_signup,container,false);
        fragmentSignupBinding.setModel(signupModel);

        return fragmentSignupBinding.getRoot();
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
        return signupModel;
    }

    @Override
    public void showInFragment() {
        PreLoginActivity preLoginActivity= (PreLoginActivity) getActivity();
        preLoginActivity.showInFragment();
    }
}
