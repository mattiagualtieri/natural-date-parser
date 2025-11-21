package com.guti.tokenizer.word;

import static org.junit.jupiter.api.Assertions.*;

import com.guti.tokenizer.Token;
import com.guti.tokenizer.TokenType;

import java.time.DayOfWeek;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class WeekdayWordTest {

  WeekdayWord word = new WeekdayWord();

  @ParameterizedTest
  @MethodSource("provideInputsForShouldMatch")
  void shouldMatch(String inputWord) {
    assertTrue(word.match(inputWord));
  }

  private static Stream<Arguments> provideInputsForShouldMatch() {
    return Stream.of(
        Arguments.of("monday"),
        Arguments.of("tuesday"),
        Arguments.of("mon"),
        Arguments.of("tue"),
        Arguments.of("tues"),
        Arguments.of("sun"));
  }

  @ParameterizedTest
  @MethodSource("provideInputsForShouldNotMatch")
  void shouldNotMatch(String inputWord) {
    assertFalse(word.match(inputWord));
  }

  private static Stream<Arguments> provideInputsForShouldNotMatch() {
    return Stream.of(
        Arguments.of("monday_"),
        Arguments.of("monn"),
        Arguments.of("tuesd"),
        Arguments.of("random"));
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
        Arguments.of("monday", new Token(TokenType.WEEKDAY, "monday", DayOfWeek.MONDAY)),
        Arguments.of("tuesday", new Token(TokenType.WEEKDAY, "tuesday", DayOfWeek.TUESDAY)),
        Arguments.of("mon", new Token(TokenType.WEEKDAY, "mon", DayOfWeek.MONDAY)),
        Arguments.of("tue", new Token(TokenType.WEEKDAY, "tue", DayOfWeek.TUESDAY)),
        Arguments.of("sun", new Token(TokenType.WEEKDAY, "sun", DayOfWeek.SUNDAY)));
  }
}
