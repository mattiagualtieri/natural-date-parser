package com.ggutim.tokenizer.word;

import static org.junit.jupiter.api.Assertions.*;

import com.ggutim.tokenizer.Token;
import com.ggutim.tokenizer.TokenType;
import java.time.LocalTime;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TimeKeywordWordTest {

  TimeKeywordWord word = new TimeKeywordWord();

  @ParameterizedTest
  @MethodSource("provideInputsForShouldMatch")
  void shouldMatch(String inputWord) {
    assertTrue(word.match(inputWord));
  }

  private static Stream<Arguments> provideInputsForShouldMatch() {
    return Stream.of(Arguments.of("noon"), Arguments.of("midnight"));
  }

  @ParameterizedTest
  @MethodSource("provideInputsForShouldNotMatch")
  void shouldNotMatch(String inputWord) {
    assertFalse(word.match(inputWord));
  }

  private static Stream<Arguments> provideInputsForShouldNotMatch() {
    return Stream.of(Arguments.of("noo"), Arguments.of("mid"));
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
        Arguments.of("noon", new Token(TokenType.TIME_KEYWORD, "noon", LocalTime.NOON)),
        Arguments.of(
            "midnight", new Token(TokenType.TIME_KEYWORD, "midnight", LocalTime.MIDNIGHT)));
  }
}
