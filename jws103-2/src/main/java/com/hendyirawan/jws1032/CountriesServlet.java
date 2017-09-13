package com.hendyirawan.jws1032;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Example RESTful Web Services implementation using {@link HttpServlet}.
 */
@WebServlet("/countries/*")
public class CountriesServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(CountriesServlet.class);

    /**
     * Requires Jackson for converting object from/to JSON (provided by Spring Boot).
     */
    @Autowired
    private ObjectMapper mapper;
    private final List<Country> countries = new ArrayList<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        // Prepare list of Country objects
        countries.add(new Country("IDN", "Indonesia"));
        countries.add(new Country("MYS", "Malaysia"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (null == req.getPathInfo() || "/".equals(req.getPathInfo())) {
            final ImmutableList<Country> sortedCountries;
            // Get query parameter
            String sort = req.getParameter("sort");
            if ("name,desc".equals(sort)) {
                sortedCountries = ImmutableList.sortedCopyOf(
                        (a, b) -> b.getCode().compareTo(a.getCode()), this.countries);
            } else {
                // default sorting: name (ascending)
                sortedCountries = ImmutableList.sortedCopyOf(
                        (a, b) -> a.getCode().compareTo(b.getCode()), this.countries);
            }

            // Log the objects
            LOG.info("Countries (sorted): {}", sortedCountries);
            // Convert POJO objects to JSON using Jackson
            String countriesJson = mapper.writeValueAsString(sortedCountries);
            LOG.info("Countries JSON: {}", countriesJson);
            // Ensure the response MIME type is application/json
            resp.setHeader("Content-Type", "application/json;charset=UTF-8");
            // Write out the response body
            PrintWriter writer = resp.getWriter();
            writer.write(countriesJson);
        } else if (req.getPathInfo().startsWith("/")) {
            // get the first path parameter
            String[] segments = req.getPathInfo().split("/");
            String code = segments[1];
            // find the country
            Optional<Country> country = countries.stream().filter(it -> code.equals(it.getCode())).findAny();
            if (country.isPresent()) {
                // Convert POJO objects to JSON using Jackson
                String countryJson = mapper.writeValueAsString(country.get());
                LOG.info("Country JSON: {}", countryJson);
                // Ensure the response MIME type is application/json
                resp.setHeader("Content-Type", "application/json;charset=UTF-8");
                // Write out the response body
                PrintWriter writer = resp.getWriter();
                writer.write(countryJson);
            } else {
                resp.sendError(404, "Unknown country code: " + code);
            }
        } else {
            throw new UnsupportedOperationException("Unknown path: " + req.getPathInfo());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (null == req.getPathInfo() || "/".equals(req.getPathInfo())) {
            // Add the given country to the list
            Country newCountry = mapper.readValue(req.getInputStream(), Country.class);
            countries.add(newCountry);

            // Convert POJO objects to JSON using Jackson
            String countryJson = mapper.writeValueAsString(newCountry);
            LOG.info("Country JSON: {}", countryJson);
            // Ensure the response MIME type is application/json
            resp.setHeader("Content-Type", "application/json;charset=UTF-8");
            // Write out the response body
            PrintWriter writer = resp.getWriter();
            writer.write(countryJson);
        } else {
            throw new UnsupportedOperationException("Unknown path: " + req.getPathInfo());
        }
    }
}
