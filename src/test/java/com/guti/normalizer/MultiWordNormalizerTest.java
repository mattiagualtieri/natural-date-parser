package com.guti.normalizer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MultiWordNormalizerTest {

  private final MultiWordNormalizer normalizer = new MultiWordNormalizer();

  @ParameterizedTest
  @MethodSource("provideInputsForShouldMultiWordNormalize")
  void shouldMultiWordNormalize(String input, String expected) {
    String actual = normalizer.normalize(input);
    assertEquals(expected, actual);
  }

  private static Stream<Arguments> provideInputsForShouldMultiWordNormalize() {
    return Stream.of(
        Arguments.of("today", "today"),
        Arguments.of("the day after tomorrow", "the day_after_tomorrow"),
        Arguments.of("a day before yesterday", "a day_before_yesterday"));
  }
}
