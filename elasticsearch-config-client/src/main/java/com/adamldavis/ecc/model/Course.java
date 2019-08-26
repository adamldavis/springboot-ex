package com.adamldavis.ecc.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.UUID;

@Document(indexName = "courses", shards = 7)
public class Course {

    public static Course create(String name, long price) {
        return new Course(UUID.randomUUID(), name, price);
    }

    @Id
    private UUID id;
    private String name;
    private long price = 2000; // $20.00 is default price

    /** Default constructor so jackson can deserialize. */
    public Course() {
    }

    private Course(UUID id, String name, long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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
