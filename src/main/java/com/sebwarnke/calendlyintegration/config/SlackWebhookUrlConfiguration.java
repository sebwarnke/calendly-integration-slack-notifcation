package com.sebwarnke.calendlyintegration.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "slack.webhook.url")
public class SlackWebhookUrlConfiguration {
  private String base;
  private String secret;
}
