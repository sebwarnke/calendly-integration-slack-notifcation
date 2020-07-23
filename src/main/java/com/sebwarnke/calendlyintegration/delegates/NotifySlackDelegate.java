package com.sebwarnke.calendlyintegration.delegates;

import com.samskivert.mustache.Mustache;
import com.sebwarnke.calendlyintegration.model.CalendlyEvent;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class NotifySlackDelegate implements JavaDelegate {
  @Override
  public void execute(DelegateExecution execution) throws Exception {
    CalendlyEvent calendlyEvent = (CalendlyEvent) execution.getVariable("calendlyEvent");

    String incomingEvent = calendlyEvent.getEvent();
    String eventName = calendlyEvent.getPayload().getEventType().getName();
    String inviteeName = calendlyEvent.getPayload().getInvitee().getName();
    String startTime = calendlyEvent.getPayload().getEvent().getStartTimePretty();
    String endTime = calendlyEvent.getPayload().getEvent().getEndTimePretty();

    String slackMessage = incomingEvent.equals("invitee.created") ?
      "The event \\\"" + eventName + "\\\" was just scheduled by " + inviteeName + ". Time: " + startTime +
        " - " + endTime : "The event \\\"" + eventName + "\\\" originally scheduled by " + inviteeName + " was just " +
      "canceled. Time: " + startTime +
      " - " + endTime;


    InputStream demoScheduledStream = ClassLoader.getSystemResourceAsStream("demo_scheduled.json");
    InputStreamReader inputStreamReader = new InputStreamReader(demoScheduledStream);

//    Mustache.compiler().compile(inputStreamReader).execute();


    final String POST_BODY = "{\"text\" : \"" + slackMessage + "\"}";

    URL url = new URL("https://hooks.slack.com/services/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");
    HttpURLConnection slackCon = (HttpURLConnection) url.openConnection();
    slackCon.setDoOutput(true);
    slackCon.setRequestMethod("POST");
    slackCon.setRequestProperty("Content-Type", "application/json");

    OutputStream os = slackCon.getOutputStream();
    os.write(POST_BODY.getBytes());
    os.flush();
    os.close();

    int responseCode = slackCon.getResponseCode();
    String responseMessage = slackCon.getResponseMessage();
    System.out.print("Response Code: " + responseCode);
    System.out.print("Response message: " + responseMessage);

  }
}
