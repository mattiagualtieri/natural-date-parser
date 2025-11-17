package com.guti.parser.pipeline.rule.rules;

import static com.guti.tokenizer.constant.Keyword.*;
import static com.guti.tokenizer.constant.TokenType.*;

import com.guti.parser.pipeline.ParseContext;
import com.guti.parser.pipeline.rule.Rule;
import com.guti.tokenizer.Token;
import com.guti.tokenizer.constant.MeridiemKeyword;
import com.guti.tokenizer.constant.TokenType;

import java.time.LocalTime;
import java.time.temporal.Temporal;
import java.util.List;

public class TimeRule extends Rule {

  private final List<List<TokenType>> patterns =
      List.of(
          List.of(KEYWORD, TIME, MERIDIEM),
          List.of(KEYWORD, NUMBER, MERIDIEM),
          List.of(KEYWORD, TIME),
          List.of(KEYWORD, NUMBER),
          List.of(KEYWORD, TIME_KEYWORD),
          List.of(TIME, MERIDIEM),
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
        Integer number = (Integer) slice.get(1).value();
        if (slice.size() == 3 && number < 12 && slice.get(2).value() == MeridiemKeyword.PM) {
          number += 12;
        }
        LocalTime value = LocalTime.of(number, 0);
        ctx.setExplicitTime(value);
        return true;
      } else {
        LocalTime value = (LocalTime) slice.get(1).value();
        if (slice.size() == 3
            && value.getHour() < 12
            && slice.get(2).value() == MeridiemKeyword.PM) {
          value = value.plusHours(12);
        }
        ctx.setExplicitTime(value);
        return true;
      }
    }

    LocalTime value = (LocalTime) slice.get(0).value();
    ctx.setExplicitTime(value);

    return true;
  }
}
