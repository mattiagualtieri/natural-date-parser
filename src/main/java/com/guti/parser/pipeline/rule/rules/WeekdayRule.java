package com.guti.parser.pipeline.rule.rules;

import com.guti.parser.pipeline.ParseContext;
import com.guti.parser.pipeline.rule.Rule;
import com.guti.tokenizer.Token;
import com.guti.tokenizer.constant.Keyword;
import com.guti.tokenizer.constant.TokenType;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

import static com.guti.tokenizer.constant.Keyword.*;
import static com.guti.tokenizer.constant.TokenType.*;

public class WeekdayRule extends Rule {

  private final List<List<TokenType>> patterns =
      List.of(List.of(WEEKDAY), List.of(KEYWORD, WEEKDAY));

  @Override
  public List<List<TokenType>> getPatterns() {
    return patterns;
  }

  @Override
  public boolean apply(ParseContext ctx, List<Token> tokens, int pos) {
    List<Token> slice = tokens.subList(pos, pos + this.length());

    if (slice.get(0).type() == WEEKDAY) {
      DayOfWeek value = (DayOfWeek) slice.get(0).value();
      ctx.setWeekday(value);
      return true;
    } else {
      Keyword modifier = (Keyword) slice.get(0).value();
      if (Set.of(THIS, NEXT, LAST).contains(modifier)) {
        ctx.setWeekdayModifier(modifier);
        DayOfWeek value = (DayOfWeek) slice.get(1).value();
        ctx.setWeekday(value);
        return true;
      }
    }

    return false;
  }
}
