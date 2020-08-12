package com.sebwarnke.calendlyintegration.services;

import com.sebwarnke.calendlyintegration.model.CalendlyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class OrchestratorService {

  private EventConversionService eventConversionService;
  private SlackNotificationService slackNotificationService;

  @Autowired
  public OrchestratorService(EventConversionService eventConversionService, SlackNotificationService slackNotificationService) {
    this.eventConversionService = eventConversionService;
    this.slackNotificationService = slackNotificationService;
  }

  public void processCalendlyEvent(CalendlyEvent calendlyEvent, String channel) throws IOException {
    Map<String, String> data = eventConversionService.handleCalendlyEvent(calendlyEvent);

    if (calendlyEvent.event.equals("invitee.created")) {
      slackNotificationService.notifySlackOnCreation(data, channel);
    } else {
      slackNotificationService.notifySlackOnCancelation(data, channel);
    }
  }
}
