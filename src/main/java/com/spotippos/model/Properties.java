package com.spotippos.model;

import java.util.List;

public class Properties {

    private int totalProperties;
    private List<Property> properties;

    public Properties() {
    }

    public Properties(List<Property> properties) {
        this.properties = properties;
        this.totalProperties = properties.size();
    }

    public int getTotalProperties() {
        return totalProperties;
    }

    public void setTotalProperties(int totalProperties) {
        this.totalProperties = totalProperties;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

}
