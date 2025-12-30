package com.meteo.weatherservice.model;

public class CityDTO {
    private Long id;
    private String name;
    private String country;

    public CityDTO() {
    }

    public CityDTO(Long id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    // getters & setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

