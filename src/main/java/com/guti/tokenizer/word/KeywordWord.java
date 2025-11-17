package com.guti.tokenizer.word;

import com.guti.tokenizer.Token;
import com.guti.tokenizer.TokenType;

import java.util.Set;

public class KeywordWord implements Word {

  public static final Set<String> KEYWORDS =
      Set.of(
          "next", "last", "this", "in", "ago", "at", "on", "after", "before", "from", "to", "until",
          "by", "around", "about", "past", "of");

  @Override
  public boolean contains(String word) {
    return KEYWORDS.contains(word);
  }

  @Override
  public Token tokenize(String word) {
    if (contains(word)) {
      return new Token(TokenType.KEYWORD, word, word);
    }
    return new Token(TokenType.UNKNOWN, word, word);
  }
}
