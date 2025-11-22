package com.ggutim.parser;

import com.ggutim.normalizer.pipeline.DefaultNormalizationPipeline;
import com.ggutim.parser.pipeline.ParsePipeline;
import com.ggutim.parser.pipeline.rule.RuleBasedParsePipeline;
import com.ggutim.tokenizer.Tokenizer;
import com.ggutim.tokenizer.word.WordTokenizer;

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
