package com.project.shopping.shoppingapp.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.shopping.shoppingapp.R;
import com.project.shopping.shoppingapp.ViewModel.DetailsViewModel;
import com.project.shopping.shoppingapp.activity.HomeActivity;
import com.project.shopping.shoppingapp.databinding.FragmentDetailsBinding;
import com.project.shopping.shoppingapp.model.Product;
import com.project.shopping.shoppingapp.viewcallbacks.DetailsViewCallback;
import com.project.shopping.shoppingapp.viewcallbacks.MainViewModel;

/**
 * Created by mohan on 22/05/17.
 */

public class DetailsFragment extends BaseFragment implements DetailsViewCallback.View{

    DetailsViewModel detailsViewModel=new DetailsViewModel();
    public static final String EXTRA_PRODUCT="product";

    private Product product;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        // 8197728485


        FragmentDetailsBinding fragmentDetailsBinding=DataBindingUtil.inflate(inflater, R.layout.fragment_details,container,false);

        HomeActivity homeActivity= (HomeActivity) getActivity();

        product=getArguments().getParcelable(EXTRA_PRODUCT);
        fragmentDetailsBinding.setModel(detailsViewModel);
        fragmentDetailsBinding.setProduct(product);
        fragmentDetailsBinding.toolbarLayout.setTitle(product.getName());
        homeActivity.setSupportActionBar(fragmentDetailsBinding.toolbar);
        homeActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setHasOptionsMenu(true);

        return fragmentDetailsBinding.getRoot();
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
        return detailsViewModel;
    }

    public static Fragment getInstance(Product product) {

        DetailsFragment detailsFragment=new DetailsFragment();
        Bundle bundle=new Bundle();
        bundle.putParcelable(EXTRA_PRODUCT,product);
        detailsFragment.setArguments(bundle);
        return detailsFragment;
    }

    @Override
    public Product getProduct() {
        return product;
    }
}
