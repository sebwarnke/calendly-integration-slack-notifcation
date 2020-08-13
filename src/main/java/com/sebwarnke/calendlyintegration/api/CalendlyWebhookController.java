package com.sebwarnke.calendlyintegration.api;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sebwarnke.calendlyintegration.model.CalendlyEvent;
import com.sebwarnke.calendlyintegration.services.OrchestratorService;
import com.sebwarnke.calendlyintegration.services.SlackNotificationService;
import org.slf4j.Logger;
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

  private static final Logger log = LoggerFactory.getLogger(CalendlyWebhookController.class);


  private OrchestratorService orchestratorService;

   @Autowired
  public CalendlyWebhookController(OrchestratorService orchestratorService) {
    this.orchestratorService = orchestratorService;
  }

  @PostMapping("/deliver")
  public void onEventReceived(@RequestBody CalendlyEvent calendlyEvent) throws IOException {

    log.info("New even created: " + new ObjectMapper().writeValueAsString(calendlyEvent));



    orchestratorService.processCalendlyEvent(calendlyEvent);
  }

}
