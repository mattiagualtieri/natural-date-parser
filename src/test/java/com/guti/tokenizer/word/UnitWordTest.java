package com.guti.tokenizer.word;

import static org.junit.jupiter.api.Assertions.*;

import com.guti.tokenizer.Token;
import com.guti.tokenizer.TokenType;

import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class UnitWordTest {

  UnitWord word = new UnitWord();

  @ParameterizedTest
  @MethodSource("provideInputsForShouldMatch")
  void shouldMatch(String inputWord) {
    assertTrue(word.match(inputWord));
  }

  private static Stream<Arguments> provideInputsForShouldMatch() {
    return Stream.of(
        Arguments.of("second"),
        Arguments.of("seconds"),
        Arguments.of("sec"),
        Arguments.of("minutes"),
        Arguments.of("mins"),
        Arguments.of("hrs"),
        Arguments.of("days"),
        Arguments.of("months"),
        Arguments.of("yrs"));
  }

  @ParameterizedTest
  @MethodSource("provideInputsForShouldNotMatch")
  void shouldNotMatch(String inputWord) {
    assertFalse(word.match(inputWord));
  }

  private static Stream<Arguments> provideInputsForShouldNotMatch() {
    return Stream.of(
        Arguments.of("secss"),
        Arguments.of("mnts"),
        Arguments.of("hs"),
        Arguments.of("asdfg"),
        Arguments.of("just"));
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
        Arguments.of("second", new Token(TokenType.UNIT, "second", ChronoUnit.SECONDS)),
        Arguments.of("seconds", new Token(TokenType.UNIT, "seconds", ChronoUnit.SECONDS)),
        Arguments.of("sec", new Token(TokenType.UNIT, "sec", ChronoUnit.SECONDS)),
        Arguments.of("minutes", new Token(TokenType.UNIT, "minutes", ChronoUnit.MINUTES)),
        Arguments.of("mins", new Token(TokenType.UNIT, "mins", ChronoUnit.MINUTES)),
        Arguments.of("hrs", new Token(TokenType.UNIT, "hrs", ChronoUnit.HOURS)),
        Arguments.of("days", new Token(TokenType.UNIT, "days", ChronoUnit.DAYS)),
        Arguments.of("months", new Token(TokenType.UNIT, "months", ChronoUnit.MONTHS)),
        Arguments.of("yrs", new Token(TokenType.UNIT, "yrs", ChronoUnit.YEARS)));
  }
}
