package com.example.guest.imago.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.imago.R;
import com.example.guest.imago.models.Image;
import com.squareup.picasso.Picasso;

import static com.example.guest.imago.R.id.imageImageView;

public class FirebaseImageViewHolder extends RecyclerView.ViewHolder {
    private static final int MAX_WIDTH = 1000;
    private static final int MAX_HEIGHT = 1000;

    View mView;
    Context mContext;
    public ImageView mDragIcon;

    public FirebaseImageViewHolder(View itemView) {
        super(itemView);
        mView = itemView;

        mContext = itemView.getContext();
    }

    public void bindImage(Image image) {
        mDragIcon = (ImageView) mView.findViewById(R.id.dragIcon);
        ImageView savedImage = (ImageView) mView.findViewById(imageImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.imagePhotographerUserNameTextView);
        TextView websiteTextView = (TextView) mView.findViewById(R.id.imagePhotographerwebsiteTextView);

        Picasso.with(mContext)
                .load(image.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(savedImage);

//        nameTextView.setText(image.getImagePhotographerUserName());
//        websiteTextView.setText(image.getImageWebsiteLabel());


    }

}