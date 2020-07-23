package com.sebwarnke.calendlyintegration.api;

import com.sebwarnke.calendlyintegration.model.CalendlyEvent;
import com.sebwarnke.calendlyintegration.services.SlackNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calendly")
public class CalendlyWebhookController {

  private SlackNotificationService slackNotificationService;

  @Autowired
  public CalendlyWebhookController(SlackNotificationService slackNotificationService) {
    this.slackNotificationService = slackNotificationService;
  }

  @PostMapping("/deliver")
  public void onEventReceived(@RequestBody CalendlyEvent calendlyEvent) {

  }

}
