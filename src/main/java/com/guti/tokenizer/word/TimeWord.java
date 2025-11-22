package com.guti.tokenizer.word;

import com.guti.tokenizer.Token;
import com.guti.tokenizer.TokenType;

import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeWord implements Word {

  private static final Pattern TIME_REGEX =
      Pattern.compile("(\\d{1,2})(?::(\\d{2}))?\\s*(a\\.?m?\\.?|p\\.?m?\\.?)?");

  @Override
  public boolean match(String word) {
    return TIME_REGEX.matcher(word).matches();
  }

  @Override
  public Token tokenize(String word) {
    Matcher timeMatch = TIME_REGEX.matcher(word);
    if (timeMatch.matches()) {
      int hour = Integer.parseInt(timeMatch.group(1));
      int minute = timeMatch.group(2) != null ? Integer.parseInt(timeMatch.group(2)) : 0;
      String meridiem = timeMatch.group(3);

      if (minute < 0 || minute > 59) return new Token(TokenType.UNKNOWN, word, word);

      if (meridiem != null) {
        if (hour < 1 || hour > 12) {
          return new Token(TokenType.UNKNOWN, word, word);
        }
        if (meridiem.equals("pm") && hour < 12) hour += 12;
        if (meridiem.equals("am") && hour == 12) hour = 0;
      } else {
        if (hour < 0 || hour > 23) return new Token(TokenType.UNKNOWN, word, word);
      }

      return new Token(TokenType.TIME, word, LocalTime.of(hour, minute));
    }
    return new Token(TokenType.UNKNOWN, word, word);
  }
}
