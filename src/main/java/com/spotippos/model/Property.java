package com.spotippos.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.google.gson.annotations.SerializedName;

public class Property {

    private Integer id;

    @NotNull(message = "É necessário informar o título da propriedade.")
    @NotBlank(message = "É necessário informar a título da propriedade.")
    private String title;

    @NotNull(message = "É necessário informar o preço da propriedade.")
    private Double price;

    @NotNull(message = "É necessário informar a descrição da propriedade.")
    @NotBlank(message = "É necessário informar a descrição da propriedade.")
    private String description;

    @NotNull(message = "É necessário informar a posição X da propriedade.")
    @SerializedName("lat")
    private Integer x;

    @NotNull(message = "É necessário informar a posição Y da propriedade.")
    @SerializedName("long")
    private Integer y;

    @NotNull(message = "É necessário informar a quantidade de quartos da propriedade.")
    private Integer beds;

    @NotNull(message = "É necessário informar a quantidade de banheiros da propriedade.")
    private Integer baths;

    @NotNull(message = "É necessário informar a quantidade de metros quadrados da propriedade.")
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
