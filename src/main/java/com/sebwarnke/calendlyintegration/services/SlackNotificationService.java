package com.sebwarnke.calendlyintegration.services;

import com.samskivert.mustache.Mustache;
import com.sebwarnke.calendlyintegration.config.SlackWebhookUrlConfiguration;
import com.sebwarnke.calendlyintegration.model.CalendlyEvent;
import com.sebwarnke.calendlyintegration.util.TemplateFieldConstants;
import com.sebwarnke.calendlyintegration.util.TemplatePaths;
import com.sebwarnke.calendlyintegration.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import reactor.core.publisher.Mono;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

@Service
public class SlackNotificationService {
  private static final Logger log = LoggerFactory.getLogger(SlackNotificationService.class);

  private WebClient webClient;
  private SlackWebhookUrlConfiguration slackWebhookUrlConfiguration;

  @Autowired
  public SlackNotificationService(WebClient webClient, SlackWebhookUrlConfiguration slackWebhookUrlConfiguration) {
    this.webClient = webClient;
    this.slackWebhookUrlConfiguration = slackWebhookUrlConfiguration;
  }

  public void notifySlackOnCreation(Map<String, String> data) throws IOException {

    /* Read json body template */
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    this.getClass().getClassLoader().getResourceAsStream(TemplatePaths.DEMO_SCHEDULED).transferTo(outputStream);

    String template = outputStream.toString(StandardCharsets.UTF_8);

    /* Compile Template */
    String payload = Mustache.compiler().compile(template).execute(data);

    ClientResponse clientResponse = webClient.post()
      .uri(slackWebhookUrlConfiguration.getSecret())
      .body(BodyInserters.fromValue(payload))
      .exchange()
      .block();
  }

  public void notifySlackOnCancelation(Map<String, String> data) throws IOException {

    /* Read json body template */
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    this.getClass().getClassLoader().getResourceAsStream(TemplatePaths.EVENT_CANCELED).transferTo(outputStream);

    String template = outputStream.toString(StandardCharsets.UTF_8);
    outputStream.close();

    /* Compile Template */
    String payload = Mustache.compiler().compile(template).execute(data);

    ClientResponse clientResponse = webClient.post()
      .uri(slackWebhookUrlConfiguration.getSecret())
      .body(BodyInserters.fromValue(payload))
      .exchange()
      .block();

  }
}
