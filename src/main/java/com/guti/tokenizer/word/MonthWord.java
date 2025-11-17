package com.guti.tokenizer.word;

import com.guti.tokenizer.Token;
import com.guti.tokenizer.constant.TokenType;

import java.time.Month;
import java.util.Map;

public class MonthWord implements Word {

  public final Map<String, Object> MONTHS =
      Map.ofEntries(
          Map.entry("january", Month.JANUARY),
          Map.entry("jan", Month.JANUARY),
          Map.entry("february", Month.FEBRUARY),
          Map.entry("feb", Month.FEBRUARY),
          Map.entry("march", Month.MARCH),
          Map.entry("mar", Month.MARCH),
          Map.entry("april", Month.APRIL),
          Map.entry("apr", Month.APRIL),
          Map.entry("may", Month.MAY),
          Map.entry("june", Month.JUNE),
          Map.entry("jun", Month.JUNE),
          Map.entry("july", Month.JULY),
          Map.entry("jul", Month.JULY),
          Map.entry("august", Month.AUGUST),
          Map.entry("aug", Month.AUGUST),
          Map.entry("september", Month.SEPTEMBER),
          Map.entry("sep", Month.SEPTEMBER),
          Map.entry("sept", Month.SEPTEMBER),
          Map.entry("october", Month.OCTOBER),
          Map.entry("oct", Month.OCTOBER),
          Map.entry("november", Month.NOVEMBER),
          Map.entry("nov", Month.NOVEMBER),
          Map.entry("december", Month.DECEMBER),
          Map.entry("dec", Month.DECEMBER));

  @Override
  public boolean contains(String word) {
    return MONTHS.containsKey(word);
  }

  @Override
  public Token tokenize(String word) {
    if (contains(word)) {
      return new Token(TokenType.MONTH, word, MONTHS.get(word));
    }
    return new Token(TokenType.UNKNOWN, word, word);
  }
}
