package com.hendyirawan.jws1033;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("")
public class JerseyConfiguration extends ResourceConfig {
    public JerseyConfiguration() {

    }

    @PostConstruct
    public void setUp() {
        register(CountriesController.class);

    }
}