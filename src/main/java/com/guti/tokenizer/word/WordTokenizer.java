package com.guti.tokenizer.word;

import com.guti.tokenizer.Token;
import com.guti.tokenizer.constant.TokenType;

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
          new NumberWord(),
          new TimeWord(),
          new OrdinalWord());

  public Token tokenize(String word) {
    for (Word strategy : strategies) {
      if (strategy.contains(word)) {
        return strategy.tokenize(word);
      }
    }
    return new Token(TokenType.UNKNOWN, word, word);
  }
}
