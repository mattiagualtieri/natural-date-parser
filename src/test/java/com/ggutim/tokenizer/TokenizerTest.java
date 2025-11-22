package com.ggutim.tokenizer;

import static com.ggutim.tokenizer.word.DateKeywordWord.DateKeyword.*;
import static com.ggutim.tokenizer.word.KeywordWord.Keyword.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ggutim.normalizer.pipeline.DefaultNormalizationPipeline;
import com.ggutim.tokenizer.word.MeridiemWord;
import com.ggutim.tokenizer.word.WordTokenizer;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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
        Arguments.of("Today", List.of(new Token(TokenType.DATE_KEYWORD, "today", TODAY))),
        Arguments.of(
            "Tomorrow at 5pm",
            List.of(
                new Token(TokenType.DATE_KEYWORD, "tomorrow", TOMORROW),
                new Token(TokenType.KEYWORD, "at", AT),
                new Token(TokenType.TIME, "5pm", LocalTime.of(17, 0)))),
        Arguments.of(
            "two days ago",
            List.of(
                new Token(TokenType.NUMBER, "2", 2),
                new Token(TokenType.UNIT, "days", ChronoUnit.DAYS),
                new Token(TokenType.KEYWORD, "ago", AGO))),
        Arguments.of(
            "Day after tomorrow",
            List.of(new Token(TokenType.DATE_KEYWORD, "day_after_tomorrow", DAY_AFTER_TOMORROW))),
        Arguments.of(
            "Sunday, March 3rd 2024",
            List.of(
                new Token(TokenType.WEEKDAY, "sunday", DayOfWeek.SUNDAY),
                new Token(TokenType.MONTH, "march", Month.MARCH),
                new Token(TokenType.NUMBER, "3rd", 3),
                new Token(TokenType.YEAR, "2024", 2024))),
        Arguments.of(
            "AT MIDNIGHT",
            List.of(
                new Token(TokenType.KEYWORD, "at", AT),
                new Token(TokenType.TIME_KEYWORD, "midnight", LocalTime.MIDNIGHT))),
        Arguments.of(
            "5 p.m.",
            List.of(
                new Token(TokenType.NUMBER, "5", 5),
                new Token(TokenType.MERIDIEM, "p.m.", MeridiemWord.Meridiem.PM))),
        Arguments.of(
            "twenty-one minutes",
            List.of(
                new Token(TokenType.NUMBER, "21", 21),
                new Token(TokenType.UNIT, "minutes", ChronoUnit.MINUTES))),
        Arguments.of(
            "  Tomorrow   at   noon  ",
            List.of(
                new Token(TokenType.DATE_KEYWORD, "tomorrow", TOMORROW),
                new Token(TokenType.KEYWORD, "at", AT),
                new Token(TokenType.TIME_KEYWORD, "noon", LocalTime.NOON))),
        Arguments.of(
            "random!!! word",
            List.of(
                new Token(TokenType.UNKNOWN, "random", "random"),
                new Token(TokenType.UNKNOWN, "word", "word"))));
  }
}
