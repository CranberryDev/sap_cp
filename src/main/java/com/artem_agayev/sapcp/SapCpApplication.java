package com.artem_agayev.sapcp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;


@SpringBootApplication
public class SapCpApplication extends SpringBootServletInitializer {

    public static void main(String ... args){
        SpringApplication.run(SapCpApplication.class, args);
    }

}
