package com.sebwarnke.calendlyintegration.services;

import com.samskivert.mustache.Mustache;
import com.sebwarnke.calendlyintegration.model.CalendlyEvent;
import com.sebwarnke.calendlyintegration.util.TemplatePaths;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Collections;
import java.util.Map;

@Service
public class SlackNotificationService {

    public void startProcessForEventFromCalendly(CalendlyEvent calendlyEvent) {

        InputStreamReader in =
          new InputStreamReader(ClassLoader.getSystemResourceAsStream(TemplatePaths.DEMO_SCHEDULED));


        URL url = null;
        try {
            url = new URL("https://hooks.slack.com/services/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");
            HttpURLConnection slackCon = (HttpURLConnection) url.openConnection();
            slackCon.setDoOutput(true);
            slackCon.setRequestMethod("POST");
            slackCon.setRequestProperty("Content-Type", "application/json");

            OutputStreamWriter out = new OutputStreamWriter(slackCon.getOutputStream());

            Map<String, String> data = Collections.emptyMap();
            Mustache.compiler().compile(in).execute(data, out);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, String> data = Collections.emptyMap();
        Mustache.compiler().compile(in).execute();

        new OutpuStr
    }
}
