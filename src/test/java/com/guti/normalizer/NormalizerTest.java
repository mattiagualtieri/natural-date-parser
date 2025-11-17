package com.guti.normalizer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class NormalizerTest {

  private final Normalizer normalizer = new Normalizer();

  @Test
  void normalizerBasic_Trim() {
    String result = normalizer.normalize("  today   ");
    assertNotNull(result);
    assertEquals("today", result);
  }

  @Test
  void normalizerBasic_Lower() {
    String result = normalizer.normalize("Today TOMORROW");
    assertNotNull(result);
    assertEquals("today tomorrow", result);
  }

  @Test
  void normalizerBasic_MultiWord() {
    String result = normalizer.normalize("The day after Tomorrow");
    assertNotNull(result);
    assertEquals("the day_after_tomorrow", result);
  }
}
