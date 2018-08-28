package com.cencosud.scanandgo.splash.presentation.activity;

import android.content.DialogInterface;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.cencosud.scan_commons.App;
import com.cencosud.scan_commons.utils.MyLocationManager;
import com.cencosud.scanandgo.R;
import com.cencosud.scanandgo.databinding.ActivitySplashBinding;
import com.cencosud.scanandgo.login_register.presentation.activity.LoginRegisterActivity;
import com.cencosud.scanandgo.menu.presentation.activity.DrawerMenuActivity;
import com.cencosud.scanandgo.nps.presentation.activity.NpsActivity;
import com.cencosud.scanandgo.onboarding.presentation.activity.OnBoardingActivity;
import com.cencosud.scanandgo.qr.presentation.activity.QrActivity;
import com.cencosud.scanandgo.splash.di.DaggerSplashComponent;
import com.cencosud.scanandgo.splash.presentation.SplashContract;
import com.core.presentation.activity.BaseSplashActivity;
import com.core.util.ConnectionUtils;
import com.core.util.DialogHelper;
import com.scottyab.rootbeer.RootBeer;

import javax.inject.Inject;

public class SplashActivity extends BaseSplashActivity<ActivitySplashBinding> implements SplashContract.View, MyLocationManager.MyLocationManagerInterface {

    @Inject
    SplashContract.Presenter presenter;

    private MyLocationManager myLocationManager;



    @Override
    protected void openNextActivity() {
    }

    @Override
    public void initView() {
        RootBeer rootBeer = new RootBeer(this);
        if (rootBeer.isRooted()) {
            showRooterDialogError();
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                );
            }

            init();
        }

    }

    private void init() {

        if (ConnectionUtils.isConnectionOff(App.getInstance())){
            if (ConnectionUtils.isConnectionOff(App.getInstance())) {
                showDialogError();
                return;
            }
        }

        presenter.initialize(this);
        myLocationManager = new MyLocationManager(this, this);
        myLocationManager.getMyLocation();
        RootBeer rootBeer = new RootBeer(this);



    }

    @Override
    public void injectDependencies() {
        DaggerSplashComponent.builder().build().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected int getSplashTime() {
        return 700;
    }

    @Override
    public void goToDashboard() {
        startActivity(DrawerMenuActivity.class);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public void goToOnBoarding() {
        startActivity(OnBoardingActivity.class);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public void goToLogin() {
        startActivity(LoginRegisterActivity.class);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public void goToQr() {
        startActivity(QrActivity.class);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public void goToNps() {
        startActivity(NpsActivity.class);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public void showDialogError() {
        new DialogHelper().attachContext(this).showMessageDialog("Ha ocurrido un error con la conexión verifique su conexión a internet.", "Error", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                init();
            }
        });
    }

    @Override
    public void showRooterDialogError() {
        new DialogHelper().attachContext(this).showMessageDialog("Ha ocurrido un error al ejecutar la aplicación.", "Error", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();

            }
        });
    }

    @Override
    public void showProgress(boolean show) {
    }

    @Override
    public void showMessage(String message) {
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        myLocationManager.getMyLocation();
    }

    @Override
    public void onRequestPermission(@NonNull String[] permissions, int requestCode) {
        ActivityCompat.requestPermissions(this, permissions, requestCode);
    }

    @Override
    public void onMyLocation(Location bestLocation) {
        presenter.getStores(bestLocation);
    }
}
