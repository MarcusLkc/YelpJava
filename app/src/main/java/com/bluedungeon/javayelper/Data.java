package com.bluedungeon.javayelper;


public class Data {

    private String description;

    private String imagePath;

    private String website;

    static int count = 0;

    public Data(String imagePath, String description, String website) {
        this.imagePath = imagePath;
        this.description = description;
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getWebsite() { return website; }

    static void increment()
    {
        count++;
    }

}