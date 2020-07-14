package com.sebwarnke.calendlyintegration.api;

import com.sebwarnke.calendlyintegration.model.CalendlyEvent;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/calendly")
public class CalendlyWebhookController {

  private ProcessEngine processEngine;

  @Autowired
  public CalendlyWebhookController(ProcessEngine processEngine) {
    this.processEngine = processEngine;
  }

  @PostMapping("/deliver")
  public void onEventReceived(@RequestBody CalendlyEvent calendlyEvent) {

    Map<String, Object> variables = new HashMap<>();
    variables.put("calendlyEvent", calendlyEvent);

    ProcessInstance processInstance = processEngine.getRuntimeService()
      .startProcessInstanceByKey("camunda-backend-process", variables);
  }

}
