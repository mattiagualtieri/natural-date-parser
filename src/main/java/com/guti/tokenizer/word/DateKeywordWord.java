package com.guti.tokenizer.word;

import com.guti.tokenizer.Token;
import com.guti.tokenizer.constant.DateKeyword;
import com.guti.tokenizer.constant.TokenType;
import java.util.Map;

import static com.guti.tokenizer.constant.DateKeyword.*;

public class DateKeywordWord implements Word {

  public static final Map<String, DateKeyword> DATE_KEYWORDS =
      Map.ofEntries(
          Map.entry("today", TODAY),
          Map.entry("tomorrow", TOMORROW),
          Map.entry("yesterday", YESTERDAY),
          Map.entry("day_before_yesterday", DAY_BEFORE_YESTERDAY),
          Map.entry("day_after_tomorrow", DAY_AFTER_TOMORROW));

  @Override
  public boolean contains(String word) {
    return DATE_KEYWORDS.containsKey(word);
  }

  @Override
  public Token tokenize(String word) {
    if (contains(word)) {
      return new Token(TokenType.DATE_KEYWORD, word, DateKeyword.valueOf(word.toUpperCase()));
    }
    return new Token(TokenType.UNKNOWN, word, word);
  }
}
