package com.spotippos.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Property {

    private Integer id;
    private String title;
    private Double price;
    private String description;
    @SerializedName("lat")
    private Integer x;
    @SerializedName("long")
    private Integer y;
    private Integer beds;
    private Integer baths;
    private Integer squareMeters;
    private List<String> provinces;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer lat) {
        this.x = lat;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer lon) {
        this.y = lon;
    }

    public Integer getBeds() {
        return beds;
    }

    public void setBeds(Integer beds) {
        this.beds = beds;
    }

    public Integer getBaths() {
        return baths;
    }

    public void setBaths(Integer baths) {
        this.baths = baths;
    }

    public Integer getSquareMeters() {
        return squareMeters;
    }

    public void setSquareMeters(Integer squareMeters) {
        this.squareMeters = squareMeters;
    }

    public List<String> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<String> provinces) {
        this.provinces = provinces;
    }

}
