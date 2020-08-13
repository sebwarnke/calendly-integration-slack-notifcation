package com.sebwarnke.calendlyintegration.services;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sebwarnke.calendlyintegration.api.CalendlyWebhookController;
import com.sebwarnke.calendlyintegration.config.LoggerConfiguration;
import com.sebwarnke.calendlyintegration.model.CalendlyEvent;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class OrchestratorService {

  Logger logger = ((LoggerContext) LoggerFactory.getILoggerFactory()).getLogger("Events");



  private EventConversionService eventConversionService;
  private SlackNotificationService slackNotificationService;

  @Autowired
  public OrchestratorService(EventConversionService eventConversionService, SlackNotificationService slackNotificationService) {

    this.eventConversionService = eventConversionService;
    this.slackNotificationService = slackNotificationService;

  }

  public void processCalendlyEvent(CalendlyEvent calendlyEvent) throws IOException {

    logger.info("New even created: " + new ObjectMapper().writeValueAsString(calendlyEvent));
    Map<String, String> data = eventConversionService.handleCalendlyEvent(calendlyEvent);



    if (calendlyEvent.event.equals("invitee.created")) {
      slackNotificationService.notifySlackOnCreation(data);
    } else {
      slackNotificationService.notifySlackOnCancelation(data);
    }
  }
}
