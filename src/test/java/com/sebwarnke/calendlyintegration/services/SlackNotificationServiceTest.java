package com.sebwarnke.calendlyintegration.services;

import ch.qos.logback.classic.Level;
import ch.qos.logback.core.FileAppender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sebwarnke.calendlyintegration.config.LoggerConfiguration;
import com.sebwarnke.calendlyintegration.config.SlackWebhookUrlConfiguration;
import com.sebwarnke.calendlyintegration.model.CalendlyEvent;
import com.sebwarnke.calendlyintegration.util.Util;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static javax.print.attribute.standard.ReferenceUriSchemesSupported.FILE;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SlackNotificationServiceTest {

    @Autowired
    private OrchestratorService orchestratorService;
    CalendlyEvent event = null;

    @Autowired
    private LoggerConfiguration loggerConfiguration;


    @Before
    public void setUp() throws JsonProcessingException {


        StringBuilder jsonString = new StringBuilder();
        try {
            File myObj = new File("src/test/resources/example_event_scheduled.json");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                jsonString.append(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("file not found.");
            e.printStackTrace();
        }
        ObjectMapper mapper = new ObjectMapper();
        event = mapper.readValue(jsonString.toString(), CalendlyEvent.class);
    }

    @Test
    public void testNotifySlackOnCreation() throws IOException {
        orchestratorService.processCalendlyEvent(event);
    }
}