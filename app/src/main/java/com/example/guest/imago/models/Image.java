package com.example.guest.imago.models;

import java.util.ArrayList;


public class Image {

    private String imageUrl;
    private String imageName;
    private String imagePhotographerUserName;
    private String imageWebsiteLabel;
    private String location;

    public Image(String imageUrl, String imageName, String imagePhotographerUserName, String imageWebsiteLabel, String location) {
        this.imageUrl = imageUrl;
        this.imageName = imageName;
        this.imagePhotographerUserName = imagePhotographerUserName;
        this.imageWebsiteLabel = imageWebsiteLabel;
        this.location = location;
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
