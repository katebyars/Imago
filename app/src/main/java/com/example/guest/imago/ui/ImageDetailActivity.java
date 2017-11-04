package com.example.guest.imago.ui;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.guest.imago.R;
import com.example.guest.imago.adapters.ImagePagerAdapter;
import com.example.guest.imago.adapters.ImagePagerAdapter;
import com.example.guest.imago.models.Image;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ImageDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager)
    ViewPager mViewPager;

    private ImagePagerAdapter adapterViewPager;
    ArrayList<Image> mImages = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        ButterKnife.bind(this);

        mImages = Parcels.unwrap(getIntent().getParcelableExtra("images"));

        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new ImagePagerAdapter(getSupportFragmentManager(), mImages);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}