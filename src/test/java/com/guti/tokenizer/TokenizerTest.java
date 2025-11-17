package com.guti.tokenizer;

import com.guti.normalizer.Normalizer;
import com.guti.tokenizer.constant.DateKeyword;
import com.guti.tokenizer.constant.Keyword;
import com.guti.tokenizer.constant.TokenType;
import com.guti.tokenizer.word.KeywordWord;
import com.guti.tokenizer.word.WordTokenizer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TokenizerTest {

  private final Tokenizer tokenizer = new Tokenizer(new Normalizer(), new WordTokenizer());

  @ParameterizedTest
  @MethodSource("provideInputsForTokenizeBasicRelativeDay")
  void tokenizeBasicRelativeDate(String input, DateKeyword expected) {
    List<Token> result = tokenizer.tokenize(input);
    assertNotNull(result);
    assertEquals(1, result.size());
    assertEquals(
        new Token(TokenType.DATE_KEYWORD, input.replace(" ", "_").toLowerCase(), expected),
        result.get(0));
  }

  private static Stream<Arguments> provideInputsForTokenizeBasicRelativeDay() {
    return Stream.of(
        Arguments.of("Today", DateKeyword.TODAY),
        Arguments.of("Tomorrow", DateKeyword.TOMORROW),
        Arguments.of("Yesterday", DateKeyword.YESTERDAY));
  }

  @ParameterizedTest
  @MethodSource("provideInputsForTokenizeRelativeOffsetInTimeUnit")
  void tokenizeRelativeOffset_InTimeUnit(String input, int expectedTime, ChronoUnit expectedUnit) {
    String[] inputParts = input.split(" ");
    List<Token> result = tokenizer.tokenize(input);
    assertNotNull(result);
    assertEquals(3, result.size());
    assertEquals(new Token(TokenType.KEYWORD, "in", Keyword.IN), result.get(0));
    assertEquals(
        new Token(TokenType.NUMBER, inputParts[1].toLowerCase(), expectedTime), result.get(1));
    assertEquals(
        new Token(TokenType.UNIT, inputParts[2].toLowerCase(), expectedUnit), result.get(2));
  }

  private static Stream<Arguments> provideInputsForTokenizeRelativeOffsetInTimeUnit() {
    return Stream.of(
        Arguments.of("in 10 seconds", 10, ChronoUnit.SECONDS),
        Arguments.of("in 5 minutes", 5, ChronoUnit.MINUTES),
        Arguments.of("in 72 hours", 72, ChronoUnit.HOURS),
        Arguments.of("in 2 days", 2, ChronoUnit.DAYS),
        Arguments.of("in three days", 3, ChronoUnit.DAYS),
        Arguments.of("in one year", 1, ChronoUnit.YEARS),
        Arguments.of("in a year", 1, ChronoUnit.YEARS));
  }

  @ParameterizedTest
  @MethodSource("provideInputsForTokenizeRelativeOffsetTimeUnitAgo")
  void tokenizeRelativeOffset_TimeUnitAgo(String input, int expectedTime, ChronoUnit expectedUnit) {
    String[] inputParts = input.split(" ");
    List<Token> result = tokenizer.tokenize(input);
    assertNotNull(result);
    assertEquals(3, result.size());
    assertEquals(
        new Token(TokenType.NUMBER, inputParts[0].toLowerCase(), expectedTime), result.get(0));
    assertEquals(
        new Token(TokenType.UNIT, inputParts[1].toLowerCase(), expectedUnit), result.get(1));
    assertEquals(new Token(TokenType.KEYWORD, "ago", "ago"), result.get(2));
  }

  private static Stream<Arguments> provideInputsForTokenizeRelativeOffsetTimeUnitAgo() {
    return Stream.of(
        Arguments.of("10 seconds ago", 10, ChronoUnit.SECONDS),
        Arguments.of("5 minutes ago", 5, ChronoUnit.MINUTES),
        Arguments.of("72 hours ago", 72, ChronoUnit.HOURS),
        Arguments.of("2 days ago", 2, ChronoUnit.DAYS),
        Arguments.of("three days ago", 3, ChronoUnit.DAYS),
        Arguments.of("one year ago", 1, ChronoUnit.YEARS),
        Arguments.of("a year ago", 1, ChronoUnit.YEARS));
  }

  @ParameterizedTest
  @MethodSource("provideInputsForTokenizeWeekdaySimple")
  void tokenizeWeekday_Simple(String input, DayOfWeek expectedDay) {
    String[] inputParts = input.split(" ");
    List<Token> result = tokenizer.tokenize(input);
    assertNotNull(result);
    assertEquals(1, result.size());
    assertEquals(
        new Token(TokenType.WEEKDAY, inputParts[0].toLowerCase(), expectedDay), result.get(0));
  }

  private static Stream<Arguments> provideInputsForTokenizeWeekdaySimple() {
    return Stream.of(
        Arguments.of("monday", DayOfWeek.MONDAY),
        Arguments.of("saturday", DayOfWeek.SATURDAY),
        Arguments.of("thu", DayOfWeek.THURSDAY),
        Arguments.of("FRI", DayOfWeek.FRIDAY),
        Arguments.of("wed", DayOfWeek.WEDNESDAY));
  }

  @ParameterizedTest
  @MethodSource("provideInputsForTokenizeWeekdayComposite")
  void tokenizeWeekday_Composite(String input, DayOfWeek expectedDay) {
    String[] inputParts = input.split(" ");
    List<Token> result = tokenizer.tokenize(input);
    assertNotNull(result);
    assertEquals(2, result.size());
    assertEquals(
        new Token(TokenType.KEYWORD, inputParts[0].toLowerCase(), inputParts[0].toLowerCase()),
        result.get(0));
    assertEquals(
        new Token(TokenType.WEEKDAY, inputParts[1].toLowerCase(), expectedDay), result.get(1));
  }

  private static Stream<Arguments> provideInputsForTokenizeWeekdayComposite() {
    return Stream.of(
        Arguments.of("next monday", DayOfWeek.MONDAY),
        Arguments.of("last friday", DayOfWeek.FRIDAY),
        Arguments.of("this wednesday", DayOfWeek.WEDNESDAY));
  }

  @ParameterizedTest
  @MethodSource("provideInputsForTokenizeWeekdayAtTime")
  void tokenizeWeekdayAtTime(String input, DayOfWeek expectedDay, LocalTime expectedTime) {
    String[] inputParts = input.split(" ");
    List<Token> result = tokenizer.tokenize(input);
    assertNotNull(result);
    assertEquals(3, result.size());
    assertEquals(
        new Token(TokenType.WEEKDAY, inputParts[0].toLowerCase(), expectedDay), result.get(0));
    assertEquals(new Token(TokenType.KEYWORD, "at", "at"), result.get(1));
    assertEquals(
        new Token(TokenType.TIME, inputParts[2].toLowerCase(), expectedTime), result.get(2));
  }

  private static Stream<Arguments> provideInputsForTokenizeWeekdayAtTime() {
    return Stream.of(
        Arguments.of("monday at 1pm", DayOfWeek.MONDAY, LocalTime.of(13, 0)),
        Arguments.of("friday at 12:00", DayOfWeek.FRIDAY, LocalTime.of(12, 0)),
        Arguments.of("wednesday at 23:59", DayOfWeek.WEDNESDAY, LocalTime.of(23, 59)));
  }

  @ParameterizedTest
  @MethodSource("provideInputsForTokenizeAbsoluteDatesMonthAndDay")
  void tokenizeAbsoluteDates_MonthAndDay(String input, List<Token> expectedTokens) {
    List<Token> result = tokenizer.tokenize(input);
    assertNotNull(result);
    assertEquals(expectedTokens.size(), result.size());
    for (int i = 0; i < expectedTokens.size(); i++) {
      assertEquals(expectedTokens.get(i), result.get(i));
    }
  }

  private static Stream<Arguments> provideInputsForTokenizeAbsoluteDatesMonthAndDay() {
    return Stream.of(
        Arguments.of(
            "january 5",
            List.of(
                new Token(TokenType.MONTH, "january", Month.JANUARY),
                new Token(TokenType.NUMBER, "5", 5))),
        Arguments.of(
            "mar 21",
            List.of(
                new Token(TokenType.MONTH, "mar", Month.MARCH),
                new Token(TokenType.NUMBER, "21", 21))),
        Arguments.of(
            "AUGUST 17th",
            List.of(
                new Token(TokenType.MONTH, "august", Month.AUGUST),
                new Token(TokenType.ORDINAL, "17th", 17))),
        Arguments.of(
            "Dec 1st",
            List.of(
                new Token(TokenType.MONTH, "dec", Month.DECEMBER),
                new Token(TokenType.ORDINAL, "1st", 1))),
        Arguments.of(
            "ten of february",
            List.of(
                new Token(TokenType.NUMBER, "ten", 10),
                new Token(TokenType.KEYWORD, "of", "of"),
                new Token(TokenType.MONTH, "february", Month.FEBRUARY))));
  }
}
