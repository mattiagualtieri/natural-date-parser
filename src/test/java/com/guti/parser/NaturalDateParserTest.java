package com.guti.parser;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class NaturalDateParserTest {

  @Test
  void test() {
    NaturalDateParser parser = NaturalDateParser.builder().build();
    LocalDateTime date = parser.parse("Next monday at 18");
    System.out.println(date);
  }
}
