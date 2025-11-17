package com.guti.parser.rule;

import static com.guti.TestUtils.tokenOf;
import static com.guti.tokenizer.constant.Keyword.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.guti.parser.pipeline.ParseContext;
import com.guti.parser.pipeline.rule.rules.WeekdayRule;
import com.guti.tokenizer.Token;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import com.guti.tokenizer.constant.Keyword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class WeekdayRuleTest {

  WeekdayRule rule;

  @BeforeEach
  void setUp() {
    rule = new WeekdayRule();
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
        Arguments.of(List.of(tokenOf("Monday")), 1),
        Arguments.of(List.of(tokenOf("Tuesday")), 1),
        Arguments.of(List.of(tokenOf("Next"), tokenOf("Monday")), 2),
        Arguments.of(List.of(tokenOf("Next"), tokenOf("Tuesday")), 2),
        Arguments.of(List.of(tokenOf("This"), tokenOf("Monday")), 2),
        Arguments.of(List.of(tokenOf("This"), tokenOf("Tuesday")), 2),
        Arguments.of(List.of(tokenOf("Last"), tokenOf("Monday")), 2),
        Arguments.of(List.of(tokenOf("Last"), tokenOf("Tuesday")), 2));
  }

  @ParameterizedTest
  @MethodSource("provideInputsForShouldApplyCorrectly")
  void shouldApplyCorrectly(
      List<Token> inputTokens, DayOfWeek expectedWeekday, Keyword expectedModifier) {
    LocalDateTime reference = LocalDateTime.of(2026, 6, 15, 12, 0);
    ParseContext ctx = new ParseContext(reference);
    rule.matches(inputTokens, 0);
    boolean applied = rule.apply(ctx, inputTokens, 0);
    assertTrue(applied);
    assertEquals(expectedWeekday, ctx.getWeekday());
    assertEquals(expectedModifier, ctx.getWeekdayModifier());
  }

  private static Stream<Arguments> provideInputsForShouldApplyCorrectly() {
    return Stream.of(
        Arguments.of(List.of(tokenOf("Monday")), DayOfWeek.MONDAY, null),
        Arguments.of(List.of(tokenOf("Tuesday")), DayOfWeek.TUESDAY, null),
        Arguments.of(List.of(tokenOf("Next"), tokenOf("Monday")), DayOfWeek.MONDAY, NEXT),
        Arguments.of(List.of(tokenOf("Next"), tokenOf("Tuesday")), DayOfWeek.TUESDAY, NEXT),
        Arguments.of(List.of(tokenOf("This"), tokenOf("Monday")), DayOfWeek.MONDAY, THIS),
        Arguments.of(List.of(tokenOf("This"), tokenOf("Tuesday")), DayOfWeek.TUESDAY, THIS),
        Arguments.of(List.of(tokenOf("Last"), tokenOf("Monday")), DayOfWeek.MONDAY, LAST),
        Arguments.of(List.of(tokenOf("Last"), tokenOf("Tuesday")), DayOfWeek.TUESDAY, LAST));
  }
}
