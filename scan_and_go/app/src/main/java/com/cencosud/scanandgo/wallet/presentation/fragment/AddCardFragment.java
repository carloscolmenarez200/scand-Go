package com.cencosud.scanandgo.wallet.presentation.fragment;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.cencosud.scan_commons.user.domain.model.User;
import com.cencosud.scanandgo.BuildConfig;
import com.cencosud.scanandgo.R;
import com.cencosud.scanandgo.databinding.FragmentAddCardBinding;
import com.cencosud.scanandgo.menu.presentation.activity.DrawerMenuActivity;
import com.cencosud.scanandgo.wallet.di.component.DaggerAddCardComponent;
import com.cencosud.scanandgo.wallet.presentation.contract.AddCardContract;
import com.cencosud.scanandgo.wallet.utils.DoneListener;
import com.cencosud.scanandgo.wallet.utils.OnBackPressed;
import com.core.presentation.fragment.BaseFragment;
import com.core.util.DialogHelper;
import java.net.URLEncoder;
import javax.inject.Inject;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by carlos on 29-03-18.
 */

public class AddCardFragment extends BaseFragment<FragmentAddCardBinding> implements AddCardContract.View, OnBackPressed {

    private DoneListener doneListener;

    @Inject
    AddCardContract.Presenter presenter;

    private User user;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_add_card;
    }

    @Override
    protected void initView() {

        if (getActivity() instanceof DrawerMenuActivity) {
            DrawerMenuActivity drawerMenuActivity = (DrawerMenuActivity) getActivity();
            drawerMenuActivity.setOnBackPressed(this);
            drawerMenuActivity.setIconToggleBack(getString(R.string.tv_title_cards));
        } else {
            if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
                ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
            }
        }
        presenter.initialize(this);
    }

    @Override
    protected void injectDependencies() {
        DaggerAddCardComponent.builder().build().inject(this);
    }

    public void setDoneListener(DoneListener doneListener) {
        this.doneListener = doneListener;
    }

    public void goBack() {
        doneListener.onDone();
        clearBackStack();
    }

    public void settingsWebView() {

        WebSettings settings = binder.wvCard.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSaveFormData(true);
        settings.setUseWideViewPort(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setDomStorageEnabled(true);
        binder.wvCard.loadUrl(BuildConfig.UrlCardWebView);
    }

    @Override
    public void showProgress(boolean show) {
        binder.layoutProgress.setVisibility(show ? View.VISIBLE : View.GONE);

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void displayWebView(User user_) {

        this.user = user_;

        binder.layoutProgress.setVisibility(View.VISIBLE);
        binder.wvCard.setWebViewClient(new WebViewClient() {

            // Handle API until level 21
            @SuppressWarnings("deprecation")
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                if(url.equals(BuildConfig.UrlCardWebView)) {
                    return getNewResponse(url);
                } else {
                    return super.shouldInterceptRequest(view, url);

                }
            }

            // Handle API 21+
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                String url = request.getUrl().toString();
                if (url.equals(BuildConfig.UrlCardWebView)) {
                    return getNewResponse(url);
                } else {
                    return super.shouldInterceptRequest(view, request);
                }
            }

            private WebResourceResponse getNewResponse(String url) {

                try {
                    String postData = "nombre=" + URLEncoder.encode(user.firstName, "UTF-8") + "&apellido=" + URLEncoder.encode(user.lastName, "UTF-8") + "&email=" + URLEncoder.encode(user.email, "UTF-8");
                    OkHttpClient httpClient = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(url.trim())
                            .addHeader("x-api-key", BuildConfig.ApiKey)// Example header
                            .addHeader("Content-Type", "application/x-www-form-urlencoded")
                            .post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"), postData.getBytes()))
                            .build();

                    Response response = httpClient.newCall(request).execute();

                    return new WebResourceResponse(
                            "null",
                            "UTF-8",
                            response.body().byteStream()
                    );

                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            /*@Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Map<String, String> header = new HashMap<>();
                header.put("x-api-key", BuildConfig.ApiKey);
                view.loadUrl(url, header);
                return true;
            }*/

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                if(url.contains("token/create")){
                    binder.layoutProgress.setVisibility(View.VISIBLE);
                }

                if(url.contains("paymentSuccess") || url.contains("paymentError")){
                    binder.layoutProgress.setVisibility(View.GONE);
                }

                if (url.contains("paymentOk") || url.contains("addPaymentAfter")) {
                    goBack();
                } else if (url.contains("paymentError")) {
                    showError();
                } else super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onReceivedError(WebView view, android.webkit.WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                binder.wvCard.setVisibility(View.GONE);
                binder.layoutProgress.setVisibility(View.GONE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                binder.wvCard.setVisibility(View.VISIBLE);
                binder.layoutProgress.setVisibility(View.GONE);
            }
        });

        settingsWebView();
    }

    public void showError() {

        new DialogHelper().attachContext(getContext()).showMessageDialogWitchTitle("Error", "Ha ocurrido un error, por favor verifique que los datos ingresados sean correctos", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                settingsWebView();
            }
        });
    }

    @Override
    public boolean allowBackPressed() {
        doneListener.onDone();
        return true;
    }

    @Override
    public void onBack() {
        goBack();
    }

}
