package com.example.tourguide.ui.activities.places;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.tourguide.R;
import com.example.tourguide.data.model.Cities;
import com.example.tourguide.data.model.CityPlaces;
import com.example.tourguide.ui.adapters.events.EventsAdapter;
import com.example.tourguide.ui.adapters.places.PlacesAdapter;

import java.util.ArrayList;

import io.github.sporklibrary.Spork;
import io.github.sporklibrary.android.annotations.BindLayout;
import io.github.sporklibrary.android.annotations.BindView;

/**
 * This class that responsible for generating and handling the places activity,
 * that displays the different places of the city in a list
 */

@SuppressLint("NonConstantResourceId")
@BindLayout(R.layout.activity_places_)
public class PlacesActivity extends AppCompatActivity {

    // RecyclerView to display places in it
    @BindView(R.id.PlavesRecyclerView)
    RecyclerView recyclerView;

    // Current city object
    Cities Current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Spork.bind(this);

        // getting extras sent by  previous activity
        Intent myIntent = getIntent();
        Current = myIntent.getParcelableExtra("Title");

        // this declaration that indicates the type of the places.
        // tour place
        // or
        // restaurant
        // or
        // event
        // and setting current activity title
        String TYPE;
        TYPE = myIntent.getStringExtra("type");
        setTitle(TYPE.toUpperCase()+"S At "+Current.getName()+"("+Current.getCountry()+")");

        if (!TYPE.equals("event")) {
            // setting the list of the places according to the type of the place
            ArrayList<CityPlaces> C = new ArrayList<>();
            for (CityPlaces a:Current.getPlaces()){
                if(a.getPlaceType().equals(TYPE))
                    C.add(a);
            }

            // places adapter that handles the the recyclerview
            // declaring and setting the recyclerview and its adapter
            PlacesAdapter adapter = new PlacesAdapter(this,this, C);
            LinearLayoutManager llm = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
            recyclerView.setLayoutManager(llm);
            recyclerView.setAdapter(adapter);
        }else{
            // declaring and setting the recyclerview and its adapter
            EventsAdapter adapter = new EventsAdapter(this,this,Current.getEvents());
            LinearLayoutManager llm = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
            recyclerView.setLayoutManager(llm);
            recyclerView.setAdapter(adapter);
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