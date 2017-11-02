package com.example.guest.imago.adapters;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.guest.imago.models.Image;
import com.example.guest.imago.ui.ImageDetailFragment;

import java.util.ArrayList;

public class ImagePagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Image> mImages;
    private String mSource;

    public ImagePagerAdapter(FragmentManager fm, ArrayList<Image> images, String source) {
        super(fm);
        mImages = images;
        mSource = source;

    }

    @Override
    public Fragment getItem(int position) {
        return ImageDetailFragment.newInstance(mImages, position, mSource);
    }

    @Override
    public int getCount() {
        return mImages.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mImages.get(position).getImageName();
    }
}

