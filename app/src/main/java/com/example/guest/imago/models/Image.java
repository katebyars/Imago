package com.example.guest.imago.models;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class Image {

     String index;
     String imageUrl;
     String imageName;
     String imagePhotographerUserName;
     String imageWebsiteLabel;
     String location;


    public Image () {}

    public Image(String imageUrl, String imageName, String imagePhotographerUserName, String imageWebsiteLabel, String location) {
        this.imageUrl = imageUrl;
        this.imageName = imageName;
        this.imagePhotographerUserName = imagePhotographerUserName;
        this.imageWebsiteLabel = imageWebsiteLabel;
        this.location = location;
        this.index = "not_specfied";
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImagePhotographerUserName() {
        return imagePhotographerUserName;
    }

    public void setImagePhotographerUserName(String imagePhotographerUserName) {
        this.imagePhotographerUserName = imagePhotographerUserName;
    }

    public String getImageWebsiteLabel() {
        return imageWebsiteLabel;
    }

    public void setImageWebsiteLabel(String imageWebsiteLabel) {
        this.imageWebsiteLabel = imageWebsiteLabel;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
