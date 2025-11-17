package com.guti.tokenizer.word;

import com.guti.tokenizer.Token;
import com.guti.tokenizer.constant.Keyword;
import com.guti.tokenizer.constant.TokenType;

import java.util.Map;

import static com.guti.tokenizer.constant.Keyword.*;

public class KeywordWord implements Word {

  public static final Map<String, Keyword> KEYWORDS =
      Map.ofEntries(
          Map.entry("at", AT),
          Map.entry("of", OF),
          Map.entry("in", IN),
          Map.entry("ago", AGO),
          Map.entry("from", FROM),
          Map.entry("next", NEXT),
          Map.entry("this", THIS),
          Map.entry("last", LAST));

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
}
