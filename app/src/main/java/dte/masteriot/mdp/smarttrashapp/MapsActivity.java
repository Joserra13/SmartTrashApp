package dte.masteriot.mdp.smarttrashapp;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import dte.masteriot.mdp.smarttrashapp.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    Intent inputIntent;
    String name, lat, lon;

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
        name = inputIntent.getStringExtra("container_name");
        String location = inputIntent.getStringExtra("location");

        location = location.substring(10, location.length()-1);

        lat = location.substring(0, location.indexOf(","));
        lon = location.substring(location.indexOf(",")+1);
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
        mMap.addMarker(new MarkerOptions().position(garden).title("Marker in " + name).snippet("Storage: 100%, Temp: 50.4ºC, Hum: 50.4%, Vertical"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(garden));
        mMap.setMinZoomPreference(13);
    }
}