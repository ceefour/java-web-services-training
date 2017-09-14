package com.hendyirawan.jws1044;

import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GeonamesSearchResult implements Serializable {

    private int totalResultsCount;
    private List<Geoname> geonames = new ArrayList<>();

    public int getTotalResultsCount() {
        return totalResultsCount;
    }

    public void setTotalResultsCount(int totalResultsCount) {
        this.totalResultsCount = totalResultsCount;
    }

    public List<Geoname> getGeonames() {
        return geonames;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("totalResultsCount", totalResultsCount)
                .add("geonames", geonames)
                .toString();
    }
}
