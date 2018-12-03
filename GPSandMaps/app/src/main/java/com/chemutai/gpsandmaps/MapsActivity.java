package com.chemutai.gpsandmaps;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LocationRequest mRequest;
    FusedLocationProviderClient mProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mProvider = LocationServices.getFusedLocationProviderClient(this);//get providers that can provide gps location
        mRequest = new LocationRequest();
        mRequest.setInterval(5000);//time in seconds
        mRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);//which provider to use i.e. gps, wifi
        mRequest.setSmallestDisplacement(10);//measure distance in meters
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        // how to enable zoom buttons on google maps android
        //how to change map style
        //how to disable traffic and buildings
        //how to


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 454);//activity you are calling from
            return;
        }
        mProvider.requestLocationUpdates(mRequest, mCallback, Looper.myLooper());
    }

    LocationCallback mCallback = new LocationCallback(){
        @Override
        public void onLocationResult(LocationResult locationResult) {
            super.onLocationResult(locationResult);

            Location current = locationResult.getLastLocation();

            mMap.clear();
            //removing marker
            /*if (mMap != null){
                mMap.clear();
            }*/

            //custom image marker on the maps
            //how to show nearby schools
            //how to add on click listener on the map


            // Add a marker in Sydney and move the camera
            LatLng myLocation = new LatLng(current.getLatitude(), current.getLongitude());
//            Marker marker = mMap.addMarker(new MarkerOptions().position(myLocation).title("You are here").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));//changing the color of the marker

            Marker marker = mMap.addMarker(new MarkerOptions().position(myLocation)
                                .title("You are here").draggable(true)
                                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.location)));//changing the icon of the marker
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 15f));

        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 454 && grantResults.length > 0){
            Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
        }
    }
    //https://www.youtube.com/watch?v=OknMZUnTyds&list=PLgCYzUzKIBE-vInwQhGSdnbyJ62nixHCt
//youtube-dl -i -f mp4 --yes-playlist 'https://www.youtube.com/watch?v=7Vy8970q0Xc&list=PLwJ2VKmefmxpUJEGB1ff6yUZ5Zd7Gegn2'
   // youtube-dl -i RDKBZWDM5bFpg





    //https://www.youtube.com/watch?v=RQxY7rrZATU&list=PLgCYzUzKIBE-SZUrVOsbYMzH7tPigT3gi

    //https://www.youtube.com/watch?v=EU7PRmCpx-0&list=PLillGF-RfqbYhQsN5WMXy6VsDMKGadrJ-
    /*public void zoom(View view) {

        if (view.getId() == R.id.zoomOut){
            mMap.animateCamera(CameraUpdateFactory.zoomOut());
        }
        else if (view.getId() == R.id.zoomIn){
            mMap.animateCamera(CameraUpdateFactory.zoomIn());
        }

    }

    public void changeType(View view) {
        if (mMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL)
        {
            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        }
        else if (mMap.getMapType() == GoogleMap.MAP_TYPE_HYBRID)
        {
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        }
        else if (mMap.getMapType() == GoogleMap.MAP_TYPE_SATELLITE)
        {
            mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        }
        else if (mMap.getMapType() == GoogleMap.MAP_TYPE_TERRAIN)
        {
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }

    }*/
}
