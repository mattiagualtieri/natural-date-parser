package com.guti.parser.rule;

import com.guti.parser.pipeline.rule.rules.AbsoluteDateRule;
import com.guti.tokenizer.Token;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.guti.TestUtils.tokenOf;
import static org.junit.jupiter.api.Assertions.*;

public class AbsoluteDateRuleTest {

  AbsoluteDateRule rule;

  @BeforeEach
  void setUp() {
    rule = new AbsoluteDateRule();
  }

  @ParameterizedTest
  @MethodSource("provideInputsForShouldMatchCorrectPatterns")
  void shouldMatchCorrectPatterns(List<Token> inputTokens, int expectedMatchLength) {
    boolean matches = rule.matches(inputTokens, 0);
    int matchLength = rule.length();
    assertTrue(matches);
    assertEquals(expectedMatchLength, matchLength);
  }

  private static Stream<Arguments> provideInputsForShouldMatchCorrectPatterns() {
    return Stream.of(
        Arguments.of(List.of(tokenOf("January"), tokenOf("5")), 2),
        Arguments.of(List.of(tokenOf("Feb"), tokenOf("12")), 2),
        Arguments.of(List.of(tokenOf("5"), tokenOf("Jan")), 2),
        Arguments.of(List.of(tokenOf("12"), tokenOf("February")), 2),
        Arguments.of(List.of(tokenOf("February"), tokenOf("12"), tokenOf("2026")), 3),
        Arguments.of(List.of(tokenOf("Jan"), tokenOf("5"), tokenOf("2026")), 3));
  }
}
