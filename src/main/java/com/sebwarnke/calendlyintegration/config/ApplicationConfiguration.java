package com.sebwarnke.calendlyintegration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ApplicationConfiguration {

  @Bean
  public WebClient defaultWebClient(SlackWebhookUrlConfiguration slackWebhookUrlConfiguration) {
    return WebClient.builder()
      .baseUrl(slackWebhookUrlConfiguration.getBase())
      .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .build();
  }
}
