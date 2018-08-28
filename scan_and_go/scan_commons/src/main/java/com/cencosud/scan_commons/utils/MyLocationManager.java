package com.cencosud.scan_commons.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by carlos on 24-07-18.
 */

public class MyLocationManager {

    private Context context;
    private LocationManager locationManager;
    private ArrayList<String> permissions = new ArrayList<>();
    private MyLocationManagerInterface myLocationManagerInterface;

    public MyLocationManager(Context context, MyLocationManagerInterface myLocationManagerInterface) {
        this.context = context;
        this.permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        this.permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        this.locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        this.myLocationManagerInterface = myLocationManagerInterface;
    }

    public void getMyLocation() {
        if (checkLocation()) {
            getLastKnownLocation();
        }
    }

    private boolean isPermissionNotAvailable() {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED;
    }

    @SuppressLint("MissingPermission")
    private void getLastKnownLocation() {
        List<String> providers = locationManager.getProviders(true);
        Location bestLocation = null;

        if (isPermissionNotAvailable()) {
            myLocationManagerInterface.onRequestPermission(permissions.toArray(new String[permissions.size()]), 1);

        } else {

            for (String provider : providers) {
                Location l = locationManager.getLastKnownLocation(provider);
                if (l == null) {
                    continue;
                }
                if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                    bestLocation = l;
                }
            }

            myLocationManagerInterface.onMyLocation(bestLocation);
        }
    }

    private boolean checkLocation() {
        if (!isLocationEnabled())
            showAlert();
        return isLocationEnabled();
    }

    private boolean isLocationEnabled() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle("Enable Location")
                .setMessage("Su ubicación esta desactivada.\npor favor active su ubicación " +
                        "usa esta app")
                .setPositiveButton("Configuración de ubicación", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        context.startActivity(myIntent);
                        paramDialogInterface.dismiss();
                        ((Activity) context).finish();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        paramDialogInterface.dismiss();
                        ((Activity) context).finish();
                    }
                });
        dialog.show();
    }

    public static float calculateDistance(LatLng a, LatLng b) {

        float[] distance = new float[1];
        android.location.Location.distanceBetween(a.latitude, a.longitude, b.latitude, b.longitude, distance);
        return distance[0];

    }

    public static String replaceDistance(float distance){

        if (distance > 1000) {
            return String.format("%.1f", distance / 1000).replace(".", ",") + " Km";
        }
        return String.format("%.1f", distance).replace(".", ",") + " Mts";

    }

    public static Address getAddress(double latitude, double longitude, Context context) {

        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(context, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(latitude,longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            if(!addresses.isEmpty()) return addresses.get(0);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public interface MyLocationManagerInterface {
        void onRequestPermission(@NonNull String[] permissions, int requestCode);

        void onMyLocation(Location bestLocation);
    }

}

