package com.example.guest.imago.ui;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guest.imago.Constants;
import com.example.guest.imago.R;
import com.example.guest.imago.models.Image;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ImageDetailFragment extends Fragment implements View.OnClickListener {
    private static final int MAX_WIDTH = 1000;
    private static final int MAX_HEIGHT = 1000;

    @Bind(R.id.imageImageView) ImageView mImageLabel;
    @Bind(R.id.imagePhotographerUserNameTextView) TextView mProfileNameLabel;
    @Bind(R.id.imagePhotographerwebsiteTextView) TextView mWebsiteLabel;
    @Bind(R.id.saveImageButton) Button mSaveImageButton;

    private Image mImage;

    public static ImageDetailFragment newInstance(Image image) {
        ImageDetailFragment imageDetailFragment = new ImageDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("image", Parcels.wrap(image));
        imageDetailFragment.setArguments(args);
        return imageDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImage = Parcels.unwrap(getArguments().getParcelable("image"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext())
                .load(mImage.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mImageLabel);

        mProfileNameLabel.setText(mImage.getImagePhotographerUserName());
        mWebsiteLabel.setText(mImage.getImageWebsiteLabel());

        mWebsiteLabel.setOnClickListener(this);
        mProfileNameLabel.setOnClickListener(this);
        mSaveImageButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mSaveImageButton) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();

            DatabaseReference imageRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_SAVED_IMAGE)
                    .child(uid);

            DatabaseReference pushRef = imageRef.push();
            String pushId = pushRef.getKey();
            mImage.setPushId(pushId);
            pushRef.setValue(mImage);

            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
        if (v == mWebsiteLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(mImage.getImageWebsiteLabel()));
            startActivity(webIntent);
        }
    }
}
