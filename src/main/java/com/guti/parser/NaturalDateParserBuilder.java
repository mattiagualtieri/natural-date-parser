package com.guti.parser;

import com.guti.normalizer.pipeline.DefaultNormalizationPipeline;
import com.guti.parser.pipeline.ParsePipeline;
import com.guti.parser.pipeline.rule.RuleBasedParsePipeline;
import com.guti.tokenizer.Tokenizer;
import com.guti.tokenizer.word.WordTokenizer;

public class NaturalDateParserBuilder {

  private final NaturalDateParser parser;
  private static final Tokenizer defaultTokenizer =
      new Tokenizer(new DefaultNormalizationPipeline(), new WordTokenizer());
  private static final ParsePipeline defaultParsePipeline = new RuleBasedParsePipeline();

  public NaturalDateParserBuilder() {
    this.parser = new NaturalDateParser(defaultTokenizer, defaultParsePipeline);
  }

  public NaturalDateParser build() {
    return parser;
  }
}
