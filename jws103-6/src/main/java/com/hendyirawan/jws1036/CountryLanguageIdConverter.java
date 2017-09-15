package com.hendyirawan.jws1036;

import org.springframework.data.rest.webmvc.spi.BackendIdConverter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class CountryLanguageIdConverter implements BackendIdConverter {
    @Override
    public Serializable fromRequestId(String id, Class<?> entityType) {
        String[] parts = id.split("-");
        return new CountryLanguage.PK(parts[0], parts[1]);
    }

    @Override
    public String toRequestId(Serializable id, Class<?> entityType) {
        CountryLanguage.PK pk = (CountryLanguage.PK) id;
        return String.format("%s-%s", pk.getCountryCode(), pk.getLanguage());
    }

    @Override
    public boolean supports(Class<?> delimiter) {
        return delimiter.equals(CountryLanguage.class);
    }
}
