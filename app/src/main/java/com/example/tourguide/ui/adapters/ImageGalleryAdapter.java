package com.example.tourguide.ui.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tourguide.R;

import io.github.sporklibrary.Spork;
import io.github.sporklibrary.android.annotations.BindView;

/**
 * This is the adapter class of the city places class which handles recyclerview,
 * displaying the images of the place as a horizontal list
 */

public class ImageGalleryAdapter extends RecyclerView.Adapter<ImageGalleryAdapter.MyViewHolder> {

    // current context
    Context mContext;
    // Current activity
    Activity mActivity;
    // list of image resources to be displayed
    int[] Resources;

    public ImageGalleryAdapter(Context context, Activity activity, int[] imageResources) {
        mContext = context;
        mActivity = activity;
        Resources = imageResources;
    }

    /**
     * Holder class responsible for holding the list item components
     */
    @SuppressLint("NonConstantResourceId")
    public class MyViewHolder extends RecyclerView.ViewHolder{

        // image view that will handle displaying the images
        @BindView(R.id.ImageGalleryView)
        ImageView myImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            Spork.bind(this);
        }
    }

    @NonNull
    @Override
    public ImageGalleryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_image, parent, false);
        return new ImageGalleryAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageGalleryAdapter.MyViewHolder holder, int position) {
        // getting the current object being displayed
        int CP = Resources[position];

        // setting current image to the image view to display on the screen
        Glide.with(mContext).load(CP).into(holder.myImage);
    }

    @Override
    public int getItemCount() {
        return Resources.length;
    }

}
