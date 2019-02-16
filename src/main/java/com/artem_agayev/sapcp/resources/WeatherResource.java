package com.artem_agayev.sapcp.resources;

import com.artem_agayev.sapcp.dto.TemperatureResponseDTO;
import com.artem_agayev.sapcp.outer_rest_api.TemperatureResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WeatherResource {

    private TemperatureResource temperatureResource;

    @Autowired
    public WeatherResource(TemperatureResource temperatureResource) {
        this.temperatureResource = temperatureResource;
    }

    @RequestMapping(value = "/temperature", method = {RequestMethod.GET})
    ResponseEntity getTemperature(@RequestParam String cityName) {

        TemperatureResponseDTO temperature =  temperatureResource.getTemperatureInCity(cityName);

        return ResponseEntity
                .status(temperature != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST)
                .body(temperature);
    }

}
