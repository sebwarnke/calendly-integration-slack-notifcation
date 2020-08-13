package com.sebwarnke.calendlyintegration;

import ch.qos.logback.classic.util.ContextInitializer;
import com.sebwarnke.calendlyintegration.config.LoggerConfiguration;
import com.sebwarnke.calendlyintegration.services.OrchestratorService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.logging.Logger;

@SpringBootApplication
@EnableConfigurationProperties
public class Application {

    @Autowired
    public static void main(String[] args, LoggerConfiguration loggerConfiguration) {
        System.setProperty("LOG_LOCATION", loggerConfiguration.getOutput());
        System.out.println("Here: "+ System.getProperty("LOG_LOCATION"));
        SpringApplication.run(Application.class);


  }

}