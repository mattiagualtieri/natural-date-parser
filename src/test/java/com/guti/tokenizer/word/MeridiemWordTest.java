package com.guti.tokenizer.word;

import static org.junit.jupiter.api.Assertions.*;

import com.guti.tokenizer.Token;
import com.guti.tokenizer.TokenType;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MeridiemWordTest {

  MeridiemWord word = new MeridiemWord();

  @ParameterizedTest
  @MethodSource("provideInputsForShouldMatch")
  void shouldMatch(String inputWord) {
    assertTrue(word.match(inputWord));
  }

  private static Stream<Arguments> provideInputsForShouldMatch() {
    return Stream.of(
        Arguments.of("am"),
        Arguments.of("pm"),
        Arguments.of("a.m."),
        Arguments.of("p.m."),
        Arguments.of("am."),
        Arguments.of("pm."));
  }

  @ParameterizedTest
  @MethodSource("provideInputsForShouldNotMatch")
  void shouldNotMatch(String inputWord) {
    assertFalse(word.match(inputWord));
  }

  private static Stream<Arguments> provideInputsForShouldNotMatch() {
    return Stream.of(
        Arguments.of("a m"),
        Arguments.of("p m"),
        Arguments.of("a..m."),
        Arguments.of("aaa"),
        Arguments.of("ppp"),
        Arguments.of("mmm"));
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
        Arguments.of("am", new Token(TokenType.MERIDIEM, "am", MeridiemWord.Meridiem.AM)),
        Arguments.of("pm", new Token(TokenType.MERIDIEM, "pm", MeridiemWord.Meridiem.PM)),
        Arguments.of("a.m.", new Token(TokenType.MERIDIEM, "a.m.", MeridiemWord.Meridiem.AM)),
        Arguments.of("p.m.", new Token(TokenType.MERIDIEM, "p.m.", MeridiemWord.Meridiem.PM)),
        Arguments.of("am.", new Token(TokenType.MERIDIEM, "am.", MeridiemWord.Meridiem.AM)),
        Arguments.of("pm.", new Token(TokenType.MERIDIEM, "pm.", MeridiemWord.Meridiem.PM)));
  }
}
