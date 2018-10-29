package com.chemutai.gpsandmaps;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.media.MediaPlayer;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity {

    TextView txtLatitude, txtLongitude, txtSpeed;
    LocationRequest mRequest;
    FusedLocationProviderClient mProvider;
    Button btnSound, btnNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLatitude = findViewById(R.id.txtLatitude);
        txtLongitude = findViewById(R.id.txtLongitude);
        txtSpeed = findViewById(R.id.txtSpeed);
        btnSound = findViewById(R.id.btnSound);
        btnNav = findViewById(R.id.btnNav);

        final MediaPlayer mdp = MediaPlayer.create(this, R.raw.taylor);

        btnSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mdp.start();
            }
        });

        mProvider = LocationServices.getFusedLocationProviderClient(this);//get providers that can provide gps location
        mRequest = new LocationRequest();
        mRequest.setInterval(5000);//time in seconds
        mRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);//which provider to use i.e. gps, wifi
        mRequest.setSmallestDisplacement(10);//measure distance in meters

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



           txtLatitude.setText(current.getLatitude()+"");
           txtLongitude.setText(current.getLongitude()+"");

           Double speed = current.getSpeed()* 3.6;
           txtSpeed.setText(speed+"");
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

    public void navigateMap(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

}
