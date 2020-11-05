package com.example.tourguide.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This is the City class which represents the city with its =>
 * name, country, images, location, places, restaurants and events
 */

public class Cities implements Serializable , Parcelable {
    // city name
    private final String name;
    // city's country name
    private final String country;
    // city image
    private final int cityImage;
    // city location image
    private final int cityLocationImage;
    // city location on map
    private final LatLng CityLocation;
    // city list of places and restaurants
    private ArrayList<CityPlaces> places;
    // city list of events
    private ArrayList<CityEvents> events;

    public void setEvents(ArrayList<CityEvents> events) {
        this.events = events;
    }

    public Cities(String Name, String Country, int Cimage, int CLimage, LatLng CLocation){
        name = Name;
        country = Country;
        cityImage = Cimage;
        cityLocationImage = CLimage;
        CityLocation = CLocation;
    }

    protected Cities(Parcel in) {
        name = in.readString();
        country = in.readString();
        cityImage = in.readInt();
        cityLocationImage = in.readInt();
        CityLocation = in.readParcelable(LatLng.class.getClassLoader());
        places = in.createTypedArrayList(CityPlaces.CREATOR);
        events = in.createTypedArrayList(CityEvents.CREATOR);
    }

    public static final Creator<Cities> CREATOR = new Creator<Cities>() {
        @Override
        public Cities createFromParcel(Parcel in) {
            return new Cities(in);
        }

        @Override
        public Cities[] newArray(int size) {
            return new Cities[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public int getCityImage() {
        return cityImage;
    }

    public int getCityLocationImage() {
        return cityLocationImage;
    }

    public LatLng getCityLocation() {
        return CityLocation;
    }

    public void setPlaces(ArrayList<CityPlaces> plc){
        this.places = plc;
    }

    public ArrayList<CityPlaces> getPlaces() {
        return places;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(country);
        parcel.writeInt(cityImage);
        parcel.writeInt(cityLocationImage);
        parcel.writeParcelable(CityLocation, i);
        parcel.writeTypedList(places);
        parcel.writeTypedList(events);
    }

    public ArrayList<CityEvents> getEvents() {
        return events;
    }
}
