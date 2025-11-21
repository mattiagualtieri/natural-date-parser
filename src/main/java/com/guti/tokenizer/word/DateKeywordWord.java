package com.guti.tokenizer.word;

import com.guti.tokenizer.Token;
import com.guti.tokenizer.TokenType;
import java.util.Map;

public class DateKeywordWord implements Word {

  public static final Map<String, DateKeyword> DATE_KEYWORDS =
      Map.ofEntries(
          Map.entry("today", DateKeyword.TODAY),
          Map.entry("tomorrow", DateKeyword.TOMORROW),
          Map.entry("yesterday", DateKeyword.YESTERDAY),
          Map.entry("day_before_yesterday", DateKeyword.DAY_BEFORE_YESTERDAY),
          Map.entry("day_after_tomorrow", DateKeyword.DAY_AFTER_TOMORROW));

  @Override
  public boolean match(String word) {
    return DATE_KEYWORDS.containsKey(word);
  }

  @Override
  public Token tokenize(String word) {
    if (match(word)) {
      return new Token(TokenType.DATE_KEYWORD, word, DateKeyword.valueOf(word.toUpperCase()));
    }
    return new Token(TokenType.UNKNOWN, word, word);
  }

  public enum DateKeyword {
    TODAY,
    TOMORROW,
    YESTERDAY,
    DAY_AFTER_TOMORROW,
    DAY_BEFORE_YESTERDAY,
  }
}
