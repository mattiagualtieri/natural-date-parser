package com.ggutim.tokenizer.word;

import static com.ggutim.tokenizer.word.DateKeywordWord.DateKeyword.TOMORROW;
import static com.ggutim.tokenizer.word.KeywordWord.Keyword.AGO;
import static com.ggutim.tokenizer.word.MeridiemWord.Meridiem.AM;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ggutim.tokenizer.Token;
import com.ggutim.tokenizer.TokenType;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class WordTokenizerTest {

  private final WordTokenizer wordTokenizer = new WordTokenizer();

  @ParameterizedTest
  @MethodSource("provideInputsForShouldTokenize")
  void shouldTokenize(String inputWord, Token expectedToken) {
    Token token = wordTokenizer.tokenize(inputWord);
    assertEquals(expectedToken.type(), token.type());
    assertEquals(expectedToken.text(), token.text());
    assertEquals(expectedToken.value(), token.value());
  }

  private static Stream<Arguments> provideInputsForShouldTokenize() {
    return Stream.of(
        Arguments.of("february", new Token(TokenType.MONTH, "february", Month.FEBRUARY)),
        Arguments.of("mon", new Token(TokenType.WEEKDAY, "mon", DayOfWeek.MONDAY)),
        Arguments.of("ago", new Token(TokenType.KEYWORD, "ago", AGO)),
        Arguments.of("tomorrow", new Token(TokenType.DATE_KEYWORD, "tomorrow", TOMORROW)),
        Arguments.of("midnight", new Token(TokenType.TIME_KEYWORD, "midnight", LocalTime.MIDNIGHT)),
        Arguments.of("hrs", new Token(TokenType.UNIT, "hrs", ChronoUnit.HOURS)),
        Arguments.of("2025", new Token(TokenType.YEAR, "2025", 2025)),
        Arguments.of("30th", new Token(TokenType.NUMBER, "30th", 30)),
        Arguments.of("7pm", new Token(TokenType.TIME, "7pm", LocalTime.of(19, 0))),
        Arguments.of("am", new Token(TokenType.MERIDIEM, "am", AM)),
        Arguments.of("random", new Token(TokenType.UNKNOWN, "random", "random")));
  }
}
