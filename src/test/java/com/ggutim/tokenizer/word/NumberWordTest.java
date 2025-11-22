package com.ggutim.tokenizer.word;

import static org.junit.jupiter.api.Assertions.*;

import com.ggutim.tokenizer.Token;
import com.ggutim.tokenizer.TokenType;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class NumberWordTest {

  NumberWord word = new NumberWord();

  @ParameterizedTest
  @MethodSource("provideInputsForShouldMatch")
  void shouldMatch(String inputWord) {
    assertTrue(word.match(inputWord));
  }

  private static Stream<Arguments> provideInputsForShouldMatch() {
    return Stream.of(
        Arguments.of("0"),
        Arguments.of("1"),
        Arguments.of("17"),
        Arguments.of("1st"),
        Arguments.of("2nd"),
        Arguments.of("3rd"),
        Arguments.of("4th"),
        Arguments.of("1229th"),
        Arguments.of("12345"));
  }

  @ParameterizedTest
  @MethodSource("provideInputsForShouldNotMatch")
  void shouldNotMatch(String inputWord) {
    assertFalse(word.match(inputWord));
  }

  private static Stream<Arguments> provideInputsForShouldNotMatch() {
    return Stream.of(
        Arguments.of("-1"),
        Arguments.of("-1000"),
        Arguments.of("zero"),
        Arguments.of("1o1"),
        Arguments.of("17:30"),
        Arguments.of("1stt"),
        Arguments.of("nd"),
        Arguments.of("th"),
        Arguments.of("th4th"),
        Arguments.of("h12"));
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
        Arguments.of("0", new Token(TokenType.NUMBER, "0", 0)),
        Arguments.of("1", new Token(TokenType.NUMBER, "1", 1)),
        Arguments.of("17", new Token(TokenType.NUMBER, "17", 17)),
        Arguments.of("1st", new Token(TokenType.NUMBER, "1st", 1)),
        Arguments.of("2nd", new Token(TokenType.NUMBER, "2nd", 2)),
        Arguments.of("3rd", new Token(TokenType.NUMBER, "3rd", 3)),
        Arguments.of("4th", new Token(TokenType.NUMBER, "4th", 4)),
        Arguments.of("1229th", new Token(TokenType.NUMBER, "1229th", 1229)),
        Arguments.of("12345", new Token(TokenType.NUMBER, "12345", 12345)));
  }
}
