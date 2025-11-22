package com.ggutim.normalizer;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberWordNormalizerTest {

  private final NumberWordNormalizer normalizer = new NumberWordNormalizer();

  @ParameterizedTest
  @MethodSource("provideInputsForShouldNumberWordNormalize")
  void shouldNumberWordNormalize(String input, String expected) {
    String actual = normalizer.normalize(input);
    assertEquals(expected, actual);
  }

  private static Stream<Arguments> provideInputsForShouldNumberWordNormalize() {
    return Stream.of(
        Arguments.of("october 11", "october 11"),
        Arguments.of("a week from now", "1 week from now"),
        Arguments.of("twenty of march", "20 of march"),
        Arguments.of("twenty-one of march", "21 of march"));
  }
}
