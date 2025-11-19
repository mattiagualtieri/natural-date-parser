package com.guti.parser;

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
        Arguments.of("Today at 13:00", reference, LocalDateTime.of(2026, 6, 15, 13, 0)),
        Arguments.of("Tomorrow at midnight", reference, LocalDateTime.of(2026, 6, 16, 0, 0)),
        Arguments.of("This monday at 13:00", reference, LocalDateTime.of(2026, 6, 15, 13, 0)),
        Arguments.of("Next monday at 13:00", reference, LocalDateTime.of(2026, 6, 22, 13, 0)),
        Arguments.of("Last monday at 13:00", reference, LocalDateTime.of(2026, 6, 8, 13, 0)),
        Arguments.of("Today at 1am", reference, LocalDateTime.of(2026, 6, 15, 1, 0)),
        Arguments.of("Today at 1 am", reference, LocalDateTime.of(2026, 6, 15, 1, 0)),
        Arguments.of("Today at 1 AM", reference, LocalDateTime.of(2026, 6, 15, 1, 0)),
        Arguments.of("Today at 1 a.m.", reference, LocalDateTime.of(2026, 6, 15, 1, 0)),
        Arguments.of("Today at 1pm", reference, LocalDateTime.of(2026, 6, 15, 13, 0)),
        Arguments.of("Today at 1 pm", reference, LocalDateTime.of(2026, 6, 15, 13, 0)),
        Arguments.of("Today at 1 PM", reference, LocalDateTime.of(2026, 6, 15, 13, 0)),
        Arguments.of("Today at 1 p.m.", reference, LocalDateTime.of(2026, 6, 15, 13, 0)),
        Arguments.of("Tomorrow at 12", reference, LocalDateTime.of(2026, 6, 16, 12, 0)),
        Arguments.of("17 June", reference, LocalDateTime.of(2026, 6, 17, 12, 0)),
        Arguments.of("17 June 2027", reference, LocalDateTime.of(2027, 6, 17, 12, 0)),
        Arguments.of("June 17", reference, LocalDateTime.of(2026, 6, 17, 12, 0)),
        Arguments.of("Jun 17", reference, LocalDateTime.of(2026, 6, 17, 12, 0)),
        Arguments.of("June 17 2027", reference, LocalDateTime.of(2027, 6, 17, 12, 0)),
        Arguments.of("June 1st", reference, LocalDateTime.of(2026, 6, 1, 12, 0)),
        Arguments.of("June 2nd", reference, LocalDateTime.of(2026, 6, 2, 12, 0)),
        Arguments.of("June 3rd", reference, LocalDateTime.of(2026, 6, 3, 12, 0)),
        Arguments.of("June 17th", reference, LocalDateTime.of(2026, 6, 17, 12, 0)),
        Arguments.of("1st of June", reference, LocalDateTime.of(2026, 6, 1, 12, 0)),
        Arguments.of("2nd of June", reference, LocalDateTime.of(2026, 6, 2, 12, 0)),
        Arguments.of("3rd of June", reference, LocalDateTime.of(2026, 6, 3, 12, 0)),
        Arguments.of("17th of June", reference, LocalDateTime.of(2026, 6, 17, 12, 0)),
        Arguments.of("17th of June 2027", reference, LocalDateTime.of(2027, 6, 17, 12, 0)),
        Arguments.of("17 June 2027", reference, LocalDateTime.of(2027, 6, 17, 12, 0)),
        Arguments.of("June 1st at 17:00", reference, LocalDateTime.of(2026, 6, 1, 17, 0)),
        Arguments.of("June 1 at 17:00", reference, LocalDateTime.of(2026, 6, 1, 17, 0)),
        Arguments.of("June 1 at 5 pm", reference, LocalDateTime.of(2026, 6, 1, 17, 0)),
        Arguments.of("1st of June at 5 pm", reference, LocalDateTime.of(2026, 6, 1, 17, 0)),
        Arguments.of("1st of June at noon", reference, LocalDateTime.of(2026, 6, 1, 12, 0)),
        Arguments.of("1st of June at midnight", reference, LocalDateTime.of(2026, 6, 1, 0, 0)),
        Arguments.of("In 2 minutes", reference, LocalDateTime.of(2026, 6, 15, 12, 2)),
        Arguments.of("In 2 hours", reference, LocalDateTime.of(2026, 6, 15, 14, 0)),
        Arguments.of("In 2 days", reference, LocalDateTime.of(2026, 6, 17, 12, 0)),
        Arguments.of("In 2 weeks", reference, LocalDateTime.of(2026, 6, 29, 12, 0)),
        Arguments.of("In 2 months", reference, LocalDateTime.of(2026, 8, 15, 12, 0)),
        Arguments.of("In 2 years", reference, LocalDateTime.of(2028, 6, 15, 12, 0)),
        Arguments.of("2 minutes ago", reference, LocalDateTime.of(2026, 6, 15, 11, 58)),
        Arguments.of("2 hours ago", reference, LocalDateTime.of(2026, 6, 15, 10, 0)),
        Arguments.of("2 days ago", reference, LocalDateTime.of(2026, 6, 13, 12, 0)),
        Arguments.of("2 weeks ago", reference, LocalDateTime.of(2026, 6, 1, 12, 0)),
        Arguments.of("2 months ago", reference, LocalDateTime.of(2026, 4, 15, 12, 0)),
        Arguments.of("2 years ago", reference, LocalDateTime.of(2024, 6, 15, 12, 0)),
        Arguments.of("12 thirty", reference, LocalDateTime.of(2026, 6, 15, 12, 30)),
        Arguments.of(
            "March 12th, 2023 at three thirty", reference, LocalDateTime.of(2023, 3, 12, 3, 30)),
        Arguments.of("3:30, March 12th, 2023", reference, LocalDateTime.of(2023, 3, 12, 3, 30)),
        Arguments.of("Sunday, March 12th, 2023", reference, LocalDateTime.of(2023, 3, 12, 12, 0)),
        Arguments.of(
            "3 thirty AM on Sunday, March 12th, 2023",
            reference,
            LocalDateTime.of(2023, 3, 12, 3, 30)),
        Arguments.of("Yesterday", reference, reference.minusDays(1)));
  }
}
