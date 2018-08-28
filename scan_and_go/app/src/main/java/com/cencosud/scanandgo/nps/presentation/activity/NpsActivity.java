package com.cencosud.scanandgo.nps.presentation.activity;

import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;
import com.cencosud.scanandgo.R;
import com.cencosud.scanandgo.databinding.ActivityNpsBinding;
import com.cencosud.scanandgo.menu.presentation.activity.DrawerMenuActivity;
import com.cencosud.scanandgo.nps.di.component.DaggerNpsComponent;
import com.cencosud.scanandgo.nps.presentation.contract.NpsContract;
import com.core.presentation.activity.BaseActivity;
import javax.inject.Inject;

public class NpsActivity extends BaseActivity<ActivityNpsBinding> implements NpsContract.View {

    @Inject
    NpsContract.Presenter presenter;

    private int selected = -1;

    @Override
    protected void initView() {

        presenter.initialize(this);
        binder.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                ratingBar.setRating((int) ratingBar.getRating());

                switch ((int) ratingBar.getRating()) {
                    case 1:
                        binder.rlNps.setBackground(getResources().getDrawable(R.drawable.nps_purple));
                        binder.ivNps.setBackground(getResources().getDrawable(R.drawable.icon_nps_01));
                        break;
                    case 2:
                        binder.rlNps.setBackground(getResources().getDrawable(R.drawable.nps_purple_light));
                        binder.ivNps.setBackground(getResources().getDrawable(R.drawable.icon_nps_02));
                        break;
                    case 3:
                        binder.rlNps.setBackground(getResources().getDrawable(R.drawable.nps_dark_pink));
                        binder.ivNps.setBackground(getResources().getDrawable(R.drawable.icon_nps_03));
                        break;
                    case 4:
                        binder.rlNps.setBackground(getResources().getDrawable(R.drawable.nps_pink));
                        binder.ivNps.setBackground(getResources().getDrawable(R.drawable.icon_nps_04));
                        break;
                    case 5:
                        binder.rlNps.setBackground(getResources().getDrawable(R.drawable.nps_fucsia));
                        binder.ivNps.setBackground(getResources().getDrawable(R.drawable.icon_nps_05));
                        break;
                    default:
                        binder.rlNps.setBackground(getResources().getDrawable(R.drawable.nps_purple));
                        binder.ivNps.setBackground(getResources().getDrawable(R.drawable.icon_nps_01));
                        //ratingBar.setRating(Float.parseFloat("1"));

                }

            }
        });

        binder.tvSkipNps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.cleanCache();
                goToScanner();
            }
        });

        binder.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selected!=-1){
                    presenter.sendNps(binder.ratingBar.getRating() * 2, binder.edtAddCommentary.getText().toString(),selected==1?"si":"no");
                } else
                    Toast.makeText(getApplicationContext(), "Seleccione Si ó No fue rápida su compra.", Toast.LENGTH_LONG).show();
            }
        });

        binder.btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChangePicker(1);

            }
        });

        binder.btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChangePicker(0);
            }
        });

    }

    @Override
    protected void injectDependencies() {
        DaggerNpsComponent.builder().build().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_nps;
    }

    @Override
    public void showProgress(boolean show) {
        binder.progress.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void goToScanner() {
        startActivity(DrawerMenuActivity.class);
        finish();
    }

    public void onChangePicker(int yes_no) {
        selected = yes_no;
        binder.btnYes.setEnabled(yes_no == 0);
        binder.btnNo.setEnabled(yes_no == 1);
    }
}