package com.lohith.project.models;

import lombok.Builder;
import org.springframework.data.annotation.Id;

/**
 * Created by lohith.km on 21-07-2017.
 */
@Builder
public class Hotel {

    @Id
    private long id;

    private String name;

    private String description;

    String city;

    private int rating;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Hotel(long id, String name, String description, String city, int rating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.city = city;
        this.rating = rating;
    }

    public Hotel() {
    }
}
