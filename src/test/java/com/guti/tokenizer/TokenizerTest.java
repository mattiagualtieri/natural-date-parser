package com.guti.tokenizer;

import com.guti.normalizer.pipeline.DefaultNormalizationPipeline;
import com.guti.tokenizer.constant.DateKeyword;
import com.guti.tokenizer.constant.TokenType;
import com.guti.tokenizer.word.WordTokenizer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TokenizerTest {

  private final Tokenizer tokenizer =
      new Tokenizer(new DefaultNormalizationPipeline(), new WordTokenizer());

  @ParameterizedTest
  @MethodSource("provideInputsForShouldTokenize")
  void shouldTokenize(String input, List<Token> expectedTokens) {
    List<Token> actual = tokenizer.tokenize(input);
    assertEquals(expectedTokens.size(), actual.size());
    for (int i = 0; i < expectedTokens.size(); i++) {
      assertEquals(expectedTokens.get(i), actual.get(i));
    }
  }

  private static Stream<Arguments> provideInputsForShouldTokenize() {
    return Stream.of(
        Arguments.of(
            "Today", List.of(new Token(TokenType.DATE_KEYWORD, "today", DateKeyword.TODAY))),
        Arguments.of(
            "Tomorrow",
            List.of(new Token(TokenType.DATE_KEYWORD, "tomorrow", DateKeyword.TOMORROW))),
        Arguments.of(
            "Yesterday",
            List.of(new Token(TokenType.DATE_KEYWORD, "yesterday", DateKeyword.YESTERDAY))));
  }
}
