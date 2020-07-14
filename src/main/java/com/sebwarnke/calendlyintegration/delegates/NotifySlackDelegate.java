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

        final String POST_BODY ="{\n" + "\"userId\": 101,\r\n" +
                "    \"id\": 101,\r\n" +
                "    \"title\": \"Test Title\",\r\n" +
                "    \"body\": \"Test Body\"" + "\n}";

        URL url = new URL ("slackwebhook.com");
        HttpURLConnection slackCon = (HttpURLConnection) url.openConnection();
        slackCon.setDoOutput(true);
        slackCon.setRequestMethod("Post");
        slackCon.setRequestProperty("Content-Type", "application/json");



        OutputStream os = slackCon.getOutputStream();
        os.write(POST_BODY.getBytes());
        os.flush();
        os.close();

        int responseCode = slackCon.getResponseCode();
        String responseMessage = slackCon.getResponseMessage();
        System.out.print("Response Code: "+ responseCode);
        System.out.print("Response message: " + responseMessage);







    }
}
