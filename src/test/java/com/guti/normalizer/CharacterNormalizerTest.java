package com.guti.normalizer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CharacterNormalizerTest {

  private final CharacterNormalizer normalizer = new CharacterNormalizer();

  @ParameterizedTest
  @MethodSource("provideInputsForShouldCharacterNormalize")
  void shouldCharacterNormalize(String input, String expected) {
    String actual = normalizer.normalize(input);
    assertEquals(expected, actual);
  }

  private static Stream<Arguments> provideInputsForShouldCharacterNormalize() {
    return Stream.of(
        Arguments.of("today!", "today"),
        Arguments.of("today?", "today"),
        Arguments.of("today!?!?", "today"),
        Arguments.of("today*=()", "today"));
  }
}
