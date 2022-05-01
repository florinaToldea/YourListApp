package com.example.yourlistapp.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.yourlistapp.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends Fragment {

    private final OnMapReadyCallback onMapReadyCallback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {
            LatLng horsens = new LatLng(55.858123, 9.84756);
            LatLng bilka = new LatLng(55.85731069155761, 9.851233656007995);
            LatLng library = new LatLng(55.86221928123412, 9.85077536932157);
            LatLng home = new LatLng(55.85864713842917, 9.84996763168921);

            googleMap.addMarker(new MarkerOptions().position(horsens).title("Horsens"));
            googleMap.addMarker(new MarkerOptions().position(bilka).title("Bilka"));
            googleMap.addMarker(new MarkerOptions().position(library).title("Bog&Ide"));
            googleMap.addMarker(new MarkerOptions().position(home).title("Jysk"));

            float zooming = 16.0f;

            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(horsens, zooming));
        }
    };

    //Initialize view
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstance){
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    //Initialize map fragment
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstance){
        super.onViewCreated(view, savedInstance);

        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);

        if(supportMapFragment != null){
            supportMapFragment.getMapAsync(onMapReadyCallback);
        }
    }
}
