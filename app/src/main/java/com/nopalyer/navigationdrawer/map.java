package com.nopalyer.navigationdrawer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class map extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment=(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map=googleMap;

        LatLng Himachal =new LatLng(31.709237, 76.528043);
        map.addMarker(new MarkerOptions().position(Himachal).title("NIT HAMIRPUR"));
        map.moveCamera(CameraUpdateFactory.newLatLng(Himachal));

     /*   LatLng Ground =new LatLng(31.706069, 76.524679);
        map.addMarker(new MarkerOptions().position(Ground).title("NIT HAMIRPUR Ground"));
        map.moveCamera(CameraUpdateFactory.newLatLng(Ground));

        LatLng Park =new LatLng(31.707247, 76.528445);
        map.addMarker(new MarkerOptions().position(Park).title("Students Park"));
        map.moveCamera(CameraUpdateFactory.newLatLng(Park));

        LatLng health =new LatLng(31.706087, 76.527694);
        map.addMarker(new MarkerOptions().position(health).title("NITH Health Center"));
        map.moveCamera(CameraUpdateFactory.newLatLng(health));

        LatLng ambika =new LatLng(31.704342, 76.525385);
        map.addMarker(new MarkerOptions().position(ambika).title("Ambika Girls Hostel"));
        map.moveCamera(CameraUpdateFactory.newLatLng(ambika));

        LatLng kbh =new LatLng(31.710352, 76.526616);
        map.addMarker(new MarkerOptions().position(kbh).title("Kailash Boys Hostel"));
        map.moveCamera(CameraUpdateFactory.newLatLng(kbh)); */

    }
}
