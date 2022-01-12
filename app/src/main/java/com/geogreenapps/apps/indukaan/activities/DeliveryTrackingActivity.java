package com.geogreenapps.apps.indukaan.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.geogreenapps.apps.indukaan.GPS.GPStracker;
import com.geogreenapps.apps.indukaan.R;
import com.geogreenapps.apps.indukaan.classes.Driver;
import com.geogreenapps.apps.indukaan.helper.CommunFunctions;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeliveryTrackingActivity extends GlobalActivity implements OnMapReadyCallback {

    private final String TAG = "DeliveryTrackingActivity";
    private final int order_id = -1;
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_description)
    TextView toolbarDescription;
    @BindView(R.id.item_focus_layout)
    LinearLayout item_focus_layout;
    @BindView(R.id.btn_contact)
    AppCompatButton btn_contact;
    @BindView(R.id.closeLayout)
    ImageView closeLayout;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.address)
    TextView address;
    //add destination marker
    Marker marker;
    private GoogleMap mMap;
    private DatabaseReference mDatabase;
    private HashMap<String, String> params;
    private LatLng myPosition, delivery_boy;
    private int delievry_boy_id = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        setContentView(R.layout.activity_delivery_boy_tracking);
        ButterKnife.bind(this);

        initToolbar();

        // init firebase database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //INITIALIZE MY LOCATION
        GPStracker trackMe = new GPStracker(this);
        myPosition = new LatLng(trackMe.getLatitude(), trackMe.getLongitude());


        if (getIntent() != null && getIntent().hasExtra("params")) {
            params = (HashMap<String, String>) getIntent().getSerializableExtra("params");
            delievry_boy_id = Integer.valueOf(params.get("delivery_id"));
        } else {
            Toast.makeText(this, getString(R.string.error_try_later), Toast.LENGTH_SHORT).show();
            finish();
        }


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    public void initToolbar() {

        toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarDescription.setVisibility(View.GONE);
        toolbarTitle.setText(R.string.find_my_place);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (mMap == null) return;

        //mMap.setOnCameraIdleListener(onCameraIdleListener);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myPosition, 15));
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                item_focus_layout.setVisibility(View.VISIBLE);
                return false;
            }
        });


        checkDeliveryBoyLocation(mMap);


        mMap.setMyLocationEnabled(true);
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                mMap.clear();

                mMap.addMarker(new MarkerOptions().position(point).title("My location").draggable(true));
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (android.R.id.home == item.getItemId()) {
            Intent intent = new Intent();

            setResult(Activity.RESULT_CANCELED, intent);
            finish();
            overridePendingTransition(R.anim.righttoleft_enter, R.anim.righttoleft_exit);
        }

        return super.onOptionsItemSelected(item);
    }


    private void checkDeliveryBoyLocation(GoogleMap _mMap) {
        // [START post_value_event_listener]
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI

                Driver driver = dataSnapshot.child("users").child("user-" + delievry_boy_id).getValue(Driver.class);
                //Driver driver = dataSnapshot.getValue(Driver.class);
                if (driver != null) {
                    if (delivery_boy != null && (delivery_boy.latitude == driver.lat && driver.lng == delivery_boy.longitude)) {

                    } else {
                        delivery_boy = new LatLng(driver.lat, driver.lng);

                        _mMap.clear();

                        _mMap.addMarker(new MarkerOptions().position(delivery_boy).title("delivery boy")
                                // below line is use to add custom marker on our map.
                                .icon(CommunFunctions.BitmapFromVector(R.drawable.delivery_marker)));

                        _mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(delivery_boy, 18));


                    }

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };

        //add value event changed listener
        mDatabase.addValueEventListener(postListener);

    }


}