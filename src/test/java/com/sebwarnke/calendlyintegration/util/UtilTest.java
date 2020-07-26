package com.sebwarnke.calendlyintegration.util;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class UtilTest {

  @Test
  public void givenEventIsDemo_whenExtractSalesRepNameFromEventName_ReturnSalesRepName() {
    /* given */
    String eventName = "Camunda Enterprise Platform Demo for Dagobert Duck";
    /* when */
    String salesRepName = Util.extractSalesRepNameFromEventName(eventName);
    /* then */
    assertThat(salesRepName).isEqualTo("Dagobert Duck");
  }

  @Test
  public void givenEventIsDemoWithoutSalesRepName_whenExtractSalesRepNameFromEventName_ReturnNA() {
    /* given */
    String eventName = "Camunda Enterprise Platform Demo";
    /* when */
    String salesRepName = Util.extractSalesRepNameFromEventName(eventName);
    /* then */
    assertThat(salesRepName).isEqualTo("n/a");
  }

  @Test
  public void givenEventIsNotDemo_whenExtractSalesRepNameFromEventName_ReturnNA() {
    /* given */
    String eventName = "Scoping Call for Dagobert Duck";
    /* when */
    String salesRepName = Util.extractSalesRepNameFromEventName(eventName);
    /* then */
    assertThat(salesRepName).isEqualTo("n/a");
  }
}