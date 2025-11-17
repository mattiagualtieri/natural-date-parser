package com.guti.tokenizer.word;

import com.guti.tokenizer.Token;
import com.guti.tokenizer.TokenType;

import java.time.temporal.ChronoUnit;
import java.util.Map;

public class UnitWord implements Word {

  public static final Map<String, ChronoUnit> UNITS =
      Map.ofEntries(
          Map.entry("second", ChronoUnit.SECONDS),
          Map.entry("seconds", ChronoUnit.SECONDS),
          Map.entry("sec", ChronoUnit.SECONDS),
          Map.entry("secs", ChronoUnit.SECONDS),
          Map.entry("minute", ChronoUnit.MINUTES),
          Map.entry("minutes", ChronoUnit.MINUTES),
          Map.entry("min", ChronoUnit.MINUTES),
          Map.entry("mins", ChronoUnit.MINUTES),
          Map.entry("hour", ChronoUnit.HOURS),
          Map.entry("hours", ChronoUnit.HOURS),
          Map.entry("hr", ChronoUnit.HOURS),
          Map.entry("hrs", ChronoUnit.HOURS),
          Map.entry("day", ChronoUnit.DAYS),
          Map.entry("days", ChronoUnit.DAYS),
          Map.entry("week", ChronoUnit.WEEKS),
          Map.entry("weeks", ChronoUnit.WEEKS),
          Map.entry("month", ChronoUnit.MONTHS),
          Map.entry("months", ChronoUnit.MONTHS),
          Map.entry("year", ChronoUnit.YEARS),
          Map.entry("years", ChronoUnit.YEARS),
          Map.entry("yr", ChronoUnit.YEARS),
          Map.entry("yrs", ChronoUnit.YEARS));

  @Override
  public boolean contains(String word) {
    return UNITS.containsKey(word);
  }

  @Override
  public Token tokenize(String word) {
    if (contains(word)) {
      return new Token(TokenType.UNIT, word, UNITS.get(word));
    }
    return new Token(TokenType.UNKNOWN, word, word);
  }
}
