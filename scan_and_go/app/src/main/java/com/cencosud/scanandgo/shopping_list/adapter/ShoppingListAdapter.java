package com.cencosud.scanandgo.shopping_list.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cencosud.scan_commons.shopping_list.domain.model.TagsShopping;
import com.cencosud.scanandgo.R;
import com.cencosud.scanandgo.databinding.ItemShoppingListBinding;
import com.core.presentation.adapter.BaseListAdapter;
import com.core.presentation.adapter.OnItemClickListener;
import com.core.presentation.adapter.holder.BaseViewHolder;

/**
 * Created by carlos on 18-07-18.
 */

public class ShoppingListAdapter extends BaseListAdapter<TagsShopping, ShoppingListAdapter.ShoppingListHolder> {

    private OnItemClickListener<TagsShopping> onItemClickListener;

    @Override
    protected RecyclerView.ViewHolder createViewHolder(int viewType, View v) {
        return new ShoppingListAdapter.ShoppingListHolder(v);
    }

    @Override
    protected int getLayoutIdByType(int viewType) {
        return R.layout.item_shopping_list;
    }

    public void setOnItemClickListener(OnItemClickListener<TagsShopping> onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    class ShoppingListHolder extends BaseViewHolder<TagsShopping, ItemShoppingListBinding> {

        View view;

        public ShoppingListHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            this.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedItem();
                }
            });
        }

        @Override
        public void bind(int position, final TagsShopping item) {

            binder.tvShoppingList.setEnabled(item.checked);
            binder.tvShoppingList.setText(item.name);
            binder.chShoppingList.setChecked(item.checked);

            binder.chShoppingList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedItem();
                }
            });
        }

        private void selectedItem(){
            TagsShopping tagsShopping = getItem(getAdapterPosition());
            tagsShopping.checked = !tagsShopping.checked;
            onItemClickListener.onItemClick(getAdapterPosition(),getItem(getAdapterPosition()));
            notifyDataSetChanged();
        }

    }

}
