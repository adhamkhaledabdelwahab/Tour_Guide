package com.example.tourguide.ui.adapters.places;

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
import com.example.tourguide.data.model.CityPlaces;
import com.example.tourguide.ui.activities.places.PlaceViewActivity;

import java.util.ArrayList;

import io.github.sporklibrary.Spork;
import io.github.sporklibrary.android.annotations.BindClick;
import io.github.sporklibrary.android.annotations.BindView;

/**
 * This is the adapter class responsible for handling list of places on the recyclerview
 */

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.mViewHolder> {

    // list of places to be displayed
    private final ArrayList<CityPlaces> mValues;
    // Current activity
    private final Activity mActivity;
    // current context
    Context mContext;

    public PlacesAdapter(Context context, Activity activity, ArrayList<CityPlaces> items) {
        mValues = items;
        mActivity = activity;
        mContext = context;
    }

    @SuppressLint("NonConstantResourceId")
    public class mViewHolder extends RecyclerView.ViewHolder{

        // text view displaying place name
        @BindView(R.id.City_name)
        TextView Town;

        // an extra textView used in another adapter but here will be removed
        @BindView(R.id.City_Country_name)
        TextView Country;

        // image view displaying place image
        @BindView(R.id.City_image)
        ImageView City_image;

        // relative layout container of the component
        @BindView(R.id.parent)
        RelativeLayout parent;

        // current object being displayed
        CityPlaces currentObject;

        public void setCurrentObject(CityPlaces currentObject) {
            this.currentObject = currentObject;
        }

        public mViewHolder(View itemView) {
            super(itemView);
            Spork.bind(this);
        }

        @BindClick(R.id.City_name)
        private void onClick() {
            Intent aboutCity = new Intent(mContext, PlaceViewActivity.class);
            aboutCity.putExtra("place", (Parcelable) currentObject);
            mContext.startActivity(aboutCity);
        }
    }

    @NonNull
    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new mViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlacesAdapter.mViewHolder holder, int position) {
        // getting the current object being displayed
        CityPlaces CP = mValues.get(position);

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

        // setting the height of the list item to be 1/3 of the screen of the phone
        int newHeight = (height-actionBarHeight-statusBarHeight)/3;
        ViewGroup.LayoutParams params = holder.parent.getLayoutParams();
        params.height = newHeight;
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;

        // setting data to the list item component
        holder.parent.removeView(holder.Country);
        holder.setCurrentObject(CP);
        holder.Town.setText(CP.getName());
        holder.Town.setTextSize(40);
        Glide.with(mContext).load(CP.getImages()[0]).into(holder.City_image);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
}
