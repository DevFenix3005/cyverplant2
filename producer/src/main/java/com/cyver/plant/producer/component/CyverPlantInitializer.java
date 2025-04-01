package com.cyver.plant.producer.component;

import java.io.IOException;
import java.net.InetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cyver.plant.producer.configuration.PlantProperties;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.Location;

import kong.unirest.core.HttpResponse;
import kong.unirest.core.UnirestInstance;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@AllArgsConstructor(onConstructor_ = { @Autowired })
@Slf4j
public class CyverPlantInitializer implements CommandLineRunner {

    private final UnirestInstance unirest;

    private final DatabaseReader maxmindDatabase;

    private final PlantProperties plantProperties;

    @Override
    public void run(final String... args) throws Exception {
        log.info("Starting CyverPlant/Producer: {}", plantProperties);

        final HttpResponse<String> response = unirest.get(plantProperties.getHub().getIpifyUrl()).asString();
        if (response.getStatus() == 200) {
            final String ip = response.getBody();
            try {
                final InetAddress inetAddress = InetAddress.getByName(ip);
                final CityResponse cityResponse = maxmindDatabase.city(inetAddress);
                final Location location = cityResponse.getLocation();
                plantProperties.getHub().getLocation().setLatitude(location.getLatitude());
                plantProperties.getHub().getLocation().setLongitude(location.getLongitude());
                plantProperties.getHub().getLocation().setCityName(cityResponse.getCity().getName());
                log.info("Current location of the hub: {}", plantProperties.getHub().getLocation());
            } catch (final IOException | GeoIp2Exception e) {
                log.error("Error getting location:", e);
            }
        }
    }

}
