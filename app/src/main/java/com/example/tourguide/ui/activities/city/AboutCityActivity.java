package com.example.tourguide.ui.activities.city;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.tourguide.R;
import com.example.tourguide.data.model.Cities;
import com.example.tourguide.ui.activities.CityLocationActivity;
import com.example.tourguide.ui.activities.places.PlacesActivity;

import io.github.sporklibrary.Spork;
import io.github.sporklibrary.android.annotations.BindClick;
import io.github.sporklibrary.android.annotations.BindLayout;
import io.github.sporklibrary.android.annotations.BindView;

/**
 * This is the class responsible for generating the personal page of the city
 */

@SuppressLint("NonConstantResourceId")
@BindLayout(R.layout.activity_city_about)
public class AboutCityActivity extends AppCompatActivity {

    // current city
    Cities Current;

    // image view to display city location image
    @BindView(R.id.City_location_image)
    ImageView location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Spork.bind(this);

        // get the extra data sent by previous activity (current city)
        Intent myIntent = getIntent();
        Current = myIntent.getParcelableExtra("Title");
        setTitle(Current.getName()+"("+Current.getCountry()+")");

        // displaying city location image
        Glide.with(this).load(Current.getCityLocationImage()).into(location);
    }

    // setting onClick to places textView to open city places activity
    // sending the current city and type of displayed places
    @BindClick(R.id.places_click)
    private void placesCLick(){
        Intent places = new Intent(AboutCityActivity.this, PlacesActivity.class);
        places.putExtra("Title", (Parcelable) Current);
        places.putExtra("type","place");
        startActivity(places);
    }

    // setting onClick to restaurants textView to open city restaurants activity
    // sending the current city and type of displayed places
    @BindClick(R.id.Resturants_click)
    private void resturantsCLick(){
        Intent places = new Intent(AboutCityActivity.this, PlacesActivity.class);
        places.putExtra("Title", (Parcelable) Current);
        places.putExtra("type","restaurant");
        startActivity(places);
    }

    // setting onClick to events textView to open city events activity
    @BindClick(R.id.Events_click)
    private void eventsCLick(){
        Intent places = new Intent(AboutCityActivity.this, PlacesActivity.class);
        places.putExtra("Title", (Parcelable) Current);
        places.putExtra("type","event");
        startActivity(places);
    }

    // setting onClick to city location imageView to open city location on the map
    // sending the current city
    @BindClick(R.id.City_location_image_click)
    private void mapCLick(){
        Intent CityLocation = new Intent(AboutCityActivity.this, CityLocationActivity.class);
        CityLocation.putExtra("City", (Parcelable) Current);
        startActivity(CityLocation);
    }
}