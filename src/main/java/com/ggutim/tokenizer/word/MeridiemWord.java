package com.ggutim.tokenizer.word;

import com.ggutim.tokenizer.Token;
import com.ggutim.tokenizer.TokenType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MeridiemWord implements Word {

  private static final Pattern MERIDIEM_REGEX = Pattern.compile("(a\\.?m?\\.?|p\\.?m?\\.?)?");

  @Override
  public boolean match(String word) {
    return MERIDIEM_REGEX.matcher(word).matches();
  }

  @Override
  public Token tokenize(String word) {
    Matcher matcher = MERIDIEM_REGEX.matcher(word);
    if (matcher.matches()) {
      String meridiem = matcher.group(1);

      if (meridiem.contains("a")) {
        return new Token(TokenType.MERIDIEM, word, Meridiem.AM);
      } else {
        return new Token(TokenType.MERIDIEM, word, Meridiem.PM);
      }
    }
    return new Token(TokenType.UNKNOWN, word, word);
  }

  public enum Meridiem {
    AM,
    PM
  }
}
