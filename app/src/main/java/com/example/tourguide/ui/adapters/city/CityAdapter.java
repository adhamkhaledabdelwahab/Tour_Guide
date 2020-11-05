package com.example.tourguide.ui.adapters.city;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tourguide.R;
import com.example.tourguide.data.model.Cities;
import com.example.tourguide.ui.activities.city.AboutCityActivity;

import java.util.ArrayList;

import io.github.sporklibrary.Spork;
import io.github.sporklibrary.android.annotations.BindClick;
import io.github.sporklibrary.android.annotations.BindView;

/**
 * This is the adapter class of the city class which handles recyclerview displaying the cities
 */

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.MyViewHolder> {
    // list of cities to be displayed
    private final ArrayList<Cities> mValues;
    // Current activity
    private final Activity mActivity;
    // current context
    Context mContext;

    public CityAdapter(Context context, Activity activity, ArrayList<Cities> items) {
        mValues = items;
        mActivity = activity;
        mContext = context;
    }

    /**
     * Holder class responsible for holding the list item components
     */
    @SuppressLint("NonConstantResourceId")
    public class MyViewHolder extends RecyclerView.ViewHolder{

        // text view displaying city name
        @BindView(R.id.City_name)
        TextView Town;

        // text view displaying country name
        @BindView(R.id.City_Country_name)
        TextView Country;

        // image view displaying city image
        @BindView(R.id.City_image)
        ImageView City_image;

        // relative layout container of the component
        @BindView(R.id.parent)
        RelativeLayout parent;

        // current object being displayed
        Cities currentObject;

        public void setCurrentObject(Cities currentObject) {
            this.currentObject = currentObject;
        }

        public MyViewHolder(View itemView) {
            super(itemView);
            Spork.bind(this);
        }

        // onClick handler to display city new page on click on its country name text view
        // with sending an extra data which is the current city object to display its data
        @BindClick(R.id.City_Country_name)
        private void onClick() {
            Intent aboutCity = new Intent(mContext, AboutCityActivity.class);
            aboutCity.putExtra("Title", (Parcelable) currentObject);
            mContext.startActivity(aboutCity);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // getting the current object being displayed
        Cities CP = mValues.get(position);

        // getting the height of the android phone resolution
        Display mDisplay = mActivity.getWindowManager().getDefaultDisplay();
        final int height = mDisplay.getHeight();

        // getting the height of the action bar if exists
        int actionBarHeight= 0;
        TypedValue tv = new TypedValue();
        if (mActivity.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
        {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data,mActivity.getResources().getDisplayMetrics());
        }

        // getting the height of the status bar if exists
        int statusBarHeight = 0;
        int resource = mContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resource > 0) {
            statusBarHeight = mContext.getResources().getDimensionPixelSize(resource);
        }

        // setting the height of the list item to be half of the screen of the phone
        int newHeight = (height-actionBarHeight-statusBarHeight)/2;
        ViewGroup.LayoutParams params = holder.parent.getLayoutParams();
        params.height = newHeight;
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;

        // setting data to the list item component
        holder.setCurrentObject(CP);
        holder.Town.setText(CP.getName());
        holder.Country.setText(CP.getCountry());
        Glide.with(mContext).load(CP.getCityImage()).into(holder.City_image);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

}