package com.guti.parser.pipeline.rule.rules;

import com.guti.parser.pipeline.ParseContext;
import com.guti.parser.pipeline.rule.Rule;
import com.guti.tokenizer.constant.Keyword;
import com.guti.tokenizer.Token;
import com.guti.tokenizer.constant.TokenType;

import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.guti.tokenizer.constant.TokenType.*;

public class RelativeQuantityRule extends Rule {

  private final List<List<TokenType>> patterns =
      List.of(List.of(KEYWORD, NUMBER, UNIT), List.of(NUMBER, UNIT, KEYWORD));

  @Override
  public List<List<TokenType>> getPatterns() {
    return patterns;
  }

  @Override
  public boolean apply(ParseContext ctx, List<Token> tokens, int pos) {
    List<Token> slice = tokens.subList(pos, pos + this.length());

    if (slice.get(0).type() == KEYWORD) {
      if (slice.get(0).value() == Keyword.IN) {
        Integer value = (Integer) slice.get(1).value();
        ChronoUnit unit = (ChronoUnit) slice.get(2).value();
        ctx.setRelative(value, unit);
        return true;
      }
    } else {
      if (slice.get(2).value() == Keyword.AGO) {
        Integer value = (Integer) slice.get(0).value();
        ChronoUnit unit = (ChronoUnit) slice.get(1).value();
        ctx.setRelative(-value, unit);
        return true;
      }
    }

    return false;
  }
}
