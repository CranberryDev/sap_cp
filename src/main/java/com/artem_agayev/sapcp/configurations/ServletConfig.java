package com.artem_agayev.sapcp.configurations;

import com.artem_agayev.sapcp.filter.CORSFilter;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletException;

@Configuration
public class ServletConfig implements ServletContextInitializer {

    @Override
    public void onStartup(javax.servlet.ServletContext servletContext) throws ServletException {
        servletContext.addFilter("corsFilter", new CORSFilter());
    }

}
