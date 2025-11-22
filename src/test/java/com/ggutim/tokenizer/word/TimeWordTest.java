package com.ggutim.tokenizer.word;

import com.ggutim.tokenizer.Token;
import com.ggutim.tokenizer.TokenType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TimeWordTest {

  TimeWord word = new TimeWord();

  @ParameterizedTest
  @MethodSource("provideInputsForShouldMatch")
  void shouldMatch(String inputWord) {
    assertTrue(word.match(inputWord));
  }

  private static Stream<Arguments> provideInputsForShouldMatch() {
    return Stream.of(
        Arguments.of("17"),
        Arguments.of("17:00"),
        Arguments.of("17:30"),
        Arguments.of("5:30 am"),
        Arguments.of("5:30 pm"),
        Arguments.of("5:30am"),
        Arguments.of("5:30pm"));
  }

  @ParameterizedTest
  @MethodSource("provideInputsForShouldNotMatch")
  void shouldNotMatch(String inputWord) {
    assertFalse(word.match(inputWord));
  }

  private static Stream<Arguments> provideInputsForShouldNotMatch() {
    return Stream.of(
        Arguments.of("~17"),
        Arguments.of("17."),
        Arguments.of("17.30"),
        Arguments.of("5 ammm"),
        Arguments.of("five thirty"),
        Arguments.of("5,30 am"),
        Arguments.of("5,30 pm"));
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
        Arguments.of("17", new Token(TokenType.TIME, "17", LocalTime.of(17, 0))),
        Arguments.of("17:00", new Token(TokenType.TIME, "17:00", LocalTime.of(17, 0))),
        Arguments.of("17:30", new Token(TokenType.TIME, "17:30", LocalTime.of(17, 30))),
        Arguments.of("5:30 am", new Token(TokenType.TIME, "5:30 am", LocalTime.of(5, 30))),
        Arguments.of("5:30 pm", new Token(TokenType.TIME, "5:30 pm", LocalTime.of(17, 30))),
        Arguments.of("5:30am", new Token(TokenType.TIME, "5:30am", LocalTime.of(5, 30))),
        Arguments.of("5:30pm", new Token(TokenType.TIME, "5:30pm", LocalTime.of(17, 30))));
  }

  @ParameterizedTest
  @MethodSource("provideInputsForShouldTokenizeAsUnknown")
  void shouldTokenizeAsUnknown(String inputWord) {
    Token token = word.tokenize(inputWord);
    assertEquals(TokenType.UNKNOWN, token.type());
  }

  private static Stream<Arguments> provideInputsForShouldTokenizeAsUnknown() {
    return Stream.of(
        Arguments.of("25:00"),
        Arguments.of("24:59"),
        Arguments.of("13am"),
        Arguments.of("13:15 pm"),
        Arguments.of("12:75"),
        Arguments.of("7:99"),
        Arguments.of("0pm"));
  }
}
