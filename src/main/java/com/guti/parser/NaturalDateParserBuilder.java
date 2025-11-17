package com.guti.parser;

import com.guti.normalizer.Normalizer;
import com.guti.tokenizer.Tokenizer;
import com.guti.tokenizer.word.WordTokenizer;

public class NaturalDateParserBuilder {

  private final NaturalDateParser parser;
  // TODO: improve
  private static final Tokenizer defaultTokenizer =
      new Tokenizer(new Normalizer(), new WordTokenizer());

  public NaturalDateParserBuilder() {
    this.parser = new NaturalDateParser(defaultTokenizer);
  }

  public NaturalDateParser build() {
    return parser;
  }
}
