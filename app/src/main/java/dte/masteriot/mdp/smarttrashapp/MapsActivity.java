package dte.masteriot.mdp.smarttrashapp;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import dte.masteriot.mdp.smarttrashapp.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    Intent inputIntent;
    String name, lat, lon, orgLevel, plasLevel, papLevel, glaLevel, temp, hum, xAxis, yAxis, zAxis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Getting the Intent
        inputIntent = getIntent();

        //Getting the Values coming from First Activity extracting them from the Intent received
        name = inputIntent.getStringExtra("containerName");
        String location = inputIntent.getStringExtra("location");

        location = location.substring(10, location.length()-1);

        lat = location.substring(0, location.indexOf(","));
        lon = location.substring(location.indexOf(",")+1);

        orgLevel = inputIntent.getStringExtra("orgLevel");
        plasLevel = inputIntent.getStringExtra("plasLevel");
        papLevel = inputIntent.getStringExtra("papLevel");
        glaLevel = inputIntent.getStringExtra("glaLevel");

        temp = inputIntent.getStringExtra("temp");
        hum = inputIntent.getStringExtra("hum");

        xAxis = inputIntent.getStringExtra("xAxis");
        yAxis = inputIntent.getStringExtra("yAxis");
        zAxis = inputIntent.getStringExtra("zAxis");
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
        //LatLng sydney = new LatLng(-34, 151);
        LatLng garden = new LatLng(Double.valueOf(lat), Double.valueOf(lon));
        mMap.addMarker(new MarkerOptions().position(garden).title("Container in " + name).snippet("-->Click here to see the details<--"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(garden));
        mMap.setMinZoomPreference(13);

        mMap.setOnInfoWindowClickListener(this);
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

            // Creating Intent For Navigating to VisitActivity (Explicit Intent)
            Intent i = new Intent(MapsActivity.this, ContainerActivity.class);

            // Adding values to the intent to pass them to VisitActivity
            i.putExtra("containerName", name);
            i.putExtra("orgLevel", orgLevel);
            i.putExtra("plasLevel", plasLevel);
            i.putExtra("papLevel", papLevel);
            i.putExtra("glaLevel", glaLevel);
            i.putExtra("temp", temp);
            i.putExtra("hum", hum);
            i.putExtra("xAxis", xAxis);
            i.putExtra("yAxis", yAxis);
            i.putExtra("zAxis", zAxis);

            // Once the intent is parametrized, start the VisitActivity:
            startActivity(i);


    }
}