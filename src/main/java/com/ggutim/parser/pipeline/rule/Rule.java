package com.ggutim.parser.pipeline.rule;

import com.ggutim.parser.pipeline.ParseContext;
import com.ggutim.parser.pipeline.rule.pattern.Pattern;
import com.ggutim.tokenizer.Token;

import java.util.List;

public abstract class Rule {

  private int matchedLength;

  private int matchedPatternIndex;

  public abstract List<Pattern> getPatterns();

  public boolean apply(ParseContext ctx, List<Token> tokens, int pos) {
    List<Token> slice = tokens.subList(pos, pos + length());
    Pattern pattern = getPatterns().get(getMatchedPatternIndex());
    return pattern.resolve(slice, ctx);
  }

  public boolean matches(List<Token> tokens, int pos) {
    List<Token> slice = tokens.subList(pos, tokens.size());
    for (int i = 0; i < getPatterns().size(); i++) {
      Pattern pattern = getPatterns().get(i);
      if (pattern.matches(slice)) {
        matchedLength = pattern.length();
        matchedPatternIndex = i;
        return true;
      }
    }
    return false;
  }

  public int length() {
    return matchedLength;
  }

  public int getMatchedPatternIndex() {
    return matchedPatternIndex;
  }
}
