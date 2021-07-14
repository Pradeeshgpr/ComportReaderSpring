package com.artwiz.comport.reader.main;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(
        basePackages = "com.artwiz.comport.reader"
)
@SpringBootApplication
public class ComportStarter extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ComportStarter.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ComportStarter.class);
    }
}
