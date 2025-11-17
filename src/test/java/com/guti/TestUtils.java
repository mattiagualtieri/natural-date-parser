package com.guti;

import com.guti.normalizer.Normalizer;
import com.guti.tokenizer.Token;
import com.guti.tokenizer.word.*;

import java.util.List;

public class TestUtils {

  private static final List<Word> strategies =
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

  private static final Normalizer normalizer = new Normalizer();

  public static Token tokenOf(String word) {
    String normalized = normalizer.normalize(word);
    for (Word strategy : strategies) {
      if (strategy.contains(normalized)) {
        return strategy.tokenize(normalized);
      }
    }
    throw new IllegalArgumentException(word + " is not a valid token");
  }
}
