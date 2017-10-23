package com.example.guest.imago.ui;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.imago.models.Image;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ImageDetailFragment extends Fragment implements View.OnClickListener {
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;

    @Bind(R.id.imageImageView) ImageView mImageLabel;
    @Bind(R.id.imageNameTextView) TextView mNameLabel;
    @Bind(R.id.imagePhotographerUserNameTextView) TextView mProfileNameLabel;
    @Bind(R.id.imagePhotographerwebsiteTextView) TextView mWebsiteLabel;
    @Bind(R.id.saveImageButton)

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

        mNameLabel.setText(mImage.getImageName());
        mProfileNameLabel.setText(mProfileNameLabel.getProfileNameLabel());
        mWebsiteLabel.setText(mWebsiteLabel.getWebsiteLable());

        mWebsiteLabel.setOnClickListener(this);
//        mPhoneLabel.setOnClickListener(this);
//        mAddressLabel.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mWebsiteLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(mWebsiteLabel.getWebsite()));
            startActivity(webIntent);
        }
//        if (v == mPhoneLabel) {
//            Intent phoneIntent = new Intent(Intent.ACTION_DIAL,
//                    Uri.parse("tel:" + mRestaurant.getPhone()));
//            startActivity(phoneIntent);
//        }
//        if (v == mAddressLabel) {
//            Intent mapIntent = new Intent(Intent.ACTION_VIEW,
//                    Uri.parse("geo:" + mRestaurant.getLatitude()
//                            + "," + mRestaurant.getLongitude()
//                            + "?q=(" + mRestaurant.getName() + ")"));
//            startActivity(mapIntent);
//        }
    }
}
