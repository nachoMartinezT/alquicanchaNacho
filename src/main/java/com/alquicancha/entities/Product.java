package com.alquicancha.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private String description;
    private LocalDate fromDate;
    private LocalDate toDate;
    @ElementCollection
    private Set<String> category;
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private Set<Photo> photos = new HashSet<>();

    public Product(){

    }

    public Product(String name, double price, String description, LocalDate fromDate, LocalDate toDate, Set<String> category) {
        this.name = name;
        this.description = description;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.category = category;
        this.photos = new HashSet<>();
    }

    public Long getId() {
        return id;
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

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public Set<String> getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = Collections.singleton(category);
    }

    public Set<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }

    public void addPhoto(Photo photo){
        if (photo != null && !photos.contains(photo)) {
            photos.add(photo);
            photo.setProduct(this);
        }
    }

    public void removePhoto(Photo photo){
        if (photo != null) {
            photos.remove(photo);
            photo.setProduct(null);
        }
    }



    public int getNumberOfPhotos() {
        return photos.size();
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", category='" + category + '\'' +
                ", photos=" + photos +
                '}';
    }
}


