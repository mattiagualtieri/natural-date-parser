package com.guti.parser.rule;

import static com.guti.TestUtils.tokenOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.guti.parser.pipeline.ParseContext;
import com.guti.parser.pipeline.rule.rules.TimeRule;
import com.guti.tokenizer.Token;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TimeRuleTest {

  TimeRule rule;

  @BeforeEach
  void setUp() {
    rule = new TimeRule();
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
        Arguments.of(List.of(tokenOf("At"), tokenOf("5:00")), 2),
        Arguments.of(List.of(tokenOf("At"), tokenOf("17:00")), 2),
        Arguments.of(List.of(tokenOf("At"), tokenOf("5")), 2),
        Arguments.of(List.of(tokenOf("At"), tokenOf("17")), 2),
        Arguments.of(List.of(tokenOf("At"), tokenOf("noon")), 2),
        Arguments.of(List.of(tokenOf("At"), tokenOf("midnight")), 2),
        Arguments.of(List.of(tokenOf("5:00")), 1),
        Arguments.of(List.of(tokenOf("17:00")), 1),
        Arguments.of(List.of(tokenOf("noon")), 1),
        Arguments.of(List.of(tokenOf("midnight")), 1));
  }

  @ParameterizedTest
  @MethodSource("provideInputsForShouldApplyCorrectly")
  void shouldApplyCorrectly(List<Token> inputTokens, LocalTime expectedTime) {
    LocalDateTime reference = LocalDateTime.of(2026, 6, 15, 12, 0);
    ParseContext ctx = new ParseContext(reference);
    rule.matches(inputTokens, 0);
    boolean applied = rule.apply(ctx, inputTokens, 0);
    assertTrue(applied);
    assertEquals(expectedTime, ctx.getExplicitTime());
  }

  private static Stream<Arguments> provideInputsForShouldApplyCorrectly() {
    return Stream.of(
        Arguments.of(List.of(tokenOf("At"), tokenOf("5:00")), LocalTime.of(5, 0)),
        Arguments.of(List.of(tokenOf("At"), tokenOf("17:00")), LocalTime.of(17, 0)),
        Arguments.of(List.of(tokenOf("At"), tokenOf("5")), LocalTime.of(5, 0)),
        Arguments.of(List.of(tokenOf("At"), tokenOf("17")), LocalTime.of(17, 0)),
        Arguments.of(List.of(tokenOf("At"), tokenOf("noon")), LocalTime.NOON),
        Arguments.of(List.of(tokenOf("At"), tokenOf("midnight")), LocalTime.MIDNIGHT),
        Arguments.of(List.of(tokenOf("5:00")), LocalTime.of(5, 0)),
        Arguments.of(List.of(tokenOf("17:00")), LocalTime.of(17, 0)),
        Arguments.of(List.of(tokenOf("noon")), LocalTime.NOON),
        Arguments.of(List.of(tokenOf("midnight")), LocalTime.MIDNIGHT));
  }
}
