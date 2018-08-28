package com.cencosud.scanandgo.order_history.presentation.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cencosud.scanandgo.car.utils.TextUtils;
import com.cencosud.scanandgo.databinding.ItemPromotionBinding;
import com.cencosud.scanandgo.R;
import com.cencosud.scanandgo.checkout.domain.model.ProductDiscount;
import com.cencosud.scanandgo.checkout.domain.model.Promotion;
import com.cencosud.scanandgo.databinding.ItemPurchaseDetailsBinding;
import com.core.presentation.adapter.BaseListAdapter;
import com.core.presentation.adapter.holder.BaseViewHolder;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by joseamaro on 12-07-18.
 */

public class OrderDetailsAdapter extends BaseListAdapter<ProductDiscount, OrderDetailsAdapter.LocaleHolder> {

    @Override
    protected RecyclerView.ViewHolder createViewHolder(int viewType, View v) {
        return new OrderDetailsAdapter.LocaleHolder(v);
    }

    @Override
    protected int getLayoutIdByType(int viewType) {
        return R.layout.item_purchase_details;
    }

    class LocaleHolder extends BaseViewHolder<ProductDiscount, ItemPurchaseDetailsBinding> {

        View view;

        public LocaleHolder(View itemView) {
            super(itemView);
            this.view = itemView;
        }

        @Override
        public void bind(int position, final ProductDiscount item) {

            Glide.with(view.getContext())
                    .load(item.imageUrl)
                    .centerCrop()
                    .error(R.drawable.noimage)
                    .placeholder(R.drawable.noimage)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .priority(Priority.HIGH)
                    .into(binder.ivItemImage);

            binder.tvTotal.setText(TextUtils.decimalFormat(item.total));

            if(item.promotions!=null && !item.promotions.isEmpty()){

                for(Promotion promotion: item.promotions){

                    if(promotion.discount !=0){

                        final ItemPromotionBinding binderPromotion = DataBindingUtil.inflate(LayoutInflater.from(view.getContext()), R.layout.item_promotion, null, false);
                        binderPromotion.tvDiscount.setText(TextUtils.decimalFormatNegative(promotion.discount));
                        binderPromotion.tvTextDiscount.setText(promotion.promotion);
                        binder.lyPromotions.addView(binderPromotion.getRoot());

                    }else {
                        binder.tvTotalWithDiscount.setVisibility(view.GONE);
                    }
                }
                binder.tvTotalWithDiscount.setText(TextUtils.decimalFormat(item.totalWithDiscount));
            }else{
                binder.tvTotalWithDiscount.setVisibility(view.GONE);
            }

            if(item.isPesable){
                binder.tvQuantityProduct.setText("1 Producto(s)");
            }else{
                binder.tvQuantityProduct.setText(String.valueOf((int) Math.floor(item.quantity)) + " Producto(s)");
            }

        }
    }
}
