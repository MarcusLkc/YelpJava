package com.bluedungeon.javayelper;

/**
 * Created by fdsale on 5/20/17.
 */



public class Data {

    private String description;

    private String imagePath;

    public Data(String imagePath, String description) {
        this.imagePath = imagePath;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getImagePath() {
        return imagePath;
    }

}