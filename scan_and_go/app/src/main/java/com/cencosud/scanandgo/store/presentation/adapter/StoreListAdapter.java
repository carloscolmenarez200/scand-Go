package com.cencosud.scanandgo.store.presentation.adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.cencosud.scan_commons.store.domain.model.Store;
import com.cencosud.scanandgo.R;
import com.cencosud.scanandgo.databinding.ItemStoreListBinding;
import com.core.presentation.adapter.BaseListAdapter;
import com.core.presentation.adapter.holder.BaseViewHolder;
import com.google.android.gms.maps.model.LatLng;

import java.util.Locale;

/**
 * Created by joseamaro on 27-06-18.
 */

public class StoreListAdapter extends BaseListAdapter<Store, StoreListAdapter.LocaleHolder> {

    String uri = "";

    @Override
    protected RecyclerView.ViewHolder createViewHolder(int viewType, View v) {
        return new StoreListAdapter.LocaleHolder(v);
    }

    @Override
    protected int getLayoutIdByType(int viewType) {
        return R.layout.item_store_list;
    }

    class LocaleHolder extends BaseViewHolder<Store, ItemStoreListBinding> {

        View view;

        public LocaleHolder(View itemView) {
            super(itemView);
            this.view = itemView;
        }

        @Override
        public void bind(int position, final Store item) {


            binder.tvHowToGet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    howToGet(item, getContext());
                }
            });

            if (position != 0) {
                binder.tvNearestStore.setVisibility(View.GONE);
                binder.nameStore.setText(item.name);
                binder.tvAddressStore.setText(item.address);
            } else
                binder.tvNearestStore.setVisibility(View.VISIBLE);
                binder.nameStore.setText(item.name);
                binder.tvAddressStore.setText(item.address);

        }

    }

    public void howToGet(Store store, Context context) {

        LatLng to = new LatLng(Double.parseDouble(store.latitude), Double.parseDouble(store.longitude));

       /*String uri = String.format(Locale.ENGLISH, "geo:0,0?q=") +
               Uri.encode(String.format("%s@%f,%f", store.name, to.latitude,
                       to.longitude), "UTF-8");
       Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
       getContext().startActivity(Intent.createChooser(intent, "Select an application"));*/

        try {
            uri = String.format(Locale.ENGLISH, "geo:" + to.latitude + "," + to.longitude) + "?q=" + to.latitude + "," + to.longitude + " (" + store.name + ")";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            try {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr=" + to.latitude + "," + to.longitude));
                context.startActivity(Intent.createChooser(intent, "Selecciona  aplicación"));
            } catch (ActivityNotFoundException innerEx) {
                //getSnackbar(getResources().getString(R.string.text_store__not_available), Snackbar.LENGTH_LONG).show();
            }
        }
    }
}
