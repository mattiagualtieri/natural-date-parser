package com.guti.tokenizer.word;

import com.guti.tokenizer.Token;
import com.guti.tokenizer.constant.TokenType;
import java.util.regex.Pattern;

public class YearWord implements Word {

  // 1000 - 2999
  private static final Pattern YEAR_REGEX = Pattern.compile("\\b[12]\\d{3}\\b");

  @Override
  public boolean contains(String word) {
    return YEAR_REGEX.matcher(word).matches();
  }

  @Override
  public Token tokenize(String word) {
    if (YEAR_REGEX.matcher(word).matches()) {
      return new Token(TokenType.YEAR, word, Integer.parseInt(word));
    }
    return new Token(TokenType.UNKNOWN, word, word);
  }
}
