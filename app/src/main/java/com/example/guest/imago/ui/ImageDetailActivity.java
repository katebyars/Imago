package com.example.guest.imago.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.guest.imago.Constants;
import com.example.guest.imago.R;
import com.example.guest.imago.adapters.ImagePagerAdapter;
import com.example.guest.imago.models.Image;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ImageDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private ImagePagerAdapter adapterViewPager;
    ArrayList<Image> mImages = new ArrayList<>();
    private String mSource;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        ButterKnife.bind(this);

        mSource = getIntent().getStringExtra(Constants.KEY_SOURCE);

        mImages = Parcels.unwrap(getIntent().getParcelableExtra(Constants.EXTRA_KEY_IMAGES));
        int startingPosition = getIntent().getIntExtra(Constants.EXTRA_KEY_POSITION, 0);

        adapterViewPager = new ImagePagerAdapter(getSupportFragmentManager(), mImages, mSource);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);


    }
}