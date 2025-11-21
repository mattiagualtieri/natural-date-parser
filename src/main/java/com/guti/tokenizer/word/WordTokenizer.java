package com.guti.tokenizer.word;

import com.guti.tokenizer.Token;
import com.guti.tokenizer.TokenType;

import java.util.List;

public class WordTokenizer {

  private final List<Word> strategies =
      List.of(
          new MonthWord(),
          new WeekdayWord(),
          new KeywordWord(),
          new DateKeywordWord(),
          new TimeKeywordWord(),
          new UnitWord(),
          new YearWord(),
          new NumberWord(),
          new TimeWord(),
          new MeridiemWord());

  public Token tokenize(String word) {
    for (Word strategy : strategies) {
      if (strategy.match(word)) {
        return strategy.tokenize(word);
      }
    }
    return new Token(TokenType.UNKNOWN, word, word);
  }
}
