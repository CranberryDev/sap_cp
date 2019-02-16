package com.artem_agayev.sapcp.outer_rest_api;

import com.artem_agayev.sapcp.dto.TemperatureDTO;
import com.artem_agayev.sapcp.dto.TemperatureResponseDTO;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@Builder
public class TemperatureResource {

    private RestTemplate restTemplate;

    private final String URL;
    private final String API_KEY;
    private final String API_PARAM;
    private final String CITY_PARAM;
    private final String UNITS_METRIC;

    @Autowired
    public TemperatureResource(RestTemplate restTemplate,
                               @Value("${url.weather}") String url,
                               @Value("${url.api-key}") String apiKey,
                               @Value("${url.api-param}") String apiParam,
                               @Value("${url.units-metric}") String unitsMetric,
                               @Value("${url.city-param}") String cityParam) {
        this.restTemplate = restTemplate;
        this.URL = url;
        this.API_KEY = apiKey;
        this.API_PARAM = apiParam;
        this.UNITS_METRIC = unitsMetric;
        this.CITY_PARAM = cityParam;
    }

    /**
     * Creates request to outer api server, gets weather data for current cityName,
     * and returns only temperature data in dto-class with same param-name
     *
     * @param cityName
     * @return
     */
    public TemperatureResponseDTO getTemperatureInCity(String cityName) {
        String rqUrl = URL + "?" + CITY_PARAM + cityName + API_PARAM + API_KEY + UNITS_METRIC;

        Optional<TemperatureDTO> rs = Optional.ofNullable(restTemplate.getForObject(rqUrl, TemperatureDTO.class));

        Double temp = rs
                .map(temperatureDTO -> temperatureDTO.getMain().getTemp())
                .orElse(null);

        return TemperatureResponseDTO
                .builder()
                .temperature(temp).build();
    }
}
