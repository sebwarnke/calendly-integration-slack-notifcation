package com.sebwarnke.calendlyintegration.api;

import com.sebwarnke.calendlyintegration.model.CalendlyEvent;
import com.sebwarnke.calendlyintegration.services.OrchestratorService;
import com.sebwarnke.calendlyintegration.services.SlackNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/calendly")
public class CalendlyWebhookController {

  private OrchestratorService orchestratorService;

  @Autowired
  public CalendlyWebhookController(OrchestratorService orchestratorService) {
    this.orchestratorService = orchestratorService;
  }

  @PostMapping("/deliver")
  public void onEventReceived(@RequestBody CalendlyEvent calendlyEvent) throws IOException {
    orchestratorService.processCalendlyEvent(calendlyEvent);
  }

}
