package com.cencosud.scanandgo.car.presentation.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cencosud.scan_commons.product.domain.model.Product;
import com.cencosud.scan_commons.product.domain.model.Store;
import com.cencosud.scanandgo.R;
import com.cencosud.scanandgo.car.utils.TextUtils;
import com.cencosud.scanandgo.databinding.CarItemBinding;
import com.core.presentation.adapter.BaseListAdapter;
import com.core.presentation.adapter.holder.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joseamaro on 03-05-18.
 */

public class CarAdapter extends BaseListAdapter<Product, CarAdapter.CarHolder> {

    private OnChangePickerListener listener;


    @Override
    protected RecyclerView.ViewHolder createViewHolder(int viewType, View v) {
        return new CarHolder(v);
    }

    @Override
    protected int getLayoutIdByType(int viewType) {
        return R.layout.car_item;
    }

    public int total() {

        int total = 0;
        for (Product item : list) {
            total = total + item.productQuantity;
        }
        return total;
    }

    public double priceTotal() {

        double priceTotal = 0;

        for (Product item : list) {
            priceTotal = priceTotal + item.amount;
        }
        return priceTotal;
    }

    public void setListener(OnChangePickerListener listener) {
        this.listener = listener;
        listener.onChangePickerListener();
    }


    class CarHolder extends BaseViewHolder<Product, CarItemBinding> {

        View view;

        public CarHolder(View itemView) {
            super(itemView);
            this.view = itemView;
        }

        @Override
        public void bind(int position, final Product item) {

            Glide.with(view.getContext())
                    .load(item.imageUrl)
                    .centerCrop()
                    .error(R.drawable.noimage)
                    .placeholder(R.drawable.noimage)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .priority(Priority.HIGH)
                    .into(binder.ivItemImage);

            if (item.isPesable) {
                binder.btnAdd.setEnabled(false);
                binder.btnRemove.setEnabled(false);
                binder.tvTextPrice.setText(TextUtils.decimalFormat(item.amount));


            } else {

                binder.tvTextPrice.setText(TextUtils.decimalFormat(Double.parseDouble(item.stores.get(item.stores.keySet().toArray()[0]).price)));

                if (total() == 30)
                    binder.btnAdd.setEnabled(false);
                else
                    binder.btnAdd.setEnabled(true);

                if (item.productQuantity == 1)
                    binder.btnRemove.setEnabled(false);
                else
                    binder.btnRemove.setEnabled(true);
            }
            binder.tvItemName.setText(item.fullName);
            binder.tvTextQuantityProduct.setText(String.valueOf(item.productQuantity));
            binder.btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.productQuantity = item.productQuantity + 1;
                    item.quantity = item.productQuantity;

                    if (!item.stores.isEmpty())
                        item.amount = Double.parseDouble(item.stores.get(item.stores.keySet().toArray()[0]).price) * item.productQuantity;

                    binder.tvTextQuantityProduct.setText(String.valueOf(item.productQuantity));
                    listener.onChangePickerListener();

                    if (total() == 30) {
                        binder.btnAdd.setEnabled(false);
                        notifyDataSetChanged();
                    } else {
                        binder.btnRemove.setEnabled(true);
                    }

                }
            });

            binder.btnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    item.productQuantity = item.productQuantity - 1;
                    item.quantity = item.productQuantity;

                    if (!item.stores.isEmpty())
                        item.amount = Double.parseDouble(item.stores.get(item.stores.keySet().toArray()[0]).price) * item.productQuantity;


                    binder.tvTextQuantityProduct.setText(String.valueOf(item.productQuantity));
                    listener.onChangePickerListener();

                    if (item.productQuantity == 1) {
                        binder.btnRemove.setEnabled(false);

                    } else {
                        binder.btnAdd.setEnabled(true);
                    }

                    if (total() == 29) {
                        notifyDataSetChanged();
                    }

                }
            });

        }

    }

    public interface OnChangePickerListener {
        void onChangePickerListener();
    }

}
