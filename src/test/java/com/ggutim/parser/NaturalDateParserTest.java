package com.ggutim.parser;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NaturalDateParserTest {

  private final NaturalDateParser parser = NaturalDateParser.builder().build();

  @ParameterizedTest
  @MethodSource("provideInputsForShouldParseDateCorrectly")
  void shouldParseDateCorrectly(String input, LocalDateTime reference, LocalDateTime expected) {
    LocalDateTime parsed = parser.parse(input, reference);
    assertEquals(expected, parsed);
  }

  private static Stream<Arguments> provideInputsForShouldParseDateCorrectly() {
    LocalDateTime reference = LocalDateTime.of(2026, 6, 15, 12, 0);
    return Stream.of(
        Arguments.of("Today", reference, LocalDateTime.of(2026, 6, 15, 12, 0)),
        Arguments.of("Tomorrow", reference, LocalDateTime.of(2026, 6, 16, 12, 0)),
        Arguments.of("Yesterday", reference, LocalDateTime.of(2026, 6, 14, 12, 0)),
        Arguments.of("Day after tomorrow", reference, LocalDateTime.of(2026, 6, 17, 12, 0)),
        Arguments.of("Day before yesterday", reference, LocalDateTime.of(2026, 6, 13, 12, 0)),
        Arguments.of("Tomorrow at 11 a.m.", reference, LocalDateTime.of(2026, 6, 16, 11, 0)),
        Arguments.of("Today at 1 pm", reference, LocalDateTime.of(2026, 6, 15, 13, 0)),
        Arguments.of("Today at 1 a.m.", reference, LocalDateTime.of(2026, 6, 15, 1, 0)),
        Arguments.of("Today at 13:45", reference, LocalDateTime.of(2026, 6, 15, 13, 45)),
        Arguments.of(
            "Day after tomorrow at 06:30", reference, LocalDateTime.of(2026, 6, 17, 6, 30)),
        Arguments.of("In 45 minutes", reference, LocalDateTime.of(2026, 6, 15, 12, 45)),
        Arguments.of("In 2 hours", reference, LocalDateTime.of(2026, 6, 15, 14, 0)),
        Arguments.of("at noon in 2 hours", reference, LocalDateTime.of(2026, 6, 15, 14, 0)),
        Arguments.of("In 2 days", reference, LocalDateTime.of(2026, 6, 17, 12, 0)),
        Arguments.of("In 3 weeks", reference, LocalDateTime.of(2026, 7, 6, 12, 0)),
        Arguments.of("In 2 months", reference, LocalDateTime.of(2026, 8, 15, 12, 0)),
        Arguments.of("In 2 years", reference, LocalDateTime.of(2028, 6, 15, 12, 0)),
        Arguments.of("2 weeks ago", reference, LocalDateTime.of(2026, 6, 1, 12, 0)),
        Arguments.of("3 days ago at 9pm", reference, LocalDateTime.of(2026, 6, 12, 21, 0)),
        Arguments.of("March 5 2027 at 6 pm", reference, LocalDateTime.of(2027, 3, 5, 18, 0)),
        Arguments.of("5 March 2027", reference, LocalDateTime.of(2027, 3, 5, 12, 0)),
        Arguments.of("March 5", reference, LocalDateTime.of(2026, 3, 5, 12, 0)),
        Arguments.of("5 of March", reference, LocalDateTime.of(2026, 3, 5, 12, 0)),
        Arguments.of("June 1st at 17:00", reference, LocalDateTime.of(2026, 6, 1, 17, 0)),
        Arguments.of("1st of June at midnight", reference, LocalDateTime.of(2026, 6, 1, 0, 0)),
        Arguments.of("Sunday, March 3rd 2024", reference, LocalDateTime.of(2024, 3, 3, 12, 0)),
        Arguments.of("next Sunday at noon", reference, LocalDateTime.of(2026, 6, 21, 12, 0)),
        Arguments.of("last Friday at midnight", reference, LocalDateTime.of(2026, 6, 12, 0, 0)),
        Arguments.of("this Wednesday at 7am", reference, LocalDateTime.of(2026, 6, 17, 7, 0)),
        Arguments.of("Monday at 18:30", reference, LocalDateTime.of(2026, 6, 15, 18, 30)),
        Arguments.of("in 1 week next monday at 9", reference, LocalDateTime.of(2026, 6, 29, 9, 0)),
        Arguments.of("in 2 days at midnight", reference, LocalDateTime.of(2026, 6, 17, 0, 0)),
        Arguments.of("12:30", reference, LocalDateTime.of(2026, 6, 15, 12, 30)),
        Arguments.of("17 June 2027", reference, LocalDateTime.of(2027, 6, 17, 12, 0)),
        Arguments.of("June 17", reference, LocalDateTime.of(2026, 6, 17, 12, 0)),
        Arguments.of("June 17th at noon", reference, LocalDateTime.of(2026, 6, 17, 12, 0)),
        Arguments.of("3:30, March 12th, 2023", reference, LocalDateTime.of(2023, 3, 12, 3, 30)),
        Arguments.of(
            "3 thirty AM on Sunday, March 12th, 2023",
            reference,
            LocalDateTime.of(2023, 3, 12, 3, 30)));
  }
}
