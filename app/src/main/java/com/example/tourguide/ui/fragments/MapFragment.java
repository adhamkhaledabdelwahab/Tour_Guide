package com.example.tourguide.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tourguide.R;
import com.example.tourguide.data.model.Cities;
import com.example.tourguide.data.model.CityEvents;
import com.example.tourguide.data.model.CityPlaces;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Objects;

/**
 * This class which responsible for handling maps on mapFragment
 */

public class MapFragment extends Fragment {

    // city object to be displayed on the map
    Cities Current;

    // city place object to be displayed on the map
    CityPlaces CurrentPlace;

    // city event object to be displayed on the map
    CityEvents CurrentEvent;

    // constructor getting city object to display location on map
    public MapFragment(Cities C){
        Current = C;
        CurrentPlace = null;
    }

    // constructor getting city place object to display location on map
    public MapFragment(CityPlaces C){
        CurrentPlace = C;
        Current = null;
    }

    // constructor getting city place object to display location on map
    public MapFragment(CityEvents C){
        CurrentEvent = C;
        CurrentPlace = null;
        Current = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Initialize view
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        //Initialize map fragment
        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.google_map);

        // test which object has been initialized in the constructor to display on the map
        if (Current == null && CurrentEvent == null) {
            //Async map
            Objects.requireNonNull(supportMapFragment).getMapAsync(googleMap -> {
                //when map is loaded
                //Initialize marker map
                MarkerOptions markerOptions =  new MarkerOptions();
                //Set position of marker
                markerOptions.position(CurrentPlace.getLocation());
                //Set title of marker
                markerOptions.title(CurrentPlace.getName());
                //Remove all markers
                googleMap.clear();
                //Animating to zoom the marker
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                        CurrentPlace.getLocation(), 10
                ));
                //Add marker on map
                googleMap.addMarker(markerOptions);
            });
        }else if (CurrentPlace == null && CurrentEvent == null) {
            //Async map
            Objects.requireNonNull(supportMapFragment).getMapAsync(googleMap -> {
                //when map is loaded
                //Initialize marker map
                MarkerOptions markerOptions =  new MarkerOptions();
                //Set position of marker
                markerOptions.position(Current.getCityLocation());
                //Set title of marker
                markerOptions.title(Current.getName());
                //Remove all markers
                googleMap.clear();
                //Animating to zoom the marker
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                        Current.getCityLocation(), 10
                ));
                //Add marker on map
                googleMap.addMarker(markerOptions);
            });
        }else if (CurrentPlace == null && Current == null){
            //Async map
            Objects.requireNonNull(supportMapFragment).getMapAsync(googleMap -> {
                //when map is loaded
                //Initialize marker map
                MarkerOptions markerOptions =  new MarkerOptions();
                //Set position of marker
                markerOptions.position(CurrentEvent.getLocation());
                //Set title of marker
                markerOptions.title(CurrentEvent.getName());
                //Remove all markers
                googleMap.clear();
                //Animating to zoom the marker
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                        CurrentEvent.getLocation(), 10
                ));
                //Add marker on map
                googleMap.addMarker(markerOptions);
            });
        }


        //Return view
        return view;
    }
}