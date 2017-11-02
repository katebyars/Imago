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
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    View mView;
    Context mContext;
    public ImageView mImageImageView;

    public FirebaseImageViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindImage(Image image) {
        mImageImageView = (ImageView) mView.findViewById(imageImageView);
        Picasso.with(mContext)
                .load(image.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mImageImageView);

    }

}