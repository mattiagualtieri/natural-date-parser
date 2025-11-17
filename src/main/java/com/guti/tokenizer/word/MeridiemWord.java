package com.guti.tokenizer.word;

import com.guti.tokenizer.Token;
import com.guti.tokenizer.constant.MeridiemKeyword;
import com.guti.tokenizer.constant.TokenType;
import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MeridiemWord implements Word {

  private static final Pattern MERIDIEM_REGEX = Pattern.compile("(a\\.?m?\\.?|p\\.?m?\\.?)?");

  @Override
  public boolean contains(String word) {
    return MERIDIEM_REGEX.matcher(word).matches();
  }

  @Override
  public Token tokenize(String word) {
    Matcher matcher = MERIDIEM_REGEX.matcher(word);
    if (matcher.matches()) {
      String meridiem = matcher.group(1);

      if (meridiem.contains("a")) {
        return new Token(TokenType.MERIDIEM, word, MeridiemKeyword.AM);
      } else {
        return new Token(TokenType.MERIDIEM, word, MeridiemKeyword.PM);
      }
    }
    return new Token(TokenType.UNKNOWN, word, word);
  }
}
