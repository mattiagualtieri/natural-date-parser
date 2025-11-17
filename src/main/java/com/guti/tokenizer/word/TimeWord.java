package com.guti.tokenizer.word;

import com.guti.tokenizer.Token;
import com.guti.tokenizer.constant.TokenType;

import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeWord implements Word {

  private static final Pattern TIME_REGEX = Pattern.compile("(\\d{1,2})(?::(\\d{2}))?\\s*(am|pm)?");

  @Override
  public boolean contains(String word) {
    return TIME_REGEX.matcher(word).matches();
  }

  @Override
  public Token tokenize(String word) {
    Matcher timeMatch = TIME_REGEX.matcher(word);
    if (timeMatch.matches()) {
      int hour = Integer.parseInt(timeMatch.group(1));
      int minute = timeMatch.group(2) != null ? Integer.parseInt(timeMatch.group(2)) : 0;
      String meridiem = timeMatch.group(3);

      if (meridiem != null) {
        if (meridiem.equals("pm") && hour < 12) hour += 12;
        if (meridiem.equals("am") && hour == 12) hour = 0;
      }

      return new Token(TokenType.TIME, word, LocalTime.of(hour, minute));
    }
    return new Token(TokenType.UNKNOWN, word, word);
  }
}
