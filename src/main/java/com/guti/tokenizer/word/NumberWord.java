package com.guti.tokenizer.word;

import com.guti.tokenizer.Token;
import com.guti.tokenizer.TokenType;

import java.util.Map;
import java.util.regex.Pattern;

public class NumberWord implements Word {

  private static final Pattern NUMBER_REGEX = Pattern.compile("\\d+");

  public static final Map<String, Integer> NUMBER_WORDS =
      Map.ofEntries(
          // 1–20
          Map.entry("zero", 0),
          Map.entry("one", 1),
          Map.entry("a", 1),
          Map.entry("an", 1),
          Map.entry("two", 2),
          Map.entry("three", 3),
          Map.entry("four", 4),
          Map.entry("five", 5),
          Map.entry("six", 6),
          Map.entry("seven", 7),
          Map.entry("eight", 8),
          Map.entry("nine", 9),
          Map.entry("ten", 10),
          Map.entry("eleven", 11),
          Map.entry("twelve", 12),
          Map.entry("thirteen", 13),
          Map.entry("fourteen", 14),
          Map.entry("fifteen", 15),
          Map.entry("sixteen", 16),
          Map.entry("seventeen", 17),
          Map.entry("eighteen", 18),
          Map.entry("nineteen", 19),
          Map.entry("twenty", 20),

          // Tens
          Map.entry("thirty", 30),
          Map.entry("forty", 40),
          Map.entry("fifty", 50),
          Map.entry("sixty", 60),
          Map.entry("seventy", 70),
          Map.entry("eighty", 80),
          Map.entry("ninety", 90)

          // (compound words like “twenty-one” are handled in the tokenizer)
          );

  @Override
  public boolean contains(String word) {
    return NUMBER_WORDS.containsKey(word) || NUMBER_REGEX.matcher(word).matches();
  }

  @Override
  public Token tokenize(String word) {
    if (NUMBER_WORDS.containsKey(word)) {
      return new Token(TokenType.NUMBER, word, NUMBER_WORDS.get(word));
    }
    if (NUMBER_REGEX.matcher(word).matches()) {
      return new Token(TokenType.NUMBER, word, Integer.parseInt(word));
    }
    return new Token(TokenType.UNKNOWN, word, word);
  }
}
