package com.artem_agayev.sapcp.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class MapperConfig {

    @Bean
    @Scope("prototype")
    @Qualifier("configuredObjMapper")
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }

}
