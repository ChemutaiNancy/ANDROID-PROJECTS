package com.chemutai.test;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
/*import android.location.LocationListener;*/
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.karan.churi.PermissionManager.PermissionManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainPageActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    FirebaseAuth auth;
    FirebaseUser user;

    Button btnPickup, btnDestination;

    String userId;
    DatabaseReference mDatabaseReference;
    TextView txtEmail, txtName;
    private GoogleMap mMap;
    GoogleApiClient mClient;
    LocationRequest mRequest;
    LatLng startLatLng, endLatLng;

    Marker currentMarker, destinationMarker;
    PermissionManager mPermissionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        auth=FirebaseAuth.getInstance();
        mPermissionManager = new PermissionManager(){};
        mPermissionManager.checkAndRequestPermissions(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        btnPickup=findViewById(R.id.btnPickup);
        btnDestination=findViewById(R.id.btnDestination);
        setSupportActionBar(toolbar);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        user = FirebaseAuth.getInstance().getCurrentUser();

        if (user == null)
        {
            Intent intent = new Intent(MainPageActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            userId = user.getUid();
            mDatabaseReference = FirebaseDatabase.getInstance().getReference().child(userId);
            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String name = dataSnapshot.child("name").getValue(String.class);
                    String email = dataSnapshot.child("email").getValue(String.class);

                    txtEmail=findViewById(R.id.txtEmail);
                    txtName=findViewById(R.id.txtName);

                    txtName.setText(name);
                    txtEmail.setText(email);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        btnPickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try
                {
                    Intent i = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY).build(MainPageActivity.this);
                    startActivityForResult(i, 200);
                }
                catch (GooglePlayServicesNotAvailableException e)
                {
                    e.printStackTrace();
                }
                catch (GooglePlayServicesRepairableException e)
                {
                    e.printStackTrace();
                }
            }
        });

        btnDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try
                {
                    Intent i = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY).build(MainPageActivity.this);
                    startActivityForResult(i, 400);
                }
                catch (GooglePlayServicesNotAvailableException e)
                {
                    e.printStackTrace();
                }
                catch (GooglePlayServicesRepairableException e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mRequest = new LocationRequest().create();
        mRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mRequest.setInterval(500);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mClient, mRequest, this);
    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    @Override
    public void onLocationChanged(Location location) {
        //LocationServices.FusedLocationApi.removeLocationUpdates(mClient, this);
       if (location == null)
       {
           Toast.makeText(getApplicationContext(), "Location could not be found", Toast.LENGTH_SHORT).show();
       }
       else
       {
           startLatLng = new LatLng(location.getLatitude(), location.getLongitude());

           Geocoder geocoder = new Geocoder(this, Locale.getDefault());
           try
           {
               List<Address> myaddress = geocoder.getFromLocation(startLatLng.latitude, startLatLng.longitude,1);
               String address = myaddress.get(0).getAddressLine(0);
               String city = myaddress.get(0).getLocality();
               btnPickup.setText(address+ " "+city);
           }
           catch (IOException e)
           {
               e.printStackTrace();
           }

           if (currentMarker == null)
           {
               MarkerOptions options = new MarkerOptions();
               options.position(startLatLng);
               options.title("Current Position");
               currentMarker = mMap.addMarker(options);

               mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(startLatLng, 15));
           }
           else
           {
                currentMarker.setPosition(startLatLng);
           }


       }
    }


    public void onStatusChanged(String s, int i, Bundle bundle) {

    }


    public void onProviderEnabled(String s) {

    }

    
    public void onProviderDisabled(String s) {

    }


    public void OnMapReadyCallback(GoogleMap googleMap)
    {

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        mClient.connect();

        /*MarkerOptions options = new MarkerOptions();
        options.position(startLatLng);
        options.title("Current Position");
        mMap.addMarker(options);
        *//*Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();*//*

        // Add a marker in Nairobi and move the camera
           *//*LatLng nairobi = new LatLng(-1.28333, 36.81667);
           mMap.addMarker(new MarkerOptions().position(nairobi).title("Marker in Nairobi"));*//*
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(startLatLng, 15));*/
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mPermissionManager.checkResult(requestCode, permissions, grantResults);

        ArrayList<String> denied_arrays = mPermissionManager.getStatus().get(0).denied;
        
        if (denied_arrays.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "User granted permissions", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_payment) {
            // Handle the camera action
        } else if (id == R.id.nav_trips) {

        } else if (id == R.id.nav_help) {

        } else if (id == R.id.nav_rides) {

        } else if (id == R.id.nav_signOut) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user!=null)
            {
                auth.signOut();
                Intent intent = new Intent(MainPageActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "User is already signed out ", Toast.LENGTH_SHORT).show();
            }
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==200)
        {
            if (resultCode==RESULT_OK)
            {
                Place place = PlaceAutocomplete.getPlace(this,data);
                String name = place.getName().toString();
                startLatLng = place.getLatLng();
                btnPickup.setText(name);

                if (currentMarker==null)
                {
                    MarkerOptions options1 = new MarkerOptions();
                    options1.position(startLatLng);
                    options1.title("Pickup Location");
                    currentMarker = mMap.addMarker(options1);

                    //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(startLatLng, 15));
                }
                else
                {
                    currentMarker.setPosition(startLatLng);
                }
            }
        }
        else if (requestCode==400)
        {
            if (resultCode==RESULT_OK)
            {
                Place myplace = PlaceAutocomplete.getPlace(this, data);
                String name = myplace.getName().toString();
                endLatLng = myplace.getLatLng();
                btnDestination.setText(name);

                if (destinationMarker==null)
                {
                    //we need to put marker
                    MarkerOptions options1 = new MarkerOptions();
                    options1.position(endLatLng);
                    options1.title("Destination");
                    destinationMarker = mMap.addMarker(options1);

                    //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(endLatLng, 15));
                }
                else
                {
                    destinationMarker.setPosition(endLatLng);
                }

            }
        }
    }
}
