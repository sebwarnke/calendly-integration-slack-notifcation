package com.sebwarnke.calendlyintegration.util;

import com.sebwarnke.calendlyintegration.model.CalendlyEvent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

  public static Map<String, String> templateDataFromCalendlyEvent(final CalendlyEvent calendlyEvent) {
    HashMap<String, String> data = new HashMap<>();

    String startTimeNative = calendlyEvent.getPayload().getEvent().getStartTime();
    String endTimeNative = calendlyEvent.getPayload().getEvent().getEndTime();

    LocalDateTime startLocalDateTime = LocalDateTime.parse(startTimeNative, DateTimeFormatter.ISO_DATE_TIME);
    LocalDateTime endLocalDateTime = LocalDateTime.parse(endTimeNative, DateTimeFormatter.ISO_DATE_TIME);
    String customEventDate = startLocalDateTime.format(DateTimeFormatter.ofPattern(TemplateFieldConstants.DATE_FORMAT));
    String customStartTime = startLocalDateTime.format(DateTimeFormatter.ofPattern(TemplateFieldConstants.TIME_FORMAT))
            + (startLocalDateTime.getHour() > 11 ? "pm" : "am");
    String customEndTime = endLocalDateTime.format(DateTimeFormatter.ofPattern(TemplateFieldConstants.TIME_FORMAT))
            + (endLocalDateTime.getHour() > 11 ? "pm" : "am");

    data.put(TemplateFieldConstants.EVENT_NAME, calendlyEvent.getPayload().getEventType().getName());
    data.put(TemplateFieldConstants.COMPANY, calendlyEvent.getPayload().getQuestionsAndResponses().get_1Response());
    data.put(TemplateFieldConstants.INVITEE_EMAIL, calendlyEvent.getPayload().getInvitee().getEmail());
    data.put(TemplateFieldConstants.INVITEE_NAME, calendlyEvent.getPayload().getInvitee().getName());
    data.put(TemplateFieldConstants.EVENT_DATE, customEventDate);
    data.put(TemplateFieldConstants.EVENT_TIME_START, customStartTime);
    data.put(TemplateFieldConstants.EVENT_TIME_END, customEndTime);
    data.put(TemplateFieldConstants.CONSULTANT_NAME, calendlyEvent.getPayload().getEvent().getAssignedTo().get(0));
    data.put(TemplateFieldConstants.ZOOM_LINK, calendlyEvent.getPayload().getEvent().getLocation());
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
