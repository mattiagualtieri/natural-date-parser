package com.guti.parser.rule;

import com.guti.parser.ParseContext;
import com.guti.tokenizer.Token;
import com.guti.tokenizer.constant.TokenType;

import java.util.List;

public abstract class Rule {

    // x AbsoluteDateRule
    // x RelativeDayKeywordRule
    // x RelativeQuantityRule
    // WeekdayRule
    // TimeRule

  private int matchedLength;

  public abstract List<List<TokenType>> getPatterns();

  public abstract boolean apply(ParseContext ctx, List<Token> tokens, int pos);

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
      if (tokens.get(pos + i).type() != pattern.get(i)) {
        return false;
      }
    }
    return true;
  }
}
