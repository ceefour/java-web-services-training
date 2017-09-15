package com.hendyirawan.jws1035;

import com.google.common.base.MoreObjects;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class Country implements Serializable {
    @Id
    private String code;
    private String name;
    private String continent;
    private String region;
    @Column(name = "surfacearea")
    private Float surfaceArea;
    @Column(name = "indepyear")
    private Short indepYear;
    private Integer population;
    @Column(name = "lifeexpectancy")
    private Float lifeExpectancy;
    private BigDecimal gnp;
    @Column(name = "gnpold")
    private BigDecimal gnpOld;
    @Column(name = "localname")
    private String localName;
    @Column(name = "governmentform")
    private String governmentForm;
    @Column(name = "headofstate")
    private String headOfState;
    @ManyToOne @JoinColumn(name = "capital")
    private City capital;
    @Column(name = "capital", updatable = false, insertable = false)
    private Integer capitalId;
    private String code2;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Float getSurfaceArea() {
        return surfaceArea;
    }

    public void setSurfaceArea(Float surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    public Short getIndepYear() {
        return indepYear;
    }

    public void setIndepYear(Short indepYear) {
        this.indepYear = indepYear;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Float getLifeExpectancy() {
        return lifeExpectancy;
    }

    public void setLifeExpectancy(Float lifeExpectancy) {
        this.lifeExpectancy = lifeExpectancy;
    }

    public BigDecimal getGnp() {
        return gnp;
    }

    public void setGnp(BigDecimal gnp) {
        this.gnp = gnp;
    }

    public BigDecimal getGnpOld() {
        return gnpOld;
    }

    public void setGnpOld(BigDecimal gnpOld) {
        this.gnpOld = gnpOld;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getGovernmentForm() {
        return governmentForm;
    }

    public void setGovernmentForm(String governmentForm) {
        this.governmentForm = governmentForm;
    }

    public String getHeadOfState() {
        return headOfState;
    }

    public void setHeadOfState(String headOfState) {
        this.headOfState = headOfState;
    }

    public City getCapital() {
        return capital;
    }

    public void setCapital(City capital) {
        this.capital = capital;
    }

    public Integer getCapitalId() {
        return capitalId;
    }

    public void setCapitalId(Integer capitalId) {
        this.capitalId = capitalId;
    }

    public String getCode2() {
        return code2;
    }

    public void setCode2(String code2) {
        this.code2 = code2;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("code", code)
                .add("name", name)
                .add("continent", continent)
                .add("region", region)
                .add("surfaceArea", surfaceArea)
                .add("indepYear", indepYear)
                .add("population", population)
                .add("lifeExpectancy", lifeExpectancy)
                .add("gnp", gnp)
                .add("gnpOld", gnpOld)
                .add("localName", localName)
                .add("governmentForm", governmentForm)
                .add("headOfState", headOfState)
                .add("capitalId", capitalId)
                .add("code2", code2)
                .toString();
    }
}
