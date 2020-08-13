package com.sebwarnke.calendlyintegration.api;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sebwarnke.calendlyintegration.config.LoggerConfiguration;
import com.sebwarnke.calendlyintegration.model.CalendlyEvent;
import com.sebwarnke.calendlyintegration.services.OrchestratorService;
import com.sebwarnke.calendlyintegration.services.SlackNotificationService;
import com.sebwarnke.calendlyintegration.util.Util;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/calendly")
public class CalendlyWebhookController {

  Logger logger = (Logger) LoggerFactory.getLogger("Events");


  private OrchestratorService orchestratorService;

   @Autowired
  public CalendlyWebhookController(OrchestratorService orchestratorService, LoggerConfiguration loggerConfiguration) {
    this.orchestratorService = orchestratorService;
     if(((LoggerContext) LoggerFactory.getILoggerFactory()) == null){
       this.logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("Events");
     } else {
       Util.createLoggerFor("Events", loggerConfiguration.getOutput());
     }

  }

  @PostMapping("/deliver")
  public void onEventReceived(@RequestBody CalendlyEvent calendlyEvent) throws IOException {

    logger.info("New even created: " + new ObjectMapper().writeValueAsString(calendlyEvent));



    orchestratorService.processCalendlyEvent(calendlyEvent);
  }

}
