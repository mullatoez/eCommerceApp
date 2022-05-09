package com.cs.ecommerceapp.model;

import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("productName")
    private String name;

    private String imageUrl;

    private Double price;

    private String description;

    public Product(String name, String imageUrl, Double price) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
