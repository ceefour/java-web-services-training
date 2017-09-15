package com.hendyirawan.jws1033;

import com.google.common.collect.ImmutableList;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
@Path("/countries")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CountriesController {

    private final List<Country> countries = new ArrayList<>();

    public CountriesController(){
        countries.add(new Country("IDN", "Indonesia"));
        countries.add(new Country("MYS", "Malaysia"));
        countries.add(new Country("SGP", "Singapore"));
    }

    @GET
    public Collection getAllCountries(@QueryParam("sort") String sort) {
        final ImmutableList<Country> sortedCountries;
        // Get query parameter
        if ("name,desc".equals(sort)) {
            sortedCountries = ImmutableList.sortedCopyOf(
                    (a, b) -> b.getCode().compareTo(a.getCode()), this.countries);
        } else {
            // default sorting: name (ascending)
            sortedCountries = ImmutableList.sortedCopyOf(
                    (a, b) -> a.getCode().compareTo(b.getCode()), this.countries);
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
        Optional<Country> country = countries.stream().filter(it -> code.equals(it.getCode())).findAny();
        if(country.isPresent()) {
            return country.get();
        }
        return null;
    }

    @POST
    public Response addCountry(Country country) {
        countries.add(country);
        return Response.created(URI.create("/" + country.getCode())).build();
    }

    @PUT
    @Path("/{code}")
    public Response updateCountry(@PathParam("code") String code, Country country) {
        Optional<Country> found = countries.stream().filter(it -> code.equals(it.getCode())).findAny();
        if(found.isPresent()) {
            found.get().setCode(country.getCode());
            found.get().setName(country.getName());
            System.out.println("FOUND");
        }else{
            countries.add(country);
            System.out.println("NOT FOUND");
        }
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{code}")
    public Response deleteCountry(@PathParam("code") String code) {
        Optional<Country> found = countries.stream().filter(it -> code.equals(it.getCode())).findAny();
        if(found.isPresent()) {
            countries.remove(found.get());
        }
        return Response.ok().build();
    }

}
