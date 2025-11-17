package com.guti.parser.rule;

import static com.guti.TestUtils.tokenOf;
import static org.junit.jupiter.api.Assertions.*;

import com.guti.parser.pipeline.ParseContext;
import com.guti.parser.pipeline.rule.rules.RelativeDateKeywordRule;
import com.guti.tokenizer.Token;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class RelativeDateKeywordRuleTest {

  RelativeDateKeywordRule rule;

  @BeforeEach
  void setUp() {
    rule = new RelativeDateKeywordRule();
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
        Arguments.of(List.of(tokenOf("Today")), 1),
        Arguments.of(List.of(tokenOf("Tomorrow")), 1),
        Arguments.of(List.of(tokenOf("Yesterday")), 1),
        Arguments.of(List.of(tokenOf("Day before yesterday")), 1),
        Arguments.of(List.of(tokenOf("Day after tomorrow")), 1));
  }

  @ParameterizedTest
  @MethodSource("provideInputsForShouldApplyCorrectly")
  void shouldApplyCorrectly(List<Token> inputTokens, Integer expectedRelativeDays) {
    LocalDateTime reference = LocalDateTime.of(2026, 6, 15, 12, 0);
    ParseContext ctx = new ParseContext(reference);
    rule.matches(inputTokens, 0);
    rule.apply(ctx, inputTokens, 0);
    assertEquals(expectedRelativeDays, ctx.getRelative(ChronoUnit.DAYS));
  }

  private static Stream<Arguments> provideInputsForShouldApplyCorrectly() {
    return Stream.of(
        Arguments.of(List.of(tokenOf("Today")), 0),
        Arguments.of(List.of(tokenOf("Tomorrow")), 1),
        Arguments.of(List.of(tokenOf("Yesterday")), -1),
        Arguments.of(List.of(tokenOf("Day before yesterday")), -2),
        Arguments.of(List.of(tokenOf("Day after tomorrow")), 2));
  }
}
