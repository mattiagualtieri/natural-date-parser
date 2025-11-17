package com.guti.tokenizer.word;

import com.guti.tokenizer.Special;
import com.guti.tokenizer.Token;
import com.guti.tokenizer.TokenType;

import java.util.Map;

public class SpecialTimeWord implements Word {

  public static final Map<String, Special> SPECIAL_TIME_WORDS =
      Map.ofEntries(
          Map.entry("now", Special.NOW),
          Map.entry("noon", Special.NOON),
          Map.entry("midday", Special.NOON),
          Map.entry("midnight", Special.MIDNIGHT));

  @Override
  public boolean contains(String word) {
    return SPECIAL_TIME_WORDS.containsKey(word);
  }

  @Override
  public Token tokenize(String word) {
    if (contains(word)) {
      return new Token(TokenType.SPECIAL_TIME, word, SPECIAL_TIME_WORDS.get(word));
    }
    return new Token(TokenType.UNKNOWN, word, word);
  }
}
