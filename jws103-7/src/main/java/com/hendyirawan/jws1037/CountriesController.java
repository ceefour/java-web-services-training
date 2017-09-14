package com.hendyirawan.jws1037;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountriesController {

    @Autowired
    private CountryRepository countryRepo;

    @GetMapping
    public List<Country> getAllCountries() {
        return countryRepo.findAll();
    }

}
