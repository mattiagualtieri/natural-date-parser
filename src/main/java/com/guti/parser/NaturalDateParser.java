package com.guti.parser;

import com.guti.parser.pipeline.ParsePipeline;
import com.guti.tokenizer.Token;
import com.guti.tokenizer.Tokenizer;

import java.time.LocalDateTime;
import java.util.List;

public class NaturalDateParser {

  private final Tokenizer tokenizer;
  private final ParsePipeline pipeline;

  public NaturalDateParser(Tokenizer tokenizer, ParsePipeline pipeline) {
    this.tokenizer = tokenizer;
    this.pipeline = pipeline;
  }

  public LocalDateTime parse(String input) {
    return parse(input, LocalDateTime.now());
  }

  public LocalDateTime parse(String input, LocalDateTime reference) {
    List<Token> tokens = tokenizer.tokenize(input);
    return pipeline.parse(tokens, reference);
  }

  public static NaturalDateParserBuilder builder() {
    return new NaturalDateParserBuilder();
  }
}
