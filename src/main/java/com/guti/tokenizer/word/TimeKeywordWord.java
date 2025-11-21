package com.guti.tokenizer.word;

import com.guti.tokenizer.Token;
import com.guti.tokenizer.TokenType;

import java.time.LocalTime;
import java.util.Map;

public class TimeKeywordWord implements Word {

  public static final Map<String, LocalTime> TIME_KEYWORDS =
      Map.ofEntries(Map.entry("noon", LocalTime.NOON), Map.entry("midnight", LocalTime.MIDNIGHT));

  @Override
  public boolean match(String word) {
    return TIME_KEYWORDS.containsKey(word);
  }

  @Override
  public Token tokenize(String word) {
    if (match(word)) {
      return new Token(TokenType.TIME_KEYWORD, word, TIME_KEYWORDS.get(word));
    }
    return new Token(TokenType.UNKNOWN, word, word);
  }
}
