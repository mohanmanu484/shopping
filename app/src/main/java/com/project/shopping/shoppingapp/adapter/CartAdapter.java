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
 * Created by mohan on 22/05/17.
 */

public class CartAdapter<T> extends BaseAdapter {

    List<T> listItem;
    MainViewModel mainViewModel;

    public CartAdapter(List<T> listItem,MainViewModel mainViewModel) {
        this.listItem = listItem;
        this.mainViewModel=mainViewModel;
    }

    @Override
    public void updateList(List listItem) {
        this.listItem=listItem;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(
                layoutInflater, viewType, parent, false);
        return new CartViewHolder<>(binding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        CartViewHolder cartViewHolder= (CartViewHolder) holder;
        cartViewHolder.bindViews(listItem.get(position),mainViewModel);

    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.adapter_cart_item;
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public static class CartViewHolder<T> extends RecyclerView.ViewHolder{

        ViewDataBinding viewDataBinding;

        public CartViewHolder(ViewDataBinding itemView) {
            super(itemView.getRoot());
            this.viewDataBinding=itemView;
        }

        public void bindViews(T cartItem,MainViewModel mainViewModel){

            viewDataBinding.setVariable(BR.item,cartItem);
            viewDataBinding.setVariable(BR.handler,mainViewModel);
            viewDataBinding.executePendingBindings();
        }


    }
}
