package com.cencosud.scanandgo.car.presentation.activity;

import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;
import android.view.View;
import com.cencosud.scan_commons.databinding.ToolbarBinding;
import com.cencosud.scan_commons.product.domain.model.Product;
import com.cencosud.scanandgo.R;
import com.cencosud.scanandgo.car.di.component.DaggerCarComponent;
import com.cencosud.scanandgo.car.presentation.adapter.CarAdapter;
import com.cencosud.scanandgo.car.presentation.contract.CarContract;
import com.cencosud.scanandgo.car.utils.SwipeToDeleteCallback;
import com.cencosud.scanandgo.car.utils.TextUtils;
import com.cencosud.scanandgo.checkout.presentation.activity.CheckoutActivity;
import com.cencosud.scanandgo.databinding.ActivityCarBinding;
import com.core.presentation.activity.BaseActivity;
import com.core.util.DialogHelper;
import java.util.List;
import javax.inject.Inject;

public class CarActivity extends BaseActivity<ActivityCarBinding> implements CarContract.View, CarAdapter.OnChangePickerListener {


    @Inject
    CarAdapter adapter;

    @Inject
    CarContract.Presenter presenter;

    @Override
    protected void initView() {

        ToolbarBinding toolbarBinding = DataBindingUtil.bind(binder.toolbarIncl.toolbar);
        setSupportActionBar(binder.toolbarIncl.toolbar);
        presenter.initialize(this);
        binder.tvPrice.setText(TextUtils.decimalFormat(adapter.priceTotal()));
        toolbarBinding.tvName.setText(R.string.tv_text_toolbar);
        toolbarBinding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binder.btnPayCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(CheckoutActivity.class);
                presenter.sendActionCar("Pagar");
            }
        });

    }

    @Override
    protected void injectDependencies() {
        DaggerCarComponent.builder().build().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_car;
    }

    @Override
    public int getMenuId() {
        return R.menu.delete_car_menu;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.icon_delete_toolbar) {
            new DialogHelper().attachContext(this).showConfirmationDialog(
                    R.string.delete_text_product,
                    R.string.delete_product_confirmation,
                    R.string.delete_product,
                    R.string.cancel_product,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            presenter.deleteProducts();
                        }
                    });
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgress(boolean show) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void displayProducts(List<Product> items) {

        adapter.setList(items);
        adapter.setListener(this);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback() {
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                presenter.deleteProduct(viewHolder.getAdapterPosition(), adapter.getItem(viewHolder.getAdapterPosition()));
            }
        });
        itemTouchHelper.attachToRecyclerView(binder.recyclerView);
        binder.recyclerView.setAdapter(adapter);
    }

    @Override
    public void deleteProduct(int position) {

        adapter.remove(position);
        onChangePickerListener();
        adapter.notifyDataSetChanged();
        if (adapter.getList().size() == 0) {
            finish();
        }
    }

    @Override
    public void deleteProducts() {

        adapter.getList().removeAll(adapter.getList());
        adapter.notifyDataSetChanged();
        finish();
    }

    @Override
    public void onChangePickerListener() {
        binder.tvArticles.setText("Tienes " + String.valueOf(adapter.total()) + " de ");
        binder.tvPrice.setText(TextUtils.decimalFormat(adapter.priceTotal()));
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.setProducts(adapter.getList());
    }

}
