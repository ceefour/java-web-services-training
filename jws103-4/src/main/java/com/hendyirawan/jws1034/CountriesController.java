package com.hendyirawan.jws1034;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
@Path("/countries")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CountriesController {

    @Autowired
    private CountryRepository countryRepo;

    @GET
    public Collection getAllCountries(@QueryParam("sort") String sort) {
        final List<Country> sortedCountries;
        // Get query parameter
        if ("name,desc".equals(sort)) {
            sortedCountries = countryRepo.findAll(new Sort(Sort.Direction.DESC, "name"));
        } else {
            // default sorting: name (ascending)
            sortedCountries = countryRepo.findAll(new Sort("name"));
        }
        return sortedCountries;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/version") //http://localhost:8080/countries/version
    public String version() {
        return "Versi 0.1";
    }

    @GET
    @Path("/{code}")
    public Country getCountry(@PathParam("code") String code) {
        return countryRepo.findOne(code);
    }

    @POST
    public Country addCountry(Country country) {
        return countryRepo.save(country);
    }

    @PUT
    @Path("/{code}")
    public Country updateCountry(@PathParam("code") String code, Country country) {
        return countryRepo.save(country);
    }

    @DELETE
    @Path("/{code}")
    public ResponseEntity deleteCountry(@PathParam("code") String code) {
        countryRepo.delete(code);
        return ResponseEntity.noContent().build();
    }

}
