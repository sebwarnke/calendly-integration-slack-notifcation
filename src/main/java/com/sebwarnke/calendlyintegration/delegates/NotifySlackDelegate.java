package com.sebwarnke.calendlyintegration.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class NotifySlackDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String event = (String) execution.getVariable("event");

        URL url = new URL ("slackwebhook.com");
        HttpURLConnection slackCon = (HttpURLConnection) url.openConnection();
        slackCon.setDoOutput(true);
        slackCon.setRequestMethod("Post");
        slackCon.setRequestProperty("Content-Type", "application/json");

        String input = "{\"text\": event}";

        OutputStream os = slackCon.getOutputStream();
        os.write(input.getBytes());
        os.flush();







    }
}
