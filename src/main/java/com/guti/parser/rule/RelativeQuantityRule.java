package com.guti.parser.rule;

import com.guti.parser.ParseContext;
import com.guti.tokenizer.Token;
import com.guti.tokenizer.TokenType;

import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.util.List;

import static com.guti.tokenizer.TokenType.*;

public class RelativeQuantityRule extends Rule {

  private final List<List<TokenType>> patterns =
      List.of(List.of(KEYWORD, NUMBER, UNIT), List.of(NUMBER, UNIT, KEYWORD));

  @Override
  public List<List<TokenType>> getPatterns() {
    return patterns;
  }

  @Override
  public void apply(ParseContext ctx, List<Token> tokens, int pos) {
    List<Token> slice = tokens.subList(pos, pos + this.length());

    // Extract based on which pattern matched
    if (slice.get(0).getType() == KEYWORD) {
      // NUMBER MONTH [YEAR]
      Month month = (Month) slice.get(1).getValue();
      MonthDay day = MonthDay.of(month, (int) slice.get(0).getValue());
      Year year = (slice.size() == 3) ? Year.of((int) slice.get(2).getValue()) : null;

      ctx.setAbsoluteDate(day, month, year);

    } else {
      // MONTH NUMBER [YEAR]
      Month month = (Month) slice.get(0).getValue();
      MonthDay day = MonthDay.of(month, (int) slice.get(1).getValue());
      Year year = (slice.size() == 3) ? Year.of((int) slice.get(2).getValue()) : null;

      ctx.setAbsoluteDate(day, month, year);
    }
  }
}
