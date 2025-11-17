package com.guti.parser;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class NaturalDateParserTest {

  @Test
  void test() {
    NaturalDateParser parser = NaturalDateParser.builder().build();
    LocalDateTime date = parser.parse("30 APR");
    System.out.println(date);
  }
}
