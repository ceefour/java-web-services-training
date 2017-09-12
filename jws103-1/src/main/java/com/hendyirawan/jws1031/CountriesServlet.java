package com.hendyirawan.jws1031;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Example RESTful Web Services implementation using {@link HttpServlet}.
 */
@WebServlet("/countries")
public class CountriesServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(CountriesServlet.class);

    /**
     * Requires Jackson for converting object from/to JSON (provided by Spring Boot).
     */
    @Autowired
    private ObjectMapper mapper;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Prepare list of Country objects
        ArrayList<Country> countries = new ArrayList<Country>();
        countries.add(new Country("IDN", "Indonesia"));
        countries.add(new Country("MYS", "Malaysia"));
        // Log the objects
        LOG.info("Countries: {}", countries);
        // Convert POJO objects to JSON using Jackson
        String countriesJson = mapper.writeValueAsString(countries);
        LOG.info("Countries JSON: {}", countriesJson);
        // Ensure the response MIME type is application/json
        resp.setHeader("Content-Type", "application/json");
        // Write out the response body
        PrintWriter writer = resp.getWriter();
        writer.write(countriesJson);
    }
}
