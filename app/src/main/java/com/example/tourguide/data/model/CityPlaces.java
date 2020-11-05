package com.example.tourguide.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

/**
 * This that class of the city places object with all its attributes,
 * indicating name of the place,
 * type of the place tour or restaurant,
 * images of the place,
 * about the place,
 * location text
 * and actual location on the map
 */

public class CityPlaces implements Serializable, Parcelable {

    // list of images of the places
    private final int[] images;
    // about the places
    private final String description;
    // name of the place
    private final String name;
    // place location object
    private final LatLng location;
    // place text location
    private final String sLocation;
    // type of the place (tour or restaurant)
    private final String placeType;

    protected CityPlaces(Parcel in) {
        images = in.createIntArray();
        description = in.readString();
        name = in.readString();
        location = in.readParcelable(LatLng.class.getClassLoader());
        sLocation = in.readString();
        placeType = in.readString();
    }

    public String getPlaceType() {
        return placeType;
    }

    public static final Creator<CityPlaces> CREATOR = new Creator<CityPlaces>() {
        @Override
        public CityPlaces createFromParcel(Parcel in) {
            return new CityPlaces(in);
        }

        @Override
        public CityPlaces[] newArray(int size) {
            return new CityPlaces[size];
        }
    };

    public int[] getImages() {
        return images;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public LatLng getLocation() {
        return location;
    }

    public String getsLocation() {
        return sLocation;
    }

    public CityPlaces(String type, int[] images, String description, String name, LatLng loc, String lc) {
        this.images = images;
        this.description = description;
        this.name = name;
        location = loc;
        sLocation = lc;
        placeType = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeIntArray(images);
        parcel.writeString(description);
        parcel.writeString(name);
        parcel.writeParcelable(location, i);
        parcel.writeString(sLocation);
        parcel.writeString(placeType);
    }
}
