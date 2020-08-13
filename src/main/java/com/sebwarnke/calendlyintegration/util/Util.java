package com.sebwarnke.calendlyintegration.util;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.FileAppender;
import com.sebwarnke.calendlyintegration.model.CalendlyEvent;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

  public static Logger createLoggerFor(String string, String file) {
    LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
    PatternLayoutEncoder ple = new PatternLayoutEncoder();

    ple.setPattern("%date %level [%thread] %logger{10} [%file:%line] %msg%n");
    ple.setContext(lc);
    ple.start();
    FileAppender<ILoggingEvent> fileAppender = new FileAppender<ILoggingEvent>();
    fileAppender.setFile(file);
    fileAppender.setEncoder(ple);
    fileAppender.setContext(lc);
    fileAppender.start();

    Logger logger = (Logger) LoggerFactory.getLogger(string);
    logger.addAppender(fileAppender);
    logger.setLevel(Level.INFO);
    logger.setAdditive(false); /* set to true if root should log too */

    return logger;
  }



  public static Map<String, String> templateDataFromCalendlyEvent(final CalendlyEvent calendlyEvent) {
    HashMap<String, String> data = new HashMap<>();

    String startTimeNative = calendlyEvent.getPayload().getEvent().getStartTime();
    String endTimeNative = calendlyEvent.getPayload().getEvent().getEndTime();

    LocalDateTime startLocalDateTime = LocalDateTime.parse(startTimeNative, DateTimeFormatter.ISO_DATE_TIME);
    LocalDateTime endLocalDateTime = LocalDateTime.parse(endTimeNative, DateTimeFormatter.ISO_DATE_TIME);
    String customEventDate = startLocalDateTime.format(DateTimeFormatter.ofPattern(TemplateFieldConstants.DATE_FORMAT));
    String customStartTime = startLocalDateTime.format(DateTimeFormatter.ofPattern(TemplateFieldConstants.TIME_FORMAT));
    String customEndTime = endLocalDateTime.format(DateTimeFormatter.ofPattern(TemplateFieldConstants.TIME_FORMAT));

    data.put(TemplateFieldConstants.EVENT_NAME, calendlyEvent.getPayload().getEventType().getName());
    data.put(TemplateFieldConstants.COMPANY, calendlyEvent.getPayload().getQuestionsAndResponses().get_1Response());
    data.put(TemplateFieldConstants.INVITEE_EMAIL, calendlyEvent.getPayload().getInvitee().getEmail());
    data.put(TemplateFieldConstants.INVITEE_NAME, calendlyEvent.getPayload().getInvitee().getName());
    data.put(TemplateFieldConstants.EVENT_DATE, customEventDate);
    data.put(TemplateFieldConstants.EVENT_TIME_START, customStartTime);
    data.put(TemplateFieldConstants.EVENT_TIME_END, customEndTime);
    data.put(TemplateFieldConstants.CONSULTANT_NAME, calendlyEvent.getPayload().getEvent().getAssignedTo().get(0));
    data.put(TemplateFieldConstants.ZOOM_LINK, calendlyEvent.getPayload().getEvent().getLocation());
    data.put(TemplateFieldConstants.CANCEL_LINK, "https://calendly.com/cancellations/" + calendlyEvent.getPayload().getInvitee().getUuid());
    data.put(TemplateFieldConstants.RESCHEDULE_LINK, "https://calendly.com/reschedulings/" + calendlyEvent.getPayload().getInvitee().getUuid());
    String cancelReason = calendlyEvent.getPayload().getInvitee().getCancelReason() != null ? calendlyEvent.getPayload()
      .getInvitee()
      .getCancelReason() : "n/a";
    data.put(TemplateFieldConstants.CANCEL_REASON, cancelReason);
    data.put(
      TemplateFieldConstants.SALESREP_NAME,
      extractSalesRepNameFromEventName(calendlyEvent.getPayload().getEventType().getName())
    );

    return data;
  }

  public static String extractSalesRepNameFromEventName(final String eventName) {
    Matcher matcher = Pattern.compile("Camunda Enterprise Platform Demo for (.+)").matcher(eventName);

    if (matcher.matches()) {
      return matcher.group(1);
    } else {
      return "n/a";
    }
  }
}
