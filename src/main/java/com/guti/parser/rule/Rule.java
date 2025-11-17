package com.guti.parser.rule;

import com.guti.parser.ParseContext;
import com.guti.tokenizer.Token;
import com.guti.tokenizer.TokenType;

import java.util.List;

public abstract class Rule {

  private int matchedLength;

  public abstract List<List<TokenType>> getPatterns();

  public abstract void apply(ParseContext ctx, List<Token> tokens, int pos);

  public boolean matches(List<Token> tokens, int pos) {
    for (List<TokenType> pattern : getPatterns()) {
      if (matchesPattern(tokens, pos, pattern)) {
        matchedLength = pattern.size();
        return true;
      }
    }
    return false;
  }

  public int length() {
    return matchedLength;
  }

  private boolean matchesPattern(List<Token> tokens, int pos, List<TokenType> pattern) {
    if (pos + pattern.size() > tokens.size()) return false;
    for (int i = 0; i < pattern.size(); i++) {
      if (tokens.get(pos + i).getType() != pattern.get(i)) {
        return false;
      }
    }
    return true;
  }
}
