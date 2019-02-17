package com.artem_agayev.sapcp.resources;

import com.artem_agayev.sapcp.dto.TemperatureResponseDTO;
import com.artem_agayev.sapcp.outer_rest_api.TemperatureResource;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@WebServlet(urlPatterns = {"/servlet"}, asyncSupported = true)
public class WeatherServlet extends HttpServlet {

    private TemperatureResource temperatureResource;
    private ObjectMapper objectMapper;

    @Autowired
    public WeatherServlet(TemperatureResource temperatureResource,
                          @Qualifier("configuredObjMapper") ObjectMapper objectMapper) {
        this.temperatureResource = temperatureResource;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doGet(HttpServletRequest rq,
                         HttpServletResponse resp) throws IOException {
        setAccessControlHeaders(resp);
        String cityName = rq.getParameter("cityName");
        TemperatureResponseDTO respDto = temperatureResource.getTemperatureInCity(cityName);
        String jsonObj = objectMapper.writeValueAsString(respDto);

        resp.getWriter().print(jsonObj);
    }


    private void setAccessControlHeaders(HttpServletResponse resp) {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "GET");
        resp.addHeader("Access-Control-Allow-Headers", "Origin, Content-Type, Accept");
        resp.addHeader("Access-Control-Max-Age", "86400");
    }

}
