package com.adamldavis.adw.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CourseData {

    @Id
    String id;

    String name;
    long price;

    public CourseData() {
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

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
