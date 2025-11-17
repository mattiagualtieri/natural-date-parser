package com.guti.tokenizer.word;

import com.guti.tokenizer.Token;
import com.guti.tokenizer.TokenType;

import java.time.DayOfWeek;
import java.util.Map;

public class WeekdayWord implements Word {

  public static final Map<String, DayOfWeek> WEEKDAYS =
      Map.ofEntries(
          Map.entry("monday", DayOfWeek.MONDAY),
          Map.entry("mon", DayOfWeek.MONDAY),
          Map.entry("tuesday", DayOfWeek.TUESDAY),
          Map.entry("tue", DayOfWeek.TUESDAY),
          Map.entry("tues", DayOfWeek.TUESDAY),
          Map.entry("wednesday", DayOfWeek.WEDNESDAY),
          Map.entry("wed", DayOfWeek.WEDNESDAY),
          Map.entry("thursday", DayOfWeek.THURSDAY),
          Map.entry("thu", DayOfWeek.THURSDAY),
          Map.entry("thur", DayOfWeek.THURSDAY),
          Map.entry("thurs", DayOfWeek.THURSDAY),
          Map.entry("friday", DayOfWeek.FRIDAY),
          Map.entry("fri", DayOfWeek.FRIDAY),
          Map.entry("saturday", DayOfWeek.SATURDAY),
          Map.entry("sat", DayOfWeek.SATURDAY),
          Map.entry("sunday", DayOfWeek.SUNDAY),
          Map.entry("sun", DayOfWeek.SUNDAY));

  @Override
  public boolean contains(String word) {
    return WEEKDAYS.containsKey(word);
  }

  @Override
  public Token tokenize(String word) {
    if (contains(word)) {
      return new Token(TokenType.WEEKDAY, word, WEEKDAYS.get(word));
    }
    return new Token(TokenType.UNKNOWN, word, word);
  }
}
