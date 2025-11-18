package com.guti.normalizer;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LowerCaseNormalizerTest {

  private final LowerCaseNormalizer normalizer = new LowerCaseNormalizer();

  @ParameterizedTest
  @MethodSource("provideInputsForShouldLowerCaseNormalize")
  void shouldLowerCaseNormalize(String input, String expected) {
    String actual = normalizer.normalize(input);
    assertEquals(expected, actual);
  }

  private static Stream<Arguments> provideInputsForShouldLowerCaseNormalize() {
    return Stream.of(
        Arguments.of("Today", "today"),
        Arguments.of("TodaY", "today"),
        Arguments.of("TODAY", "today"));
  }
}
