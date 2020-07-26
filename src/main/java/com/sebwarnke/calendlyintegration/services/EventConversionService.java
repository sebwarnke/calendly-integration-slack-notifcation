package com.sebwarnke.calendlyintegration.services;

import com.sebwarnke.calendlyintegration.model.CalendlyEvent;
import com.sebwarnke.calendlyintegration.util.Util;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EventConversionService {
  public Map<String, String> handleCalendlyEvent(CalendlyEvent calendlyEvent) {
    return Util.templateDataFromCalendlyEvent(calendlyEvent);
  }
}
