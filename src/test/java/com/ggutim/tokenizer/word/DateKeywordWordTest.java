package com.ggutim.tokenizer.word;

import static org.junit.jupiter.api.Assertions.*;

import com.ggutim.tokenizer.Token;
import com.ggutim.tokenizer.TokenType;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DateKeywordWordTest {

  DateKeywordWord word = new DateKeywordWord();

  @ParameterizedTest
  @MethodSource("provideInputsForShouldMatch")
  void shouldMatch(String inputWord) {
    assertTrue(word.match(inputWord));
  }

  private static Stream<Arguments> provideInputsForShouldMatch() {
    return Stream.of(
        Arguments.of("today"),
        Arguments.of("tomorrow"),
        Arguments.of("yesterday"),
        Arguments.of("day_before_yesterday"),
        Arguments.of("day_after_tomorrow"));
  }

  @ParameterizedTest
  @MethodSource("provideInputsForShouldNotMatch")
  void shouldNotMatch(String inputWord) {
    assertFalse(word.match(inputWord));
  }

  private static Stream<Arguments> provideInputsForShouldNotMatch() {
    return Stream.of(
        Arguments.of("todayy"),
        Arguments.of("tomorroww"),
        Arguments.of("tomorow"),
        Arguments.of("day_after_yesterday"),
        Arguments.of("day_before_tomorrow"));
  }

  @ParameterizedTest
  @MethodSource("provideInputsForShouldTokenize")
  void shouldTokenize(String inputWord, Token expectedToken) {
    Token token = word.tokenize(inputWord);
    assertEquals(expectedToken.type(), token.type());
    assertEquals(expectedToken.value(), token.value());
    assertEquals(expectedToken.text(), token.text());
  }

  private static Stream<Arguments> provideInputsForShouldTokenize() {
    return Stream.of(
        Arguments.of(
            "today", new Token(TokenType.DATE_KEYWORD, "today", DateKeywordWord.DateKeyword.TODAY)),
        Arguments.of(
            "tomorrow",
            new Token(TokenType.DATE_KEYWORD, "tomorrow", DateKeywordWord.DateKeyword.TOMORROW)),
        Arguments.of(
            "yesterday",
            new Token(TokenType.DATE_KEYWORD, "yesterday", DateKeywordWord.DateKeyword.YESTERDAY)),
        Arguments.of(
            "day_before_yesterday",
            new Token(
                TokenType.DATE_KEYWORD,
                "day_before_yesterday",
                DateKeywordWord.DateKeyword.DAY_BEFORE_YESTERDAY)),
        Arguments.of(
            "day_after_tomorrow",
            new Token(
                TokenType.DATE_KEYWORD,
                "day_after_tomorrow",
                DateKeywordWord.DateKeyword.DAY_AFTER_TOMORROW)));
  }
}
