package com.guti.parser.rule;

import static com.guti.tokenizer.constant.Keyword.*;
import static com.guti.tokenizer.constant.TokenType.*;

import com.guti.parser.ParseContext;
import com.guti.tokenizer.Token;
import com.guti.tokenizer.constant.TokenType;

import java.time.LocalTime;
import java.util.List;

public class TimeRule extends Rule {

  private final List<List<TokenType>> patterns =
      List.of(
          List.of(KEYWORD, TIME),
          List.of(KEYWORD, NUMBER),
          List.of(KEYWORD, TIME_KEYWORD),
          List.of(TIME),
          List.of(TIME_KEYWORD));

  @Override
  public List<List<TokenType>> getPatterns() {
    return patterns;
  }

  @Override
  public boolean apply(ParseContext ctx, List<Token> tokens, int pos) {
    List<Token> slice = tokens.subList(pos, pos + this.length());

    if (slice.get(0).type() == KEYWORD) {
      if (slice.get(0).value() != AT) {
        return false;
      }
      if (slice.get(1).type() == NUMBER) {
        LocalTime value = LocalTime.of((Integer) slice.get(1).value(), 0);
        ctx.setExplicitTime(value);
        return true;
      } else {
        LocalTime value = (LocalTime) slice.get(1).value();
        ctx.setExplicitTime(value);
        return true;
      }
    }

    LocalTime value = (LocalTime) slice.get(0).value();
    ctx.setExplicitTime(value);

    return true;
  }
}
