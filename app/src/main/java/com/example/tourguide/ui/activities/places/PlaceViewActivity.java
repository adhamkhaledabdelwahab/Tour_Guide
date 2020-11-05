package com.example.tourguide.ui.activities.places;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tourguide.R;
import com.example.tourguide.data.model.CityEvents;
import com.example.tourguide.data.model.CityPlaces;
import com.example.tourguide.ui.activities.CityLocationActivity;
import com.example.tourguide.ui.adapters.ImageGalleryAdapter;

import io.github.sporklibrary.Spork;
import io.github.sporklibrary.android.annotations.BindClick;
import io.github.sporklibrary.android.annotations.BindLayout;
import io.github.sporklibrary.android.annotations.BindView;

/**
 * This is the class responsible for generating and handling place view activity,
 * that displays all about the current place
 */

@SuppressLint("NonConstantResourceId")
@BindLayout(R.layout.activity_places_view)
public class PlaceViewActivity extends AppCompatActivity {

    // image gallery recycler view of the place
    @BindView(R.id.ImageGallery)
    RecyclerView recyclerView;

    // text view to display about place text
    @BindView(R.id.aboutText)
    TextView about;

    // text view to display place location text (ex: About place name)
    @BindView(R.id.StringLocation)
    TextView stringLocation;

    // text view to display about the place
    @BindView(R.id.desc)
    TextView desc;

    // parent view of the image gallery and its next and prev button
    @BindView(R.id.placeViewParent)
    RelativeLayout parent;

    // image gallery prev button
    @BindView(R.id.leftArrow)
    ImageButton left;

    // image gallery next button
    @BindView(R.id.rightArrow)
    ImageButton right;

    // image gallery recycler view handler adapter
    ImageGalleryAdapter adapter;

    // int represents width of the screen
    int screenWidth;

    // current place object
    CityPlaces Current;

    // current event object
    CityEvents currentEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Spork.bind(this);


        Intent myIntent = getIntent();
        Current = myIntent.getParcelableExtra("place");
        currentEvent = myIntent.getParcelableExtra("event");
        if (Current == null) {
            setTitle(currentEvent.getName());

            // remove image gallery buttons and it is only one image
            parent.removeView(left);
            parent.removeView(right);

            // getting width of the screen to be used later
            screenWidth = getResources().getDisplayMetrics().widthPixels;

            // set about place text
            String aboutText = " "+currentEvent.getName()+": ";
            about.setText(aboutText);

            // set place location text
            aboutText = "Location: "+currentEvent.getsLocation();
            stringLocation.setText(aboutText);

            // set place description text
            desc.setText(currentEvent.getDescription());

            // setting image gallery adapter
            adapter = new ImageGalleryAdapter(this,this, new int[]{currentEvent.getImage()});
        }else if(currentEvent == null){
            setTitle(Current.getName());

            // test if the current place type is a restaurant to remove image gallery buttons and it is only one image
            if (Current.getPlaceType().equals("restaurant")) {
                parent.removeView(left);
                parent.removeView(right);
            }

            // getting width of the screen to be used later
            screenWidth = getResources().getDisplayMetrics().widthPixels;

            // set about place text
            String aboutText = " "+Current.getName()+": ";
            about.setText(aboutText);

            // set place location text
            aboutText = "Location: "+Current.getsLocation();
            stringLocation.setText(aboutText);

            // set place description text
            desc.setText(Current.getDescription());

            // setting image gallery adapter
            adapter = new ImageGalleryAdapter(this,this, Current.getImages());
        }

        // getting and setting the adapter to the image gallery recycler view
        LinearLayoutManager llm = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);

    }

    // handling onClick action of the image gallery prev button
    @BindClick(R.id.leftArrow)
    private void prevImage(){
        recyclerView.post(() -> recyclerView.scrollBy(-screenWidth,0));
    }

    // handling onClick action of the image gallery next button
    @BindClick(R.id.rightArrow)
    private void nextImage(){
        recyclerView.post(() -> recyclerView.scrollBy(screenWidth,0));
    }

    // handling onClick action of the place location image button
    @BindClick(R.id.PlaceLocation)
    private void placeLocation(){
        Intent CityLocation = new Intent(PlaceViewActivity.this, CityLocationActivity.class);
        CityLocation.putExtra("Places", (Parcelable) Current);
        CityLocation.putExtra("Events", (Parcelable) currentEvent);
        startActivity(CityLocation);
    }

    // handling default back button onClick action
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}