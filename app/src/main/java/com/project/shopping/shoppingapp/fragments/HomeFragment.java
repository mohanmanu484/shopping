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
import com.project.shopping.shoppingapp.ViewModel.HomeViewModel;
import com.project.shopping.shoppingapp.activity.HomeActivity;
import com.project.shopping.shoppingapp.databinding.FragmentHomeBinding;
import com.project.shopping.shoppingapp.model.Product;
import com.project.shopping.shoppingapp.viewcallbacks.HomeViewCallback;
import com.project.shopping.shoppingapp.viewcallbacks.MainViewModel;

/**
 * Created by mohan on 21/05/17.
 */

public class HomeFragment extends BaseFragment implements HomeViewCallback.View{


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        FragmentHomeBinding fragmentDataBindingUtil=DataBindingUtil.inflate(inflater, R.layout.fragment_home,container,false);
        fragmentDataBindingUtil.setModel(homeViewModel);
       // homeViewModel.fetchProducts();

        return fragmentDataBindingUtil.getRoot();
    }

    HomeViewModel homeViewModel=new HomeViewModel();

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
        return homeViewModel;
    }

    @Override
    public void showDetailsFragment(Product product) {
        HomeActivity homeActivity= (HomeActivity) getActivity();
        homeActivity.showDetailsFragment(product);
    }

    @Override
    public void finish() {
        getActivity().finish();
    }
}
