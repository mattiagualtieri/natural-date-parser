package com.ggutim.tokenizer.word;

import static org.junit.jupiter.api.Assertions.*;

import com.ggutim.tokenizer.Token;
import com.ggutim.tokenizer.TokenType;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KeywordWordTest {

  KeywordWord word = new KeywordWord();

  @ParameterizedTest
  @MethodSource("provideInputsForShouldMatch")
  void shouldMatch(String inputWord) {
    assertTrue(word.match(inputWord));
  }

  private static Stream<Arguments> provideInputsForShouldMatch() {
    return Stream.of(
        Arguments.of("at"),
        Arguments.of("of"),
        Arguments.of("in"),
        Arguments.of("ago"),
        Arguments.of("from"),
        Arguments.of("next"),
        Arguments.of("this"),
        Arguments.of("last"));
  }

  @ParameterizedTest
  @MethodSource("provideInputsForShouldNotMatch")
  void shouldNotMatch(String inputWord) {
    assertFalse(word.match(inputWord));
  }

  private static Stream<Arguments> provideInputsForShouldNotMatch() {
    return Stream.of(Arguments.of("random"), Arguments.of("word"));
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
        Arguments.of("at", new Token(TokenType.KEYWORD, "at", KeywordWord.Keyword.AT)),
        Arguments.of("of", new Token(TokenType.KEYWORD, "of", KeywordWord.Keyword.OF)),
        Arguments.of("in", new Token(TokenType.KEYWORD, "in", KeywordWord.Keyword.IN)),
        Arguments.of("ago", new Token(TokenType.KEYWORD, "ago", KeywordWord.Keyword.AGO)),
        Arguments.of("from", new Token(TokenType.KEYWORD, "from", KeywordWord.Keyword.FROM)),
        Arguments.of("next", new Token(TokenType.KEYWORD, "next", KeywordWord.Keyword.NEXT)),
        Arguments.of("this", new Token(TokenType.KEYWORD, "this", KeywordWord.Keyword.THIS)),
        Arguments.of("last", new Token(TokenType.KEYWORD, "last", KeywordWord.Keyword.LAST)));
  }
}
