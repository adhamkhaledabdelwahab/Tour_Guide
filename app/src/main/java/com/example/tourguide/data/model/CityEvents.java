package com.example.tourguide.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

public class CityEvents implements Serializable, Parcelable {

    // title image of the event
    private final int image;
    // about the event
    private final String description;
    // title of the event
    private final String name;
    // event location object
    private final LatLng location;
    // event text location
    private final String sLocation;

    protected CityEvents(Parcel in) {
        image = in.readInt();
        description = in.readString();
        name = in.readString();
        location = in.readParcelable(LatLng.class.getClassLoader());
        sLocation = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(image);
        dest.writeString(description);
        dest.writeString(name);
        dest.writeParcelable(location, flags);
        dest.writeString(sLocation);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CityEvents> CREATOR = new Creator<CityEvents>() {
        @Override
        public CityEvents createFromParcel(Parcel in) {
            return new CityEvents(in);
        }

        @Override
        public CityEvents[] newArray(int size) {
            return new CityEvents[size];
        }
    };

    public int getImage() {
        return image;
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

    public CityEvents(int image, String description, String name, LatLng location, String sLocation) {
        this.image = image;
        this.description = description;
        this.name = name;
        this.location = location;
        this.sLocation = sLocation;
    }
}
