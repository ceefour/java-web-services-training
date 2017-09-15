package com.hendyirawan.jws1034;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "countrylanguage")
public class CountryLanguage implements Serializable {
    @Embeddable
    public static class PK implements Serializable {
        private String countryCode;
        private String language;

        public PK() {
        }

        public PK(String countryCode, String language) {
            this.countryCode = countryCode;
            this.language = language;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PK pk = (PK) o;
            return Objects.equal(countryCode, pk.countryCode) &&
                    Objects.equal(language, pk.language);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(countryCode, language);
        }

        @Override
        public String toString() {
            return countryCode + "-" + language;
        }
    }

    @EmbeddedId
    private PK id;
    @ManyToOne(fetch = FetchType.LAZY) @MapsId("countryCode") @JoinColumn(name = "countrycode")
    @JsonIgnore
    private Country country;
    @Column(name = "isofficial")
    private Boolean isOfficial;
    private Float percentage;

    public PK getId() {
        return id;
    }

    public void setId(PK id) {
        this.id = id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Boolean getOfficial() {
        return isOfficial;
    }

    public void setOfficial(Boolean official) {
        isOfficial = official;
    }

    public Float getPercentage() {
        return percentage;
    }

    public void setPercentage(Float percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("isOfficial", isOfficial)
                .add("percentage", percentage)
                .toString();
    }
}
