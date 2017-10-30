package com.example.guest.imago.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.imago.Constants;
import com.example.guest.imago.R;
import com.example.guest.imago.models.Image;
import com.example.guest.imago.ui.ImageDetailActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import static com.example.guest.imago.R.id.imageImageView;

public class FirebaseImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
    private static final int MAX_WIDTH = 1000;
    private static final int MAX_HEIGHT = 1000;

    View mView;
    Context mContext;

    public FirebaseImageViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindImage(Image image) {
        ImageView imageImageView = (ImageView) mView.findViewById(R.id.imageImageView);
        TextView websiteTextView = (TextView) mView.findViewById(R.id.imagePhotographerwebsiteTextView);
        TextView userNameTextView = (TextView) mView.findViewById(R.id.imagePhotographerUserNameTextView);

        Picasso.with(mContext)
                .load(image.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(imageImageView);
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Image> images = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_SAVED_IMAGE);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    images.add(snapshot.getValue(Image.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, ImageDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("images", Parcels.wrap(images));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}

