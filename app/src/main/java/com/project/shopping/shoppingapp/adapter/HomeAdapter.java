package com.project.shopping.shoppingapp.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;
import com.project.shopping.shoppingapp.R;
import com.project.shopping.shoppingapp.viewcallbacks.MainViewModel;

import java.util.List;

/**
 * Created by mohan on 21/05/17.
 */

public class HomeAdapter<T> extends BaseAdapter {

    List<T> listItem;
    MainViewModel mainViewModel;




    public HomeAdapter(List<T> listItem, MainViewModel mainViewModel) {
        this.listItem = listItem;
        this.mainViewModel=mainViewModel;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(
                layoutInflater, viewType, parent, false);
        return new HomeViewHolder(binding);
    }

    @Override
    public int getItemViewType(int position) {

        return R.layout.adapter_vegetables;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        HomeViewHolder homeViewHolder= (HomeViewHolder) holder;
        homeViewHolder.bindViews(listItem.get(position),mainViewModel);

    }

    @Override
    public void updateList(List listItem) {

        this.listItem=listItem;
    }

    public static class HomeViewHolder<T> extends RecyclerView.ViewHolder{


        ViewDataBinding viewDataBinding;
        MainViewModel mainViewModel;

        public HomeViewHolder(ViewDataBinding itemView) {
            super(itemView.getRoot());
            this.viewDataBinding=itemView;
        }

        public void bindViews(T product,MainViewModel mainViewModel){

            viewDataBinding.setVariable(BR.product,product);
            viewDataBinding.setVariable(BR.handler,mainViewModel);
            viewDataBinding.executePendingBindings();
        }


    }




    @Override
    public int getItemCount() {
        return listItem.size();
    }
}
