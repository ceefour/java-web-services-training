package com.hendyirawan.jws1038;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {

    @Autowired
    private CountryRepository countryRepo;

    public List<Country> countries() {
        return countryRepo.findAll();
    }
}