package com.meteo.cityservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String country;

    public City() {}

    public City(String name, String country) {
        this.name = name;
        this.country = country;
    }

    // getters & setters

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public void setName(String name) {}
    public void setCountry(String country) {}
}
