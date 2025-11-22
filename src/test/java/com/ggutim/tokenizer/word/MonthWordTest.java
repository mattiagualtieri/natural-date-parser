package com.ggutim.tokenizer.word;

import static org.junit.jupiter.api.Assertions.*;

import com.ggutim.tokenizer.Token;
import com.ggutim.tokenizer.TokenType;

import java.time.Month;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MonthWordTest {

  MonthWord word = new MonthWord();

  @ParameterizedTest
  @MethodSource("provideInputsForShouldMatch")
  void shouldMatch(String inputWord) {
    assertTrue(word.match(inputWord));
  }

  private static Stream<Arguments> provideInputsForShouldMatch() {
    return Stream.of(
        Arguments.of("jan"),
        Arguments.of("january"),
        Arguments.of("feb"),
        Arguments.of("february"));
  }

  @ParameterizedTest
  @MethodSource("provideInputsForShouldNotMatch")
  void shouldNotMatch(String inputWord) {
    assertFalse(word.match(inputWord));
  }

  private static Stream<Arguments> provideInputsForShouldNotMatch() {
    return Stream.of(
        Arguments.of("jann"),
        Arguments.of("ja"),
        Arguments.of("februari"),
        Arguments.of("februaryy"));
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
        Arguments.of("jan", new Token(TokenType.MONTH, "jan", Month.JANUARY)),
        Arguments.of("january", new Token(TokenType.MONTH, "january", Month.JANUARY)),
        Arguments.of("feb", new Token(TokenType.MONTH, "feb", Month.FEBRUARY)),
        Arguments.of("february", new Token(TokenType.MONTH, "february", Month.FEBRUARY)));
  }
}
