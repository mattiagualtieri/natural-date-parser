package com.guti.tokenizer.word;

import com.guti.tokenizer.Token;
import com.guti.tokenizer.TokenType;

import java.util.Map;

public class KeywordWord implements Word {

  public static final Map<String, Keyword> KEYWORDS =
      Map.ofEntries(
          Map.entry("at", Keyword.AT),
          Map.entry("of", Keyword.OF),
          Map.entry("in", Keyword.IN),
          Map.entry("ago", Keyword.AGO),
          Map.entry("from", Keyword.FROM),
          Map.entry("next", Keyword.NEXT),
          Map.entry("this", Keyword.THIS),
          Map.entry("last", Keyword.LAST));

  @Override
  public boolean contains(String word) {
    return KEYWORDS.containsKey(word);
  }

  @Override
  public Token tokenize(String word) {
    if (contains(word)) {
      return new Token(TokenType.KEYWORD, word, Keyword.valueOf(word.toUpperCase()));
    }
    return new Token(TokenType.UNKNOWN, word, word);
  }

  public enum Keyword {
    AT,
    OF,
    IN,
    AGO,
    FROM,
    NEXT,
    THIS,
    LAST
  }
}
