package com.guti.parser.rule;

import com.guti.parser.ParseContext;
import com.guti.tokenizer.Token;
import com.guti.tokenizer.constant.DateKeyword;
import com.guti.tokenizer.constant.TokenType;

import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.guti.tokenizer.constant.TokenType.DATE_KEYWORD;

public class RelativeDateKeywordRule extends Rule {

  private final List<List<TokenType>> patterns = List.of(List.of(DATE_KEYWORD));

  @Override
  public List<List<TokenType>> getPatterns() {
    return patterns;
  }

  @Override
  public boolean apply(ParseContext ctx, List<Token> tokens, int pos) {
    List<Token> slice = tokens.subList(pos, pos + this.length());

    DateKeyword value = (DateKeyword) slice.get(0).value();
    switch (value) {
      case YESTERDAY -> ctx.setRelative(-1, ChronoUnit.DAYS);
      case TOMORROW -> ctx.setRelative(1, ChronoUnit.DAYS);
      // case DAY_BEFORE_YESTERDAY -> ctx.setRelative(-2, ChronoUnit.DAYS);
      // case DAY_AFTER_TOMORROW -> ctx.setRelative(2, ChronoUnit.DAYS);
      default -> ctx.setRelative(0, ChronoUnit.DAYS);
    }

    return true;
  }
}
