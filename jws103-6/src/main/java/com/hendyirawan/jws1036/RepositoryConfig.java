package com.hendyirawan.jws1036;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        super.configureRepositoryRestConfiguration(config);
        config.exposeIdsFor(Country.class, City.class, CountryLanguage.class);
    }
}
