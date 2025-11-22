package com.ggutim.parser.rule;

import com.ggutim.parser.pipeline.ParseContext;
import com.ggutim.parser.pipeline.rule.rules.RelativeQuantityRule;
import com.ggutim.tokenizer.Token;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Stream;

import static com.ggutim.TestUtils.tokenOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RelativeQuantityRuleTest {

  RelativeQuantityRule rule;

  @BeforeEach
  void setUp() {
    rule = new RelativeQuantityRule();
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
        Arguments.of(List.of(tokenOf("In"), tokenOf("3"), tokenOf("days")), 3),
        Arguments.of(List.of(tokenOf("In"), tokenOf("2"), tokenOf("hours")), 3),
        Arguments.of(List.of(tokenOf("In"), tokenOf("4"), tokenOf("weeks")), 3),
        Arguments.of(List.of(tokenOf("In"), tokenOf("10"), tokenOf("minutes")), 3),
        Arguments.of(List.of(tokenOf("In"), tokenOf("6"), tokenOf("months")), 3),
        Arguments.of(List.of(tokenOf("In"), tokenOf("5"), tokenOf("years")), 3),
        Arguments.of(List.of(tokenOf("3"), tokenOf("days"), tokenOf("ago")), 3),
        Arguments.of(List.of(tokenOf("2"), tokenOf("hours"), tokenOf("ago")), 3),
        Arguments.of(List.of(tokenOf("4"), tokenOf("weeks"), tokenOf("ago")), 3),
        Arguments.of(List.of(tokenOf("15"), tokenOf("minutes"), tokenOf("ago")), 3),
        Arguments.of(List.of(tokenOf("8"), tokenOf("months"), tokenOf("ago")), 3),
        Arguments.of(List.of(tokenOf("1"), tokenOf("years"), tokenOf("ago")), 3));
  }

  @ParameterizedTest
  @MethodSource("provideInputsForShouldApplyCorrectly")
  void shouldApplyCorrectly(
      List<Token> inputTokens, ChronoUnit unit, Integer expectedRelativeValue) {
    LocalDateTime reference = LocalDateTime.of(2026, 6, 15, 12, 0);
    ParseContext ctx = new ParseContext(reference);
    rule.matches(inputTokens, 0);
    rule.apply(ctx, inputTokens, 0);
    assertEquals(expectedRelativeValue, ctx.getRelative(unit));
  }

  private static Stream<Arguments> provideInputsForShouldApplyCorrectly() {
    return Stream.of(
        Arguments.of(List.of(tokenOf("In"), tokenOf("3"), tokenOf("days")), ChronoUnit.DAYS, 3),
        Arguments.of(List.of(tokenOf("In"), tokenOf("2"), tokenOf("hours")), ChronoUnit.HOURS, 2),
        Arguments.of(List.of(tokenOf("In"), tokenOf("4"), tokenOf("weeks")), ChronoUnit.WEEKS, 4),
        Arguments.of(
            List.of(tokenOf("In"), tokenOf("10"), tokenOf("minutes")), ChronoUnit.MINUTES, 10),
        Arguments.of(
            List.of(tokenOf("In"), tokenOf("6"), tokenOf("months")), ChronoUnit.MONTHS, 6),
        Arguments.of(List.of(tokenOf("In"), tokenOf("5"), tokenOf("years")), ChronoUnit.YEARS, 5),
        Arguments.of(List.of(tokenOf("3"), tokenOf("days"), tokenOf("ago")), ChronoUnit.DAYS, -3),
        Arguments.of(List.of(tokenOf("2"), tokenOf("hours"), tokenOf("ago")), ChronoUnit.HOURS, -2),
        Arguments.of(
            List.of(tokenOf("4"), tokenOf("weeks"), tokenOf("ago")), ChronoUnit.WEEKS, -4),
        Arguments.of(
            List.of(tokenOf("15"), tokenOf("minutes"), tokenOf("ago")), ChronoUnit.MINUTES, -15),
        Arguments.of(
            List.of(tokenOf("8"), tokenOf("months"), tokenOf("ago")), ChronoUnit.MONTHS, -8),
        Arguments.of(
            List.of(tokenOf("1"), tokenOf("years"), tokenOf("ago")), ChronoUnit.YEARS, -1));
  }
}
