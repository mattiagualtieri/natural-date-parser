package com.guti.tokenizer.word;

import com.guti.tokenizer.Token;
import com.guti.tokenizer.constant.TokenType;

import java.util.regex.Pattern;

public class NumberWord implements Word {

  private static final Pattern NUMBER_REGEX = Pattern.compile("\\d+");

  @Override
  public boolean contains(String word) {
    return NUMBER_REGEX.matcher(word).matches();
  }

  @Override
  public Token tokenize(String word) {
    if (NUMBER_REGEX.matcher(word).matches()) {
      return new Token(TokenType.NUMBER, word, Integer.parseInt(word));
    }
    return new Token(TokenType.UNKNOWN, word, word);
  }
}
