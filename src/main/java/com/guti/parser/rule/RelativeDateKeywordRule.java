package com.guti.parser.rule;

import com.guti.parser.ParseContext;
import com.guti.tokenizer.Special;
import com.guti.tokenizer.Token;
import com.guti.tokenizer.TokenType;

import java.util.List;

import static com.guti.tokenizer.TokenType.*;

public class RelativeDateKeywordRule extends Rule {

  private final List<List<TokenType>> patterns = List.of(List.of(SPECIAL_DAY));

  @Override
  public List<List<TokenType>> getPatterns() {
    return patterns;
  }

  @Override
  public void apply(ParseContext ctx, List<Token> tokens, int pos) {
    List<Token> slice = tokens.subList(pos, pos + this.length());

    Special value = (Special) slice.get(0).getValue();
    switch (value) {
      case YESTERDAY -> ctx.setRelativeDays(-1);
      case TOMORROW -> ctx.setRelativeDays(1);
      case DAY_BEFORE_YESTERDAY -> ctx.setRelativeDays(-2);
      case DAY_AFTER_TOMORROW -> ctx.setRelativeDays(2);
      default -> ctx.setRelativeDays(0);
    }
  }
}
