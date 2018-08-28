package com.cencosud.scanandgo.scanner.presentation.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.RectF;
import android.location.Location;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cencosud.scan_commons.product.domain.model.Product;
import com.cencosud.scan_commons.shopping_list.domain.model.ShoppingList;
import com.cencosud.scan_commons.shopping_list.domain.model.TagsShopping;
import com.cencosud.scan_commons.utils.MyLocationManager;
import com.cencosud.scanandgo.R;
import com.cencosud.scanandgo.car.utils.TextUtils;
import com.cencosud.scanandgo.checkout.presentation.activity.CheckoutActivity;
import com.cencosud.scanandgo.databinding.FragmentScannerBinding;
import com.cencosud.scanandgo.scanner.camera.OnCameraErrorListener;
import com.cencosud.scanandgo.scanner.di.DaggerScannerComponent;
import com.cencosud.scanandgo.scanner.presentation.OnSaveProductListener;
import com.cencosud.scanandgo.scanner.presentation.adapter.ScannerAdapter;
import com.cencosud.scanandgo.scanner.presentation.contract.ScannerContract;
import com.cencosud.scanandgo.scanner.presentation.dialog.CodeNotFoundDialog;
import com.cencosud.scanandgo.shopping_list.adapter.ShoppingListAdapter;
import com.core.presentation.adapter.OnItemClickListener;
import com.core.presentation.fragment.BaseFragment;
import com.core.util.AndroidUtils;
import com.scandit.barcodepicker.BarcodePicker;
import com.scandit.barcodepicker.OnScanListener;
import com.scandit.barcodepicker.ScanSession;
import com.scandit.barcodepicker.ScanSettings;
import com.scandit.barcodepicker.ScanditLicense;
import com.scandit.recognition.Barcode;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Francisco Barrios on 17-05-18.
 */

public class ScannerFragment extends BaseFragment<FragmentScannerBinding> implements OnCameraErrorListener, ScannerContract.View, CodeNotFoundDialog.NoticeDialogListener, OnItemClickListener<TagsShopping>, MyLocationManager.MyLocationManagerInterface, OnScanListener {

    private static final String TAG = "BARCODE:::";
    private static final int RC_HANDLE_GMS = 9001;
    private static final int RC_HANDLE_CAMERA_PERM = 2;
    private Product product;

    private boolean hasCameraPermission = false;
    private BarcodePicker picker;

    @Inject
    ScannerContract.Presenter presenter;

    @Inject
    CodeNotFoundDialog codeNotFoundDialog;

    @Inject
    ShoppingListAdapter shoppingListAdapter;

    private ScannerAdapter scannerAdapter;
    private OnSaveProductListener onSaveProductListener;
    MyLocationManager myLocationManager;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_scanner;
    }

    @Override
    protected void injectDependencies() {
        DaggerScannerComponent.builder().build().inject(this);
    }

    @Override
    protected void initView() {

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        codeNotFoundDialog.setListener(this);
        codeNotFoundDialog.setCancelable(false);

        presenter.initialize(this);
        binder.tvPriceTotal.setText(TextUtils.decimalFormat(presenter.totalPrice()));
        checkPermissionCamera();
        //binder.scannerPreview.setOnCameraErrorListener(this);
        //onCreateDetector(binder.scannerPreview);
        binder.btnGoToPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (presenter.totalQuantity() > 0) {
                    startActivity(CheckoutActivity.class);
                    presenter.analyticSendAction("Pagar");
                }
            }
        });

        binder.edtAddCodeManually.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    if (binder.edtAddCodeManually.getText().toString().trim().length() >= 8 && binder.edtAddCodeManually.getText().toString().trim().length() <= 13 && !scannerAdapter.code.equals(binder.edtAddCodeManually.getText().toString())) {
                        scannerAdapter.code = binder.edtAddCodeManually.getText().toString();
                        if (!scannerAdapter.code.equals("")) {
                            presenter.getProduct(binder.edtAddCodeManually.getText().toString());
                            presenter.analyticSendAction("Agregar Código Manual");
                        }
                        binder.edtAddCodeManually.setText("");
                    } else {
                        scannerAdapter.code="";
                        showMessage("Debe ingresar 8 o 13 caracteres");
                        binder.edtAddCodeManually.setText("");
                    }
                    AndroidUtils.hideKeyboard(getActivity());

                    return true;
                }
                return false;
            }
        });

        binder.edtAddCodeManually.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() > 0) {

                    binder.edtAddCodeManually.setBackgroundResource(R.drawable.button_pressed_code_scan);

                } else
                    binder.edtAddCodeManually.setBackgroundResource(R.drawable.button_active_scan);


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        if (binder.lyStoreNotAvailable.rlStoreNotAvailable.getVisibility() == View.VISIBLE) {
            binder.edtAddCodeManually.setEnabled(false);
        }

        binder.slidingLayout.setFadeOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binder.slidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            }
        });

        binder.slidingLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {

            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                binder.tvList.setVisibility(View.GONE);
                binder.lyIcSlide.setVisibility(View.GONE);
            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {

            }
        });

        binder.slidingLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                // Ensure you call it only once :
                configScanner();

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    binder.slidingLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    binder.slidingLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
            }
        });

        // Here you can get the size :)
        binder.lyStoreNotAvailable.btnReconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myLocationManager.getMyLocation();
            }
        });

        myLocationManager = new MyLocationManager(getContext(), this);
        myLocationManager.getMyLocation();
    }

    @Override
    public void onRequestPermission(@NonNull String[] permissions, int requestCode) {
        requestPermissions(permissions, 1);
    }

    @Override
    public void onMyLocation(Location bestLocation) {
        //Toast.makeText(getContext(),"la mejor ubicacions es: "+bestLocation.getLongitude() +", "+bestLocation.getLatitude(),Toast.LENGTH_LONG).show();
        presenter.calculateLocation(bestLocation);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //TODO: implementar recibir el resultado del permiso
        myLocationManager.getMyLocation();
        if (requestCode == RC_HANDLE_CAMERA_PERM) {
            checkPermissionCamera();
        }
    }

    public void checkPermissionCamera() {
        try {
            if (getContext() != null) {
                int rc = ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA);
                if (rc == PackageManager.PERMISSION_GRANTED) {
                    hasCameraPermission = true;
                    picker.startScanning();
                } else {
                    requestCameraPermission();
                }
            }
        } catch (Exception e) {
            Log.d(TAG, "Exception: " + e.getMessage());
        }
    }

    @Override
    public void onCameraError() {
    }


    @Override
    public void onResume() {
        super.onResume();

        if (hasCameraPermission && picker != null && binder.lyStoreNotAvailable.rlStoreNotAvailable.getVisibility() != View.VISIBLE) {
            picker.startScanning();
            binder.edtAddCodeManually.setEnabled(true);
        }

        if (binder.lyStoreNotAvailable.rlStoreNotAvailable.getVisibility() == View.VISIBLE) {
            binder.edtAddCodeManually.setEnabled(false);
        }
        binder.tvPriceTotal.setText(TextUtils.decimalFormat(presenter.totalPrice()));
        if (binder.slidingLayout != null &&
                (binder.slidingLayout.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED || binder.slidingLayout.getPanelState() == SlidingUpPanelLayout.PanelState.ANCHORED)) {
            binder.slidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        /*if (binder.scannerPreview != null) {
            binder.scannerPreview.stop();
        }*/
        if (hasCameraPermission && picker != null) {
            picker.stopScanning();
        }
        if (product != null && product.productQuantity > 0) {
            presenter.setProduct(product);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        /*if (binder.scannerPreview != null) {
            binder.scannerPreview.release();
        }*/
    }

    @Override
    public void onStart() {
        super.onStart();
        //startCameraSource();
    }

    private void requestCameraPermission() {
        Log.w(TAG, "Camera permission is not granted. Requesting permission");
        final String[] permissions = new String[]{Manifest.permission.CAMERA};
        try {
            if (getActivity() != null) {
                requestPermissions(permissions, RC_HANDLE_CAMERA_PERM);
                return;
            }
        } catch (Exception e) {
            Log.d(TAG, "Exception: " + e.getMessage());
        }
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestPermissions(permissions, RC_HANDLE_CAMERA_PERM);
            }
        };
        //binder.scannerPreview.setOnClickListener(listener);
    }

    @Override
    public void showProgress(boolean show) {

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void displayProduct(Product product_) {
        this.product = product_;
        scannerAdapter.displayProduct(product_);
    }

    @Override
    public void onSaveProduct() {
        onSaveProductListener.onSaveProductListener();
        product = null;
    }

    @Override
    public void showProductNotFount() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        if (codeNotFoundDialog.isAdded()) {
            ft.show(codeNotFoundDialog);
        } else {
            codeNotFoundDialog.show(getFragmentManager(), "NoticeDialogFragment");
        }
    }

    @Override
    public void showStoreNotAvailable(boolean show) {
        binder.lyStoreNotAvailable.rlStoreNotAvailable.setVisibility(show?View.VISIBLE:View.GONE);
        if (hasCameraPermission && picker != null && binder.lyStoreNotAvailable.rlStoreNotAvailable.getVisibility() != View.VISIBLE) {
            picker.startScanning();
            binder.edtAddCodeManually.setEnabled(true);
        }
    }

    @Override
    public void displayShoppingList(ShoppingList shoppingList) {
        shoppingListAdapter.setList(shoppingList.tags);
        shoppingListAdapter.setOnItemClickListener(this);
        binder.lySwipe.rvShoppingList.setAdapter(shoppingListAdapter);
        binder.lySwipe.tvTitleShoppingList.setText(shoppingList.title);
        binder.lySwipe.lySwipeContent.setVisibility(View.VISIBLE);
        binder.lySwipe.dragView.setVisibility(View.VISIBLE);
        binder.lySwipe.dragView.requestLayout();
        binder.lyIcSlide.setVisibility(View.VISIBLE);
        animateIcSlide();
    }


    private void animateIcSlide() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                binder.icSlide3.setVisibility(View.VISIBLE);
            }
        }, 1000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                binder.icSlide2.setVisibility(View.VISIBLE);

            }
        }, 1300);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                binder.icSlide1.setVisibility(View.VISIBLE);
                binder.tvList.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in));
                binder.tvList.animate();
                binder.tvList.setVisibility(View.VISIBLE);
            }
        }, 1600);
    }

    @Override
    public void notShoppingList() {
        binder.lySwipe.lySwipeContent.setVisibility(View.GONE);
        binder.lySwipe.dragView.setVisibility(View.GONE);
        binder.tvList.setVisibility(View.GONE);
        binder.lyIcSlide.setVisibility(View.GONE);
    }

    public void setOnSaveProductListener(OnSaveProductListener onSaveProductListener) {
        this.onSaveProductListener = onSaveProductListener;
    }

    public static ScannerFragment newInstance() {
        ScannerFragment instance = new ScannerFragment();
        return instance;
    }


    @Override
    public void onCodeNotFoundDialogPositiveClick() {
        codeNotFoundDialog.dismiss();
        scannerAdapter.code = "";
        picker.startScanning();
    }

    public void showToolTip() {
        binder.rlTooltip.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                binder.rlTooltip.setVisibility(View.GONE);
            }
        }, 4000);
    }

    @Override
    public void onItemClick(int adapterPosition, TagsShopping item) {
        presenter.saveTagShopping(adapterPosition, item);
    }

    public void configScanner() {

        ScanditLicense.setAppKey(getString(R.string.scanner_api_key));
        ScanSettings settings = ScanSettings.create();
        settings.setSymbologyEnabled(Barcode.SYMBOLOGY_EAN8, true);
        settings.setSymbologyEnabled(Barcode.SYMBOLOGY_EAN13, true);
        settings.setSymbologyEnabled(Barcode.SYMBOLOGY_UPCA, true);

        float z = (float) binder.ivScannerMiddleMask.getLeft() / binder.pickerCamera.getWidth();
        float x = (float) binder.ivScannerMiddleMask.getTop() / binder.pickerCamera.getHeight();
        float y = (float) binder.ivScannerMiddleMask.getRight() / binder.slidingLayout.getWidth();
        float m = (float) binder.ivScannerMiddleMask.getBottom() / binder.slidingLayout.getHeight();

        RectF rectF = new RectF(z, x, y, m);
        settings.setActiveScanningArea(ScanSettings.ORIENTATION_PORTRAIT, rectF);
        picker = new BarcodePicker(getContext(), settings);
        picker.setOnScanListener(this);
        binder.pickerCamera.addView(picker);
        picker.getOverlayView().drawViewfinder(false);
        picker.getOverlayView().setBeepEnabled(false);
        scannerAdapter = new ScannerAdapter();
        scannerAdapter.initialize(binder, presenter, picker);

        if (hasCameraPermission && picker != null && binder.lyStoreNotAvailable.rlStoreNotAvailable.getVisibility() != View.VISIBLE) {
            picker.startScanning();
            binder.edtAddCodeManually.setEnabled(true);
        }

        if (binder.lyStoreNotAvailable.rlStoreNotAvailable.getVisibility() == View.VISIBLE) {
            binder.edtAddCodeManually.setEnabled(false);
        }

    }

    @Override
    public void didScan(final ScanSession scanSession) {

        final Barcode best = scanSession.getAllRecognizedCodes().get(0);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(!scannerAdapter.code.equals(best.getData().trim())){
                    pauseScanning();
                    scannerAdapter.code = best.getData().trim();
                    presenter.getProduct(scannerAdapter.code);
                }
            }
        });
    }

    public void pauseScanning(){
        picker.pauseScanning();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                picker.startScanning();
            }
        }, 2000);
    }

}