package com.guti.tokenizer.word;

import com.guti.tokenizer.Token;
import com.guti.tokenizer.TokenType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberWord implements Word {

  private static final Pattern NUMBER_REGEX = Pattern.compile("\\d+");
  private static final Pattern ORDINAL_REGEX = Pattern.compile("(\\d+)(st|nd|rd|th)");

  @Override
  public boolean contains(String word) {
    return NUMBER_REGEX.matcher(word).matches() || ORDINAL_REGEX.matcher(word).matches();
  }

  @Override
  public Token tokenize(String word) {
    Matcher numberMatcher = NUMBER_REGEX.matcher(word);
    if (numberMatcher.matches()) {
      return new Token(TokenType.NUMBER, word, Integer.parseInt(word));
    }
    Matcher ordinalMatcher = ORDINAL_REGEX.matcher(word);
    if (ordinalMatcher.matches()) {
      return new Token(TokenType.NUMBER, word, Integer.parseInt(ordinalMatcher.group(1)));
    }
    return new Token(TokenType.UNKNOWN, word, word);
  }
}
