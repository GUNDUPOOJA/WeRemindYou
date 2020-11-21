package com.example.weremindyou;

import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Geocoder myLocation;
    double latitude,longitude;
    static String address;
    Button get_loc;
    String action = "no",lat_from_edit,lng_from_edit;
    boolean edit_action = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        get_loc = findViewById(R.id.btn_get_location);
        get_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLocation();
            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    public void getLocation() {

        Intent intent=new Intent();
        intent.putExtra("Address",address);
        intent.putExtra("lat",latitude);
        intent.putExtra("lng",longitude);
        setResult(2,intent);
        finish();//finishing activity
    }

    private void mapListners() {
        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                latitude = marker.getPosition().latitude;
                longitude = marker.getPosition().longitude;
                Log.d("Marker","Lat:"+latitude+" Lon:" +longitude);
                myLocation = new Geocoder(getApplicationContext(), Locale.getDefault());
                List<Address> myList = null;

                try {
                    myList = myLocation.getFromLocation(latitude,longitude, 1);
                    address = myList.get(0).getAddressLine(0);
                    Log.d("address",myList.get(0)+"");
                    Toast.makeText(MapsActivity.this, address, Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(MapsActivity.this, "Unable to pick location\nMake sure internet is connected", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(36.86444079884785, -119.46720790117978);

        if(getIntent().getStringExtra("action")!=null) {
            action = getIntent().getStringExtra("action");
            if (action.contains("true")) {
                Log.d("Action ","edit");
                lat_from_edit = getIntent().getStringExtra("lat");
                Log.d("Action lat",lat_from_edit+"");
                lng_from_edit = getIntent().getStringExtra("lng");
                Log.d("Action lng",lng_from_edit+"");

                sydney = new LatLng(Double.valueOf(lat_from_edit), Double.valueOf(lng_from_edit));
            }
        }

        mMap.addMarker(new MarkerOptions()
                .position(sydney)
                .title("Selected Location")
                .draggable(true));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        mapListners();

    }
}