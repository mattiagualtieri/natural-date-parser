package com.guti.tokenizer.word;

import com.guti.tokenizer.Token;
import com.guti.tokenizer.constant.TokenType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrdinalWord implements Word {

  private static final Pattern ORDINAL_REGEX = Pattern.compile("(\\d+)(st|nd|rd|th)");

  @Override
  public boolean contains(String word) {
    return ORDINAL_REGEX.matcher(word).matches();
  }

  @Override
  public Token tokenize(String word) {
    Matcher matcher = ORDINAL_REGEX.matcher(word);
    if (matcher.matches()) {
      int value = Integer.parseInt(matcher.group(1));
      return new Token(TokenType.ORDINAL, word, value);
    }
    return new Token(TokenType.UNKNOWN, word, word);
  }
}
