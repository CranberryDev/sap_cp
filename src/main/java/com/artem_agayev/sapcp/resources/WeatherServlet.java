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
@WebServlet(urlPatterns = {"/servlet"})
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

    @Override
    protected void doOptions(HttpServletRequest req,
                             HttpServletResponse resp) {
        setAccessControlHeaders(resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    private void setAccessControlHeaders(HttpServletResponse resp) {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Credentials", "true");
        resp.addHeader("Access-Control-Allow-Methods", "GET, OPTIONS");
        resp.addHeader("Access-Control-Allow-Headers", "Origin,Content-Type, Accept");
    }

}
