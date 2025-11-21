package com.guti.parser.pipeline.rule.pattern;

import com.guti.parser.pipeline.ParseContext;
import com.guti.tokenizer.Token;
import com.guti.tokenizer.TokenType;

import java.util.Arrays;
import java.util.List;

public class Pattern {

  private final List<TokenType> pattern;
  private final String name;
  private final PatternResolver resolver;

  private Pattern(String name, PatternResolver resolver, List<TokenType> pattern) {
    this.name = name;
    this.pattern = pattern;
    this.resolver = resolver;
  }

  public static Pattern of(String name, PatternResolver resolver, TokenType... parts) {
    return new Pattern(name, resolver, Arrays.asList(parts));
  }

  public String getName() {
    return name;
  }

  public int length() {
    return pattern.size();
  }

  public boolean matches(List<Token> tokens) {
    if (pattern.size() > tokens.size()) return false;
    for (int i = 0; i < pattern.size(); i++) {
      if (tokens.get(i).type() != pattern.get(i)) {
        return false;
      }
    }
    return true;
  }

  public boolean resolve(List<Token> tokens, ParseContext ctx) {
    return resolver.resolve(tokens, ctx);
  }

  @Override
  public String toString() {
    return name;
  }
}
