package com.example.tourguide.ui.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.tourguide.R;
import com.example.tourguide.data.model.Cities;
import com.example.tourguide.data.model.CityEvents;
import com.example.tourguide.data.model.CityPlaces;
import com.example.tourguide.ui.fragments.MapFragment;

import java.util.Objects;

import io.github.sporklibrary.Spork;
import io.github.sporklibrary.android.annotations.BindLayout;

/**
 * This is the class responsible for generating and handling the activity that holds map fragment
 */

@SuppressLint("NonConstantResourceId")
@BindLayout(R.layout.activity_city_location)
public class CityLocationActivity extends AppCompatActivity {
    Cities Current;
    CityPlaces CurrentPlace;
    CityEvents CurrentEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Spork.bind(this);

        // allowing action bar back button
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        // get the extra data sent by previous activity
        Intent myIntent = getIntent();
        Current = myIntent.getParcelableExtra("City");
        CurrentPlace = myIntent.getParcelableExtra("Places");
        CurrentEvent = myIntent.getParcelableExtra("Events");

        // test if the sent data was a city object or place object or event object to display on the map
        if (Current == null && CurrentEvent == null) {
            // setting the title of the activity
            setTitle(CurrentPlace.getName());

            //Initialize fragment
            Fragment fragment = new MapFragment(CurrentPlace);

            //open fragment
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, fragment)
                    .commit();
        }else if (CurrentPlace == null && CurrentEvent == null) {
            // setting the title of the activity
            setTitle(Current.getName()+"("+Current.getCountry()+")");

            //Initialize fragment
            Fragment fragment = new MapFragment(Current);

            //open fragment
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, fragment)
                    .commit();
        }else if (CurrentPlace == null && Current == null){
            // setting the title of the activity
            setTitle(CurrentEvent.getName());

            //Initialize fragment
            Fragment fragment = new MapFragment(CurrentEvent);

            //open fragment
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, fragment)
                    .commit();
        }
    }

    // handling default back button on the android
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }

}