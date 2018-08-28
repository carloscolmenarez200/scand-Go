package com.cencosud.scanandgo.menu.presentation.activity;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.annotation.StringRes;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.cencosud.scan_commons.user.domain.model.User;
import com.cencosud.scanandgo.order_history.presentation.fragment.OrderHistoryFragment;
import com.cencosud.scanandgo.R;
import com.cencosud.scanandgo.car.presentation.activity.CarActivity;
import com.cencosud.scanandgo.databinding.ActivityDrawerMenuBinding;
import com.cencosud.scanandgo.databinding.MenuHeaderBinding;
import com.cencosud.scanandgo.login_register.presentation.activity.LoginRegisterActivity;
import com.cencosud.scanandgo.menu.di.DaggerDrawerMenuComponent;
import com.cencosud.scanandgo.menu.presentation.DrawerMenuContract;
import com.cencosud.scanandgo.menu.presentation.fragment.ContentFragment;
import com.cencosud.scanandgo.onboarding.presentation.activity.OnBoardingActivity;
import com.cencosud.scanandgo.profile.presentation.activity.ProfileActivity;
import com.cencosud.scanandgo.scanner.presentation.OnSaveProductListener;
import com.cencosud.scanandgo.scanner.presentation.fragment.ScannerFragment;
import com.cencosud.scanandgo.shopping_list.presentation.OnDeleteShoppingList;
import com.cencosud.scanandgo.shopping_list.presentation.fragment.ShoppingListFragment;
import com.cencosud.scanandgo.store.presentation.fragment.StoreFragment;
import com.cencosud.scanandgo.terms_and_conditions.presentation.fragment.TermsAndConditionsFragment;
import com.cencosud.scanandgo.wallet.presentation.fragment.CardsFragment;
import com.cencosud.scanandgo.wallet.utils.OnBackPressed;
import com.core.presentation.activity.BaseNavigationDrawerActivity;
import java.util.List;
import javax.inject.Inject;


/**
 * Created by consultor on 10-05-18.
 */

public class DrawerMenuActivity extends BaseNavigationDrawerActivity<ActivityDrawerMenuBinding> implements DrawerMenuContract.View, OnSaveProductListener{

    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;

    @Inject DrawerMenuContract.Presenter presenter;

    @Inject CardsFragment cardsFragment;

    @Inject StoreFragment storeFragment;

    @Inject ScannerFragment scannerFragment;

    @Inject TermsAndConditionsFragment termsAndConditionsFragment;

    @Inject ShoppingListFragment shoppingListFragment;

    @Inject OrderHistoryFragment orderHistoryFragment;

    private List<String> contacts;

    private boolean iconToggleBack;
    private OnBackPressed onBackPressed;
    private OnDeleteShoppingList onDeleteShoppingList;

    @Override
    protected void initView() {

        scannerFragment.setOnSaveProductListener(this);
        presenter.initialize(this);
        setFragment(scannerFragment);
        binder.navView.getMenu().getItem(0).setChecked(true);
        binder.menuContent.tvName.setText(R.string.tv_title_scanner);
        drawer = findViewById(R.id.menu_drawer_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(iconToggleBack){
                    onBackPressed.onBack();
                }else{
                    drawer.openDrawer(GravityCompat.START);
                    presenter.sendActionView();
                }
            }
        });
        toggle.setHomeAsUpIndicator(R.drawable.ic_menu);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        binder.menuContent.lyCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.analyticSedAction("Scanner","Header-Top-Carro");
                startActivity(CarActivity.class);
            }
        });

        binder.menuContent.lyDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteShoppingList.onDeleteShoppingList();
            }
        });

    }

    public void callPhone(String phone){
        Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    public void sendEmail(String email){
        Intent send = new Intent(Intent.ACTION_SENDTO);
        String uriText = "mailto:" + Uri.encode(email) +
                "?subject=" + Uri.encode("Scan & Go"); //+
                //"&body=" + Uri.encode("the body of the message");
        Uri uri = Uri.parse(uriText);
        send.setData(uri);
        startActivity(Intent.createChooser(send, "Enviar email..."));
    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if(getActualFragment().getId() == scannerFragment.getId())
                super.onBackPressed();
            else{
                setScannerFragment();
            }
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        binder.menuContent.lyDelete.setVisibility(View.GONE);
        switch (id) {
            case R.id.nav_scanner:
                binder.menuContent.tvName.setText(R.string.tv_title_scanner);
                binder.menuContent.lyCart.setVisibility(View.VISIBLE);
                setFragment(scannerFragment);
                presenter.analyticSedAction("Scanner","Header-Top-Menu");
                break;
            case R.id.nav_terms_and_conditions:
                setTermsAndConditions();
                break;
            case R.id.nav_my_cards:
                setPaymentMethod();
                break;
            case R.id.nav_tutorial:
                setTutorial();
                break;
            case R.id.nav_stores:
                setLocales();
                break;
            case R.id.nav_shopping_list:
                setShoppingList();
                break;
            case R.id.nav_shopping_history:
                setShoppingHistory();
                break;
            case R.id.nav_log_out:
                presenter.logout();
                break;
            case R.id.nav_email:
                sendEmail(contacts.get(0));
                break;
            case R.id.nav_call:
                callPhone(contacts.get(1));
                break;
            default:
                setFragment(new ContentFragment());
                binder.menuContent.lyCart.setVisibility(View.GONE);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        presenter.analyticSedAction("Cerrar sesion", "Header-Top-Menu");
        return true;
    }

    @Override
    protected void injectDependencies() {
        DaggerDrawerMenuComponent.builder().build().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_drawer_menu;
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.rl_menu_content;
    }

    @Override
    public void showProgress(boolean show) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void displayCountCart(int count) {

        if(count == 0){
            binder.menuContent.lyCart.setEnabled(false);
            binder.menuContent.tvCountCart.setVisibility(View.GONE);
        }else{
            binder.menuContent.lyCart.setEnabled(true);
            binder.menuContent.tvCountCart.setVisibility(View.VISIBLE);
            binder.menuContent.tvCountCart.setText(String.valueOf(count));
        }
    }

    @Override
    public void goToLogin() {
        startActivity(LoginRegisterActivity.class);
        finish();
    }

    @Override
    public void displayUser(User user) {
        MenuHeaderBinding menuHeaderBinding = DataBindingUtil.bind(binder.navView.getHeaderView(0));
        menuHeaderBinding.rlProfileInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ProfileActivity.class);
            }
        });
        menuHeaderBinding.tvUserEmail.setText(user.email);
        menuHeaderBinding.tvUserName.setText(user.fullName);
    }

    
    @Override
    public void showContacts(List<String> contacts) {
        this.contacts = contacts;
    }

    @Override
    public void showToolTips() {
        if(scannerFragment!=null){
            scannerFragment.showToolTip();
        }
    }

    @Override
    public void onSaveProductListener() {
        Animation shake;
        shake = AnimationUtils.loadAnimation(this, R.anim.anim_cart);
        binder.menuContent.ivCart.setAnimation(shake);
        presenter.getCountCart();
        presenter.toolTips();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getCountCart();
    }

    public void setScannerFragment(){
        binder.menuContent.tvName.setText(R.string.tv_title_scanner);
        binder.menuContent.lyCart.setVisibility(View.VISIBLE);
        binder.menuContent.lyDelete.setVisibility(View.GONE);
        binder.navView.getMenu().getItem(0).setChecked(true);
        setFragment(scannerFragment);
        presenter.analyticSedAction("Scanner","Header-Top-Menu");
        toggle.setHomeAsUpIndicator(R.drawable.ic_menu);
        iconToggleBack = false;
    }

    public void setTermsAndConditions(){
        binder.menuContent.tvName.setText(R.string.tv_title_terms_and_conditions);
        binder.menuContent.lyCart.setVisibility(View.GONE);
        setFragment(termsAndConditionsFragment);
        presenter.analyticSedAction("Terminos y condiciones", "Header-Top-Menu");
    }

    public void setPaymentMethod(){
        binder.menuContent.tvName.setText(R.string.tv_title_cards);
        binder.menuContent.lyCart.setVisibility(View.GONE);
        setFragment(cardsFragment);
        presenter.analyticSedAction("Medios de pago", "Header-Top-Menu");
    }

    public void setTutorial(){
        binder.menuContent.tvName.setText(R.string.tv_title_on_boarding);
        startActivity(OnBoardingActivity.class);
        finish();
        presenter.analyticSedAction("Tutorial", "Header-Top-Menu");
    }

    public void setLocales(){
        binder.menuContent.tvName.setText(R.string.menu_stores);
        binder.menuContent.lyCart.setVisibility(View.GONE);
        setFragment(storeFragment);
        presenter.analyticSedAction("Locales", "Header-Top-Menu");
    }

    public void setShoppingList(){

        binder.menuContent.tvName.setText(R.string.menu_shopping_list);
        binder.menuContent.lyCart.setVisibility(View.GONE);
        binder.menuContent.lyDelete.setVisibility(View.VISIBLE);
        setFragment(shoppingListFragment);
        presenter.analyticSedAction("Lista", "Header-Top-Menu");
    }

    public void setShoppingHistory(){
        binder.menuContent.tvName.setText(R.string.menu_shopping_history);
        binder.menuContent.lyCart.setVisibility(View.GONE);
        setFragment(orderHistoryFragment);
        presenter.analyticSedAction("Historial de compras", "Header-Top-Menu");
    }

    public void setIconToggleBack(String title){
        binder.menuContent.tvName.setText(title);
        toggle.setHomeAsUpIndicator(R.drawable.icon_back_item_car);
        iconToggleBack = true;
    }

    public void setIconToggleMenu(String title){
        binder.menuContent.tvName.setText(title);
        toggle.setHomeAsUpIndicator(R.drawable.ic_menu);
        iconToggleBack = false;
    }

    public void setOnBackPressed(OnBackPressed onBackPressed){
        this.onBackPressed = onBackPressed;
    }

    public void setOnDeleteShoppingList(OnDeleteShoppingList onDeleteShoppingList){
        this.onDeleteShoppingList = onDeleteShoppingList;
    }

}
