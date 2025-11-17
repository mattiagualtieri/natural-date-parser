package com.guti.tokenizer.word;

import com.guti.tokenizer.Token;
import com.guti.tokenizer.TokenType;

import java.util.Map;

public class MeridiemWord implements Word {

  public static final Map<String, String> MERIDIEMS =
      Map.ofEntries(Map.entry("am", "am"), Map.entry("pm", "pm"));

  @Override
  public boolean contains(String word) {
    return MERIDIEMS.containsKey(word);
  }

  @Override
  public Token tokenize(String word) {
    if (contains(word)) {
      return new Token(TokenType.MERIDIEM, word, MERIDIEMS.get(word));
    }
    return new Token(TokenType.UNKNOWN, word, word);
  }
}
