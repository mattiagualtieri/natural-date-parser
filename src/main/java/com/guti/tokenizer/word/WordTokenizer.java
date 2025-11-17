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
          new SpecialDayWord(),
          new SpecialTimeWord(),
          new UnitWord(),
          new MeridiemWord(),
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
