package com.sebwarnke.calendlyintegration.api;

import com.sebwarnke.calendlyintegration.model.CalendlyEvent;
import com.sebwarnke.calendlyintegration.services.CamundaEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/calendly")
public class CalendlyWebhookController {

  private CamundaEngineService camundaEngineService;

  @Autowired
  public CalendlyWebhookController(CamundaEngineService camundaEngineService) {
    this.camundaEngineService = camundaEngineService;
  }

  @PostMapping("/deliver")
  public void onEventReceived(@RequestBody CalendlyEvent calendlyEvent) {

    Map<String, Object> variables = new HashMap<>();
    variables.put("calendlyEvent", calendlyEvent);

//    ProcessInstance processInstance = processEngine.getRuntimeService()
//      .startProcessInstanceByKey("camunda-backend-process", variables);
  }

}
