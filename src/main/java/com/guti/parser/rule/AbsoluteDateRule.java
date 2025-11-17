package com.guti.parser.rule;

import com.guti.parser.ParseContext;
import com.guti.tokenizer.Token;
import com.guti.tokenizer.constant.TokenType;

import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.util.List;

import static com.guti.tokenizer.constant.TokenType.*;

public class AbsoluteDateRule extends Rule {

  private final List<List<TokenType>> patterns =
      List.of(
          List.of(NUMBER, MONTH, NUMBER),
          List.of(MONTH, NUMBER, NUMBER),
          List.of(NUMBER, MONTH),
          List.of(MONTH, NUMBER));

  @Override
  public List<List<TokenType>> getPatterns() {
    return patterns;
  }

  @Override
  public boolean apply(ParseContext ctx, List<Token> tokens, int pos) {
    List<Token> slice = tokens.subList(pos, pos + this.length());

    // Extract based on which pattern matched
    if (slice.get(0).type() == NUMBER) {
      // NUMBER MONTH [YEAR]
      Month month = (Month) slice.get(1).value();
      MonthDay day = MonthDay.of(month, (int) slice.get(0).value());
      Year year = (slice.size() == 3) ? Year.of((int) slice.get(2).value()) : null;

      ctx.setAbsoluteDate(day, month, year);

    } else {
      // MONTH NUMBER [YEAR]
      Month month = (Month) slice.get(0).value();
      MonthDay day = MonthDay.of(month, (int) slice.get(1).value());
      Year year = (slice.size() == 3) ? Year.of((int) slice.get(2).value()) : null;

      ctx.setAbsoluteDate(day, month, year);
    }

    return true;
  }
}
