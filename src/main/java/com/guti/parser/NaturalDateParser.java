package com.guti.parser;

import com.guti.tokenizer.Token;
import com.guti.tokenizer.Tokenizer;

import java.time.LocalDateTime;
import java.util.List;

public class NaturalDateParser {

  private final Tokenizer tokenizer;

  public NaturalDateParser(Tokenizer tokenizer) {
    this.tokenizer = tokenizer;
  }

  public LocalDateTime parse(String input) {
    List<Token> tokens = tokenizer.tokenize(input);

    ParsePipeline pipeline = new ParsePipeline();
    return pipeline.parse(tokens);

    // Node node = grammar.parse(tokens);
    // Resolver resolver = new Resolver(LocalDateTime.now());
    // return resolver.resolve(node);
  }

  public static NaturalDateParserBuilder builder() {
    return new NaturalDateParserBuilder();
  }
}
