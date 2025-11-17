package com.guti.normalizer.pipeline;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DefaultNormalizationPipelineTest {

  private final DefaultNormalizationPipeline normalizer = new DefaultNormalizationPipeline();

  @ParameterizedTest
  @MethodSource("provideInputsForShouldNormalize")
  void shouldNormalize(String input, String expected) {
    String actual = normalizer.normalize(input);
    assertEquals(expected, actual);
  }

  private static Stream<Arguments> provideInputsForShouldNormalize() {
    return Stream.of(
        Arguments.of("today", "today"),
        Arguments.of("  today  ", "today"),
        Arguments.of("Today TOMORROW", "today tomorrow"),
        Arguments.of("The day after Tomorrow", "the day_after_tomorrow"),
        Arguments.of("The day, after tomorrow", "the day_after_tomorrow"),
        Arguments.of("The day    before yesterday!!", "the day_before_yesterday"));
  }
}
