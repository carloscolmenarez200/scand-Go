package com.cencosud.scanandgo.order_history.presentation.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cencosud.scan_commons.shopping_list.domain.model.TagsShopping;
import com.cencosud.scanandgo.car.utils.TextUtils;
import com.cencosud.scanandgo.order_history.domain.model.Transaction;
import com.cencosud.scanandgo.R;
import com.cencosud.scanandgo.checkout.domain.model.ProductDiscount;
import com.cencosud.scanandgo.databinding.ItemShoppingListHistoryBinding;
import com.cencosud.scanandgo.order_history.utils.DateUtils;
import com.core.presentation.adapter.BaseListAdapter;
import com.core.presentation.adapter.OnItemClickListener;
import com.core.presentation.adapter.holder.BaseViewHolder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by joseamaro on 12-07-18.
 */

public class OrderHistoryAdapter extends BaseListAdapter<Transaction, OrderHistoryAdapter.OrderHistoryHolder> {

    private OnItemClickListener<Transaction> onItemClickListener;

    @Override
    protected RecyclerView.ViewHolder createViewHolder(int viewType, View v) {
        return new OrderHistoryAdapter.OrderHistoryHolder(v);
    }

    public void setOnItemClickListener(OnItemClickListener<Transaction> onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    protected int getLayoutIdByType(int viewType) {
        return R.layout.item_shopping_list_history;
    }

    private int totalQuantity(List<ProductDiscount> products) {

        int total = 0;
        for (ProductDiscount item : products) {

            total += item.quantity;
        }
        return total;
    }

    class OrderHistoryHolder extends BaseViewHolder<Transaction, ItemShoppingListHistoryBinding> {

        View view;

        public OrderHistoryHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            this.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(getAdapterPosition(),getItem(getAdapterPosition()));
                }
            });
        }

        @Override
        public void bind(int position, final Transaction item) {

            binder.tvDayOfPurchase.setVisibility(item.header?View.VISIBLE:View.GONE);

            binder.tvQuantityProduct.setText(totalQuantity(item.productDiscounts) + " Producto(s)");
            binder.tvTotalAmount.setText(TextUtils.decimalFormat(item.totalAmount));
            binder.tvDayOfPurchase.setText(DateUtils.formaterToString("dd MMMM yyyy",item.date));
            binder.tvNameStore.setText(item.storeName);
            binder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(getAdapterPosition(),getItem(getAdapterPosition()));
                }
            });
        }
    }

}
