package com.sebwarnke.calendlyintegration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RootUriTemplateHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ApplicationConfiguration {

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
    RootUriTemplateHandler rootUriTemplateHandler = new RootUriTemplateHandler(base);
    return restTemplateBuilder.uriTemplateHandler(rootUriTemplateHandler).build();
  }

  @Bean
  public WebClient defaultWebClient() {
    return WebClient.builder()
      .baseUrl(base)
      .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .build();
  }
}
