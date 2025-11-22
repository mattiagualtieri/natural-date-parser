package com.ggutim.tokenizer.word;

import com.ggutim.tokenizer.Token;
import com.ggutim.tokenizer.TokenType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class YearWordTest {

  YearWord word = new YearWord();

  @ParameterizedTest
  @MethodSource("provideInputsForShouldMatch")
  void shouldMatch(String inputWord) {
    assertTrue(word.match(inputWord));
  }

  private static Stream<Arguments> provideInputsForShouldMatch() {
    return Stream.of(
        Arguments.of("1900"),
        Arguments.of("2000"),
        Arguments.of("2025"),
        Arguments.of("1000"),
        Arguments.of("2999"));
  }

  @ParameterizedTest
  @MethodSource("provideInputsForShouldNotMatch")
  void shouldNotMatch(String inputWord) {
    assertFalse(word.match(inputWord));
  }

  private static Stream<Arguments> provideInputsForShouldNotMatch() {
    return Stream.of(
        Arguments.of("0"),
        Arguments.of("1"),
        Arguments.of("999"),
        Arguments.of("3000"),
        Arguments.of("2025a"));
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
        Arguments.of("1900", new Token(TokenType.YEAR, "1900", 1900)),
        Arguments.of("2000", new Token(TokenType.YEAR, "2000", 2000)),
        Arguments.of("2025", new Token(TokenType.YEAR, "2025", 2025)),
        Arguments.of("1000", new Token(TokenType.YEAR, "1000", 1000)),
        Arguments.of("2999", new Token(TokenType.YEAR, "2999", 2999)));
  }
}
