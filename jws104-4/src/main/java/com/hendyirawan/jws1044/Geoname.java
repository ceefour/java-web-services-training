package com.hendyirawan.jws1044;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

public class Geoname implements Serializable {

    private String adminCode1;
    private Double lat;
    private Double lng;
    private Long geonameId;
    private String toponymName;
    private Long countryId;
    private String fcl;
    private long population;
    private String countryCode;
    private String name;
    private String fclName;
    private String countryName;
    private String fcodeName;
    private String adminName1;
    private String fcode;

    public String getAdminCode1() {
        return adminCode1;
    }

    public void setAdminCode1(String adminCode1) {
        this.adminCode1 = adminCode1;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Long getGeonameId() {
        return geonameId;
    }

    public void setGeonameId(Long geonameId) {
        this.geonameId = geonameId;
    }

    public String getToponymName() {
        return toponymName;
    }

    public void setToponymName(String toponymName) {
        this.toponymName = toponymName;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getFcl() {
        return fcl;
    }

    public void setFcl(String fcl) {
        this.fcl = fcl;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFclName() {
        return fclName;
    }

    public void setFclName(String fclName) {
        this.fclName = fclName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getFcodeName() {
        return fcodeName;
    }

    public void setFcodeName(String fcodeName) {
        this.fcodeName = fcodeName;
    }

    public String getAdminName1() {
        return adminName1;
    }

    public void setAdminName1(String adminName1) {
        this.adminName1 = adminName1;
    }

    public String getFcode() {
        return fcode;
    }

    public void setFcode(String fcode) {
        this.fcode = fcode;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("adminCode1", adminCode1)
                .add("lat", lat)
                .add("lng", lng)
                .add("geonameId", geonameId)
                .add("toponymName", toponymName)
                .add("countryId", countryId)
                .add("fcl", fcl)
                .add("population", population)
                .add("countryCode", countryCode)
                .add("name", name)
                .add("fclName", fclName)
                .add("countryName", countryName)
                .add("fcodeName", fcodeName)
                .add("adminName1", adminName1)
                .add("fcode", fcode)
                .toString();
    }
}
