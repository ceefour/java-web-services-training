package com.hendyirawan.jws1051;

import com.google.common.base.MoreObjects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
    private String surfaceArea;
    @Column(name = "indepyear")
    private String indepYear;
    private String population;
    @Column(name = "lifeexpectancy")
    private String lifeExpectancy;
    private BigDecimal gnp;
    @Column(name = "gnpold")
    private BigDecimal gnpOld;
    @Column(name = "localname")
    private String localName;
    @Column(name = "governmentform")
    private String governmentForm;
    @Column(name = "headofstate")
    private String headOfState;
    private Integer capital;
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

    public String getSurfaceArea() {
        return surfaceArea;
    }

    public void setSurfaceArea(String surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    public String getIndepYear() {
        return indepYear;
    }

    public void setIndepYear(String indepYear) {
        this.indepYear = indepYear;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getLifeExpectancy() {
        return lifeExpectancy;
    }

    public void setLifeExpectancy(String lifeExpectancy) {
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

    public Integer getCapital() {
        return capital;
    }

    public void setCapital(Integer capital) {
        this.capital = capital;
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
                .add("capital", capital)
                .add("code2", code2)
                .toString();
    }
}
