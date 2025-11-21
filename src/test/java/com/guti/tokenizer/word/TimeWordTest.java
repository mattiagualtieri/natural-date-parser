package com.guti.tokenizer.word;

import com.guti.tokenizer.Token;
import com.guti.tokenizer.TokenType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TimeWordTest {

  TimeWord word = new TimeWord();

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
        Arguments.of("17:00", new Token(TokenType.TIME, "17:00", LocalTime.of(17, 0))),
        Arguments.of("17:30", new Token(TokenType.TIME, "17:30", LocalTime.of(17, 30))),
        Arguments.of("5:30 am", new Token(TokenType.TIME, "5:30 am", LocalTime.of(5, 30))),
        Arguments.of("5:30 pm", new Token(TokenType.TIME, "5:30 pm", LocalTime.of(17, 30))),
        Arguments.of("5:30am", new Token(TokenType.TIME, "5:30am", LocalTime.of(5, 30))),
        Arguments.of("5:30pm", new Token(TokenType.TIME, "5:30pm", LocalTime.of(17, 30))));
  }
}
