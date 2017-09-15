package com.hendyirawan.jws1035;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.MoreObjects;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class City implements Serializable {
    @Id
    private Integer id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "countrycode")
    @JsonIgnore
    private Country country;
    @Column(name = "countrycode", updatable = false, insertable = false)
    private String countryCode;
    private String district;
    private Integer population;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("countryCode", countryCode)
                .add("district", district)
                .add("population", population)
                .toString();
    }
}
