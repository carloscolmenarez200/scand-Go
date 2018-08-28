package com.cencosud.scanandgo.store.presentation.fragment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.View;
import com.cencosud.scan_commons.store.domain.model.Store;
import com.cencosud.scan_commons.utils.MyLocationManager;
import com.cencosud.scanandgo.R;
import com.cencosud.scanandgo.databinding.FragmentListBinding;
import com.cencosud.scanandgo.store.di.component.DaggerStoreFragmentComponent;
import com.cencosud.scanandgo.store.presentation.adapter.StoreListAdapter;
import com.cencosud.scanandgo.store.presentation.contract.StoreContract;
import com.core.presentation.fragment.BaseFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;

/**
 * Created by joseamaro on 27-06-18.
 */

public class StoreFragment extends BaseFragment<FragmentListBinding> implements GoogleMap.OnMarkerClickListener, OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener, StoreContract.View, LocationSource.OnLocationChangedListener, MyLocationManager.MyLocationManagerInterface {


    @Inject
    StoreListAdapter listAdapter;

    @Inject
    StoreContract.Presenter presenter;

    private GoogleMap mMap;
    private HashMap<String, Marker> mMapMarkers = new HashMap<>();

    LatLngBounds.Builder builder = new LatLngBounds.Builder();

    private static final float ZOOM_LOC = 17.0f;
    private MyLocationManager myLocationManager;
    private Location myLocation;

    String uri = "";


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initView() {

        presenter.initialize(this);
        myLocationManager = new MyLocationManager(getContext(), this);
        myLocationManager.getMyLocation();

        SupportMapFragment fm = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        fm.getMapAsync(this);

        binder.btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onChangeLisMap(false);
            }
        });
        binder.btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onChangeLisMap(true);
            }
        });
        binder.cvStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binder.cvStore.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
       myLocationManager.getMyLocation();
    }

    public static StoreFragment newInstance() {
        StoreFragment instance = new StoreFragment();
        return instance;
    }

    @Override
    protected void injectDependencies() {
        DaggerStoreFragmentComponent.builder().build().inject(this);
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        if (marker.getTag() != null) {
            Store store = listAdapter.getItem(Integer.parseInt(marker.getTag().toString()));
            showDetailStore(store,Integer.parseInt(marker.getTag().toString()));
        }
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), ZOOM_LOC));
        return true;
    }

    private void showDetailStore(final Store store, int pos) {

        if (pos!=0){
            binder.tvHowToGet.setVisibility(View.GONE);
            binder.cvStore.setVisibility(View.VISIBLE);
            binder.tvAddressStore.setText(store.address);
            binder.nameStore.setText(store.name);
            binder.tvKm.setText(MyLocationManager.replaceDistance(store.distance));

        }else
            binder.tvHowToGet.setVisibility(View.VISIBLE);
            binder.cvStore.setVisibility(View.VISIBLE);
            binder.tvAddressStore.setText(store.address);
            binder.nameStore.setText(store.name);
            binder.tvKm.setText(MyLocationManager.replaceDistance(store.distance));
            binder.tvHowToGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                howToGet(store);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        mMap.setOnMarkerClickListener(this);
        mMap.setOnInfoWindowClickListener(this);
        mMap.setPadding(220, 220, 220, 220);
    }

    @Override
    public void displayStores(List<Store> stores) {

        listAdapter.setList(stores);
        binder.rvStoreList.setAdapter(listAdapter);
        addStoresMarkers();
        setMyLocation();
    }

    private void setMyLocation() {

        if (myLocation != null) {

            Marker marker=  mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(myLocation.getLatitude(), myLocation.getLongitude()))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_my_location)));

            builder.include(marker.getPosition());
            mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 5));
        }
    }

    private void addStoresMarkers() {
        try {
            for (int i = 0; i < listAdapter.getItemCount(); i++) {
                addMarker(listAdapter.getItem(i), i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addMarker(Store store, int position) {
        LatLng latLng = new LatLng(Double.parseDouble(store.latitude), Double.parseDouble(store.longitude));

        Marker marker = mMap.addMarker(new MarkerOptions().position(latLng)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker)));
        marker.setTag(position + "");
        builder.include(marker.getPosition());
        mMapMarkers.put(String.valueOf(store.local), marker);
    }

    @Override
    public void showProgress(boolean show) {
        binder.progress.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showMessage(String message) {

    }

    public void onChangeLisMap(boolean map) {

        binder.frameMap.setVisibility(map ? View.VISIBLE : View.INVISIBLE);
        binder.btnList.setChecked(!map);
        binder.btnMaps.setChecked(map);
        binder.btnList.setEnabled(map);
        binder.btnMaps.setEnabled(!map);
        binder.rvStoreList.setVisibility(map ? View.INVISIBLE : View.VISIBLE);
    }

    @Override
    public void onLocationChanged(Location location) {

        if (location != null) {

            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(location.getLatitude(), location.getLongitude()))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_my_location)));

            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 10));
        }
    }

    public void howToGet(Store store) {
        LatLng to = new LatLng(Double.parseDouble(store.latitude), Double.parseDouble(store.longitude));

       /*String uri = String.format(Locale.ENGLISH, "geo:0,0?q=") +
               Uri.encode(String.format("%s@%f,%f", store.name, to.latitude,
                       to.longitude), "UTF-8");
       Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
       getContext().startActivity(Intent.createChooser(intent, "Select an application"));*/

        /*Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?daddr=" + to.latitude + "," + to.longitude));
        startActivity(Intent.createChooser(intent, "Selecciona  aplicación"));
        */
        try {
            uri = String.format(Locale.ENGLISH,"geo:" + to.latitude + "," +to.longitude) + "?q=" + to.latitude+","+to.longitude+" ("+store.name+")";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            try {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr=" + to.latitude + "," + to.longitude));
                startActivity(Intent.createChooser(intent, "Selecciona  aplicación"));
            } catch (ActivityNotFoundException innerEx) {
                //getSnackbar(getResources().getString(R.string.text_store__not_available), Snackbar.LENGTH_LONG).show();
            }
        }

    }

    @Override
    public void onRequestPermission(@NonNull String[] permissions, int requestCode) {
        requestPermissions(permissions, requestCode);
    }

    @Override
    public void onMyLocation(Location bestLocation) {
        myLocation = bestLocation;
        presenter.getStore(bestLocation);

    }
}
