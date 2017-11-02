package com.example.guest.imago.util;

import com.example.guest.imago.models.Image;

import java.util.ArrayList;

/**
 * Created by Guest on 11/1/17.
 */

public interface OnImageSelectedListener {

    public void onImageSelectedListener(Integer position, ArrayList<Image> images, String source);
}
