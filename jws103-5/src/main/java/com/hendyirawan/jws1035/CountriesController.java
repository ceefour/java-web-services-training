package com.hendyirawan.jws1035;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountriesController {

    @Autowired
    private CountryRepository countryRepo;

    @GetMapping
    public Collection getAllCountries(
            @RequestParam(name = "sort", required = false) String sort) {
        final List<Country> sortedCountries;
        // Get query parameter
        if ("name,desc".equals(sort)) {
            sortedCountries = countryRepo.findAll(
                    new Sort(Sort.Direction.DESC, "name"));
        } else {
            // default sorting: name (ascending)
            sortedCountries = countryRepo.findAll(
                    new Sort("name"));
        }
        return sortedCountries;
    }

    @GetMapping(value = "/version", produces = "text/plain")
    //http://localhost:8080/countries/version
    public String version() {
        return "Versi 0.1";
    }

    @GetMapping("/{code}")
    public Country getCountry(@PathVariable("code") String code) {
        return countryRepo.findOne(code);
    }

    @PostMapping
    public Country addCountry(Country country) {
        return countryRepo.save(country);
    }

    @PutMapping("/{code}")
    public Country updateCountry(@PathVariable("code") String code, Country country) {
        return countryRepo.save(country);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity deleteCountry(@PathVariable("code") String code) {
        countryRepo.delete(code);
        return ResponseEntity.noContent().build();
    }

}
