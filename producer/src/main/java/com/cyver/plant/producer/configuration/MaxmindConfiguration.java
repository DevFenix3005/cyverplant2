package com.cyver.plant.producer.configuration;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.maxmind.geoip2.DatabaseReader;

@Configuration
public class MaxmindConfiguration {

    @Bean
    public DatabaseReader maxmindDatabase() throws IOException {
        final InputStream is = new ClassPathResource("datos/GeoLite2-City.mmdb").getInputStream();
        return new DatabaseReader.Builder(is).build();
    }

}
