package com.oscar.sanchez.tqs1.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "City")
public class City {
    @Id
    @Column(name="locationId")
    private String locationId;
    @Column(name = "name")
    private String name;
    @Column(name="country")
    private String country;
    @Column(name="latitude")
    private float latitude;
    @Column(name="longitude")
    private float longitude;
    @Column(name="value")
    private float value;
    @Column(name="location")
    private String location;

    public City() {
    }

    public City(String locationId, String name, String country, float latitude, float longitude, float value, String location) {
        this.locationId = locationId;
        this.name = name;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
        this.value = value;
        this.location = location;
    }

    @Override
    public String toString() {
        return "City{" +
                "location='" + location + '\'' +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", value=" + value +" µg/m³"+
                ", locationId='" + locationId + '\'' +
                '}';
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }
}
