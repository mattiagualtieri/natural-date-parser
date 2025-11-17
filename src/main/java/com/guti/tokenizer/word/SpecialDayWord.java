package com.guti.tokenizer.word;

import com.guti.tokenizer.Special;
import com.guti.tokenizer.Token;
import com.guti.tokenizer.TokenType;
import java.util.Map;

public class SpecialDayWord implements Word {

  public static final Map<String, Special> SPECIAL_DAY_WORDS =
      Map.ofEntries(
          Map.entry("today", Special.TODAY),
          Map.entry("tomorrow", Special.TOMORROW),
          Map.entry("tmrw", Special.TOMORROW),
          Map.entry("tmr", Special.TOMORROW),
          Map.entry("yesterday", Special.YESTERDAY),
          Map.entry("day_after_tomorrow", Special.DAY_AFTER_TOMORROW),
          Map.entry("day_before_yesterday", Special.DAY_BEFORE_YESTERDAY));

  @Override
  public boolean contains(String word) {
    return SPECIAL_DAY_WORDS.containsKey(word);
  }

  @Override
  public Token tokenize(String word) {
    if (contains(word)) {
      return new Token(TokenType.SPECIAL_DAY, word, SPECIAL_DAY_WORDS.get(word));
    }
    return new Token(TokenType.UNKNOWN, word, word);
  }
}
