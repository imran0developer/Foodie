package com.imran.foodie.models;

public class RestaurantData {
    private String id;
    private String name;
    private String tags;
    private String rating;
    private String discount;
    private String primary_image;
    private String distance;

    public RestaurantData(String id, String name, String tags, String rating, String discount, String primary_image, String distance) {
        this.id = id;
        this.name = name;
        this.tags = tags;
        this.rating = rating;
        this.discount = discount;
        this.primary_image = primary_image;
        this.distance = distance;
    }

    public RestaurantData() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getPrimaryImage() {
        return primary_image;
    }

    public void setPrimaryImage(String primaryImage) {
        this.primary_image = primaryImage;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
