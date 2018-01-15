package com.example.pozigi.roadinfo;

/**
 * Created by pozigi on 13. 01. 2018.
 */

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.icu.text.DecimalFormat;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.constant.TransportMode;
import com.akexorcist.googledirection.constant.Unit;
import com.akexorcist.googledirection.model.Direction;
import com.akexorcist.googledirection.model.Fare;
import com.akexorcist.googledirection.model.Route;
import com.akexorcist.googledirection.util.DirectionConverter;
import com.example.lib_data.Lokacija;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationClickListener;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


import android.location.Location;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener, DirectionCallback, OnMyLocationButtonClickListener, OnMyLocationClickListener {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private boolean mPermissionDenied = false;
    ApplicationMy app;
    private GoogleMap mMap;
    private String serverKey = "AIzaSyDNCtwGj2h2cFH9wuEinkP6YDPMAjDG9SA";
    private LatLng origin;
    private LatLng destination;
    private final int TEN_SECONDS = 10000;
    boolean prvic = true;
    Handler handler = new Handler();
    Button bt, shrani;
    int id;
    int potniS;
    int potniN;
    List<LatLng> waypoints;
    Location mLastKnownLocation;
    boolean zastavica;
    FusedLocationProviderClient   mFusedLocationProviderClient;

    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (mMap != null) {
            // Access to the location has been granted to the app.
            mMap.setMyLocationEnabled(true);

        }
    }

    private void getDeviceLocation() {
    /*
     * Get the best and most recent location of the device, which may be null in rare
     * cases when a location is not available.
     */
    boolean mLocationPermissionGranted = true;
        try {
            if (mLocationPermissionGranted) {
                Task locationResult = mFusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            // Set the map's camera position to the current location of the device.
                            mLastKnownLocation = (Location) task.getResult();
                            app.getAll().getPotneStroske().get(potniS).getPotneNaloge().get(potniN).getRelacije().get(id).getGPS().add(new Lokacija(mLastKnownLocation.getLongitude(),mLastKnownLocation.getLatitude(),mLastKnownLocation.getTime()));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(mLastKnownLocation.getLatitude(),
                                            mLastKnownLocation.getLongitude()), 10));

                        } else {
                           // Log.d(TAG, "Current location is null. Using defaults.");
                            //Log.e(TAG, "Exception: %s", task.getException());
                          //  mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
                           // mMap.getUiSettings().setMyLocationButtonEnabled(false);
                        }
                    }
                });
            }
        } catch(SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        app = (ApplicationMy) getApplication();

        Intent i = getIntent();
        id = i.getIntExtra("id",-1);
        potniS = i.getIntExtra("potniS",-1);
        potniN = i.getIntExtra("potniN",-1);

        if(app.getAll().getPotneStroske().get(potniS).getPotneNaloge().get(potniN).getRelacije().get(id).getGPS().size()>0){
            requestDirection(false);
        }

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        PlaceAutocompleteFragment autocompleteFragment1 = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment1);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                origin = place.getLatLng();
            }

            @Override
            public void onError(Status status) {

            }
        });

        autocompleteFragment1.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                destination = place.getLatLng();
            }

            @Override
            public void onError(Status status) {

            }
        });

        bt = (Button) findViewById(R.id.button3);
        bt.setOnClickListener(this);
        shrani = (Button) findViewById(R.id.button4);
        shrani.setOnClickListener(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
       /* LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney)); */
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);
        enableMyLocation();
    }
    public void requestDirection(boolean zastavica){
        //Snackbar.make(btnRequestDirection, "Direction Requesting...", Snackbar.LENGTH_SHORT).show();
        if(zastavica){
            GoogleDirection.withServerKey(serverKey)
                    .from(origin)
                    .to(destination)
                    .unit(Unit.METRIC)
                    .transportMode(TransportMode.DRIVING)
                    .execute(this);
        }else{
            pretvori();
            GoogleDirection.withServerKey(serverKey)
                    .from(origin)
                    .and(waypoints)
                    .to(destination)
                    .unit(Unit.METRIC)
                    .transportMode(TransportMode.DRIVING)
                    .execute(this);
        }

    }
    public void pretvori(){
        ArrayList<Lokacija> lok = app.getAll().getPotneStroske().get(potniS).getPotneNaloge().get(potniN).getRelacije().get(id).getGPS();
        waypoints = new ArrayList<>();
        origin = new LatLng(lok.get(0).getLatt(),lok.get(0).getLang());
        destination = new LatLng(lok.get(lok.size()-1).getLatt(),lok.get(lok.size()-1).getLang());
        for(int k=1;k<lok.size()-1;k++){
            waypoints.clear();
            waypoints.add(new LatLng(lok.get(k).getLatt(),lok.get(k).getLang()));
        }


    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.button3){
            requestDirection(true);
        }
        if(id == R.id.button4){
            handler.removeCallbacksAndMessages(null);
            app.save();
            onBackPressed();
        }
    }

    @Override
    public void onDirectionSuccess(Direction direction, String rawBody) {
       // Snackbar.make(btnRequestDirection, "Success with status : " + direction.getStatus(), Snackbar.LENGTH_SHORT).show();
        if (direction.isOK()) {
            Fare f = direction.getRouteList().get(0).getFare();
            Route route = direction.getRouteList().get(0);
            mMap.addMarker(new MarkerOptions().position(origin));
            mMap.addMarker(new MarkerOptions().position(destination));

            ArrayList<LatLng> directionPositionList = route.getLegList().get(0).getDirectionPoint();
            mMap.addPolyline(DirectionConverter.createPolyline(this, directionPositionList, 5, Color.RED));
            setCameraWithCoordinationBounds(route);


            //IZRAČUNATE TREBA DOLŽINO



            ArrayList<Lokacija> gps = new ArrayList<>();
            for(int i=0;i<directionPositionList.size();i++){
                gps.add(new Lokacija(directionPositionList.get(i).longitude, directionPositionList.get(i).latitude,0));
            }
            Location loc1 = new Location("");
            Location loc2 = new Location("");
            double strosek=0;
            for(int i=1;i<gps.size();i++){
                loc1.setLatitude(gps.get(i-1).getLatt());
                loc1.setLongitude(gps.get(i-1).getLang());

                loc2.setLatitude(gps.get(i).getLatt());
                loc2.setLongitude(gps.get(i).getLang());

                strosek+=loc1.distanceTo(loc2);
            }

            strosek /=1000;
            strosek = Math.round(strosek*100)/100;
           // java.text.DecimalFormat df = new java.text.DecimalFormat("0.0");
           // strosek = Double.parseDouble(df.format(strosek));
           // String.format("%.2f",strosek);
            app.getAll().getPotneStroske().get(potniS).getPotneNaloge().get(potniN).getRelacije().get(id).setRazdalja(strosek);
            app.getAll().getPotneStroske().get(potniS).getPotneNaloge().get(potniN).getRelacije().get(id).setStrosek(strosek);
            //VNESEMO LOKACIJE V APPLIKACIJO
            app.getAll().getPotneStroske().get(potniS).getPotneNaloge().get(potniN).getRelacije().get(id).setGPS(gps);
            app.save();

            //btnRequestDirection.setVisibility(View.GONE);
        } else {
            //Snackbar.make(btnRequestDirection, direction.getStatus(), Snackbar.LENGTH_SHORT).show();
        }
    }

    private void setCameraWithCoordinationBounds(Route route) {
        LatLng southwest = route.getBound().getSouthwestCoordination().getCoordination();
        LatLng northeast = route.getBound().getNortheastCoordination().getCoordination();
        LatLngBounds bounds = new LatLngBounds(southwest, northeast);
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));

    }

    @Override
    public void onDirectionFailure(Throwable t) {

    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (mPermissionDenied) {
            // Permission was not granted, display error dialog.
            showMissingPermissionError();
            mPermissionDenied = false;
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        scheduleSendLocation();
        return false;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }

        if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Enable the my location layer if the permission has been granted.
            enableMyLocation();
        } else {
            // Display the missing permission error dialog when the fragments resume.
            mPermissionDenied = true;
        }
    }
    @Override
    public void onMyLocationClick(@NonNull Location location) {

    }

    public void scheduleSendLocation(){
        handler.postDelayed(new Runnable(){
            public void run(){
                getDeviceLocation();
                handler.postDelayed(this,TEN_SECONDS);
            }
        },TEN_SECONDS);
    }

    private void showMissingPermissionError() {
        PermissionUtils.PermissionDeniedDialog
                .newInstance(true).show(getSupportFragmentManager(), "dialog");
    }



}